package com.satendra.jwtcsrf;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
public class EmployeeController  {


    @GetMapping("/test")
    public Collection<String> getEmployee(){

        return List.of("satendra","amit","qwerty");
    }
}
