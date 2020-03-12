## Security filter chain doing authentication and authorization of *jwt token*
 
### _Jwt (structured token/ value token) with spring security._ 
##### This is the restful application where, rest uri send for to get token; token will get as response attached in response-header as authorization key
##### Have Custom  authentication entry point for handling custom json response


#### Request for authentication

~~~
$ curl -X POST -v -H 'Content-Type: application/json' -d '{"username": "admin","password":"admin"}' http://localhost:8080/api/authenticate

Note: Unnecessary use of -X or --request, POST is already inferred.
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
  0     0    0     0    0     0      0      0 --:--:-- --:--:-- --:--:--     0*   Trying 127.0.0.1...
* TCP_NODELAY set
* Connected to localhost (127.0.0.1) port 8080 (#0)
> POST /api/authenticate HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.63.0
> Accept: */*
> Content-Type: application/json
> Content-Length: 40
>
} [40 bytes data]
* upload completely sent off: 40 out of 40 bytes
< HTTP/1.1 200
< Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJzZWN1cmUtYXBpIiwiYXVkIjoic2VjdXJlLWFwcCIsInN1YiI6ImFkbWluIiwiZXhwIjoxNTg0ODc4MzQ2LCJyb2wiOlsiUk9MRV9BRE1JTiJdfQ.0_zt_rljZO00y8ZO9GOZ0ZrmLPS_UXmGbmzsvxfQGcHbWTjLqC8uFlTyB_N7trMesRdZ4PV6_C57pWN_0ktw0w
< X-Content-Type-Options: nosniff
< X-XSS-Protection: 1; mode=block
< Cache-Control: no-cache, no-store, max-age=0, must-revalidate
< Pragma: no-cache
< Expires: 0
< X-Frame-Options: DENY
< Content-Length: 0
< Date: Thu, 12 Mar 2020 11:59:06 GMT
<
100    40    0     0  100    40      0    363 --:--:-- --:--:-- --:--:--   363
* Connection #0 to host localhost left intact

~~~

#### Access protected resource with passing jwt with request
~~~
$ curl http://localhost:8080/test -H 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJzZWN1cmUtYXBpIiwiYXVkIjoic2VjdXJlLWFwcCIsInN1YiI6ImFkbWluIiwiZXhwIjoxNTg0ODc4MzQ2LCJyb2wiOlsiUk9MRV9BRE1JTiJdfQ.0_zt_rljZO00y8ZO9GOZ0ZrmLPS_UXmGbmzsvxfQGcHbWTjLqC8uFlTyB_N7trMesRdZ4PV6_C57pWN_0ktw0w' | json_pp
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100    28    0    28    0     0   1750      0 --:--:-- --:--:-- --:--:--  1750
[
   "satendra",
   "amit",
   "qwerty"
]
~~~

