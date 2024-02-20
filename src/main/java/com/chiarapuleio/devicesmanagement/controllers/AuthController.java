package com.chiarapuleio.devicesmanagement.controllers;

import com.chiarapuleio.devicesmanagement.entities.Employee;
import com.chiarapuleio.devicesmanagement.payloads.EmployeeDTO;
import com.chiarapuleio.devicesmanagement.payloads.EmployeeLoginDTO;
import com.chiarapuleio.devicesmanagement.payloads.LoginRespDTO;
import com.chiarapuleio.devicesmanagement.services.AuthService;
import com.chiarapuleio.devicesmanagement.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authSrv;
    @Autowired
    private EmployeeService empSrv;

    @PostMapping("/login")
    public LoginRespDTO login(@RequestBody EmployeeLoginDTO payload){
        return new LoginRespDTO(authSrv.authenticateEmployeeAndGenerateToken(payload));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee saveEmployee(@RequestBody EmployeeDTO newEmployee) throws IOException {
        return this.empSrv.save(newEmployee);
    }
}
