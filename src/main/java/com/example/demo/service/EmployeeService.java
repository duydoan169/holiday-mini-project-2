package com.example.demo.service;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployee();
    void saveEmployee(EmployeeDTO employeeDTO);
    Page<Employee> search(String search, Pageable pageable);
}