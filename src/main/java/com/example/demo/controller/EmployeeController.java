package com.example.demo.controller;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.model.Employee;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    private final DepartmentRepository departmentRepository;

    @GetMapping
    public String listEmployees(Model model,
                                @RequestParam(defaultValue = "") String search,
                                @RequestParam(required = false) Long departmentId,
                                @RequestParam(required = false) Integer minAge,
                                @RequestParam(required = false) Integer maxAge,
                                @RequestParam(defaultValue = "asc") String sortDirection,
                                @RequestParam(defaultValue = "id") String sortField,
                                @PageableDefault(page = 0, size = 5) Pageable pageable) {
        Page<Employee> result = employeeService.search(search, departmentId, minAge, maxAge, pageable);
        model.addAttribute("pageable", result);
        model.addAttribute("search", search);
        model.addAttribute("departmentId", departmentId);
        model.addAttribute("minAge", minAge);
        model.addAttribute("maxAge", maxAge);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection", sortDirection);
        model.addAttribute("departments", departmentRepository.findAll());
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