package com.satendra.jwtcsrf;

import io.jsonwebtoken.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


public class JwtAuthorizationFilter extends BasicAuthenticationFilter {


    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws IOException, ServletException {
        var authentication = getAuthentication(request);
        if (authentication == null) {
            filterChain.doFilter(request, response);
            return;
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        var token = request.getHeader(SecurityConstants.TOKEN_HEADER);
        if (!StringUtils.isEmpty(token) && token.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            try {
                var signingKey = SecurityConstants.JWT_SECRET.getBytes();

                var parsedToken = Jwts.parserBuilder().setSigningKey(signingKey).requireIssuer(SecurityConstants.TOKEN_ISSUER).build().parse(token.replace("Bearer ", ""));

                Claims claims = (Claims) parsedToken.getBody();

                var username = claims.getSubject();

                var authorities = ((List<?>) claims.get("rol")).stream().map(authority -> new SimpleGrantedAuthority((String) authority))
                        .collect(Collectors.toList());

                if (!StringUtils.isEmpty(username)) {
                    return new UsernamePasswordAuthenticationToken(username, null, authorities);
                }
            } catch (ExpiredJwtException exception) {
                //"Request to parse expired JWT : {} failed : {}", token, exception.getMessage()
            } catch (UnsupportedJwtException exception) {
                //"Request to parse unsupported JWT : {} failed : {}", token, exception.getMessage()
            } catch (MalformedJwtException exception) {
                //"Request to parse invalid JWT : {} failed : {}", token, exception.getMessage()
            } catch (SignatureException exception) {
                //"Request to parse JWT with invalid signature : {} failed : {}", token, exception.getMessage()
            } catch (IllegalArgumentException exception) {
                //"Request to parse empty or null JWT : {} failed : {}", token, exception.getMessage()
            }
        }

        return null;
    }
}
