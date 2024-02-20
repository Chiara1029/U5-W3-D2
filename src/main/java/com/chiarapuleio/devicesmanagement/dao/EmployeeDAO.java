package com.chiarapuleio.devicesmanagement.dao;

import com.chiarapuleio.devicesmanagement.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface EmployeeDAO extends JpaRepository<Employee, UUID> {
    Optional<Employee> findByEmail(String email);
}
