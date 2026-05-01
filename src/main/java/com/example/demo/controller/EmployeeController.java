package com.example.demo.controller;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    private final DepartmentRepository departmentRepository;

    @GetMapping
    public String listEmployees(Model model) {
        model.addAttribute("employees", employeeService.getAllEmployee());
        return "employee-list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("employeeDTO", new EmployeeDTO());
        model.addAttribute("departments", departmentRepository.findAll());
        return "form";
    }

    @PostMapping("/create")
    public String saveEmployee(
            @Valid @ModelAttribute EmployeeDTO employeeDTO,
            BindingResult result,
            Model model
    ) {
        if (result.hasErrors()) {
            model.addAttribute("departments", departmentRepository.findAll());
            return "form";
        }
        employeeService.saveEmployee(employeeDTO);
        return "redirect:/employees";
    }
}