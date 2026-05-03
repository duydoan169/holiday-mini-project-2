package com.example.demo.service;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface DepartmentService {
    void deleteDepartment(Long id, RedirectAttributes redirectAttributes);
}
