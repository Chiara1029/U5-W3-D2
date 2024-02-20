package com.chiarapuleio.devicesmanagement.services;

import com.chiarapuleio.devicesmanagement.entities.Employee;
import com.chiarapuleio.devicesmanagement.exceptions.UnauthorizedException;
import com.chiarapuleio.devicesmanagement.payloads.EmployeeLoginDTO;
import com.chiarapuleio.devicesmanagement.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private EmployeeService empSrv;
    @Autowired
    private JWTTools jwtTools;

    public String authenticateEmployeeAndGenerateToken(EmployeeLoginDTO payload){
        Employee employee = empSrv.findByEmail(payload.email());
        if(employee.getPassword().equals(payload.password())){
            return jwtTools.createToken(employee);
        } else {
            throw new UnauthorizedException("Wrong credentials.");
        }
    }
}
