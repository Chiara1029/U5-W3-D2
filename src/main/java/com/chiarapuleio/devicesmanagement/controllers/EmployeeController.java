package com.chiarapuleio.devicesmanagement.controllers;

import com.chiarapuleio.devicesmanagement.dao.EmployeeDAO;
import com.chiarapuleio.devicesmanagement.entities.Device;
import com.chiarapuleio.devicesmanagement.entities.Employee;
import com.chiarapuleio.devicesmanagement.exceptions.BadRequestException;
import com.chiarapuleio.devicesmanagement.payloads.DeviceDTO;
import com.chiarapuleio.devicesmanagement.payloads.EmployeeDTO;
import com.chiarapuleio.devicesmanagement.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeSrv;
    @Autowired
    private EmployeeDAO employeeDAO;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee saveEmployee(@RequestBody @Validated EmployeeDTO employee, BindingResult validation) throws Exception {
        if(validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        }
        return employeeSrv.save(employee);
    }

    @GetMapping
    public List<Employee> getEmployees(){
        return employeeDAO.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public Employee findById(@PathVariable UUID id){
        return employeeSrv.findById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Employee findAndUpdate(@PathVariable UUID id, @RequestBody Employee employee){
        return employeeSrv.findByIdAndUpdate(id, employee);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findAndDelete(@PathVariable UUID id){
        employeeSrv.findByIdAndDelete(id);
    }

    @PatchMapping("/{id}/avatar")
    public Employee uploadAvatar(@RequestParam("avatar") MultipartFile file, @PathVariable UUID id) {
        try {
            return employeeSrv.uploadAvatar(id, file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
