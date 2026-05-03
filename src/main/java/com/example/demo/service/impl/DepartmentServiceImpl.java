package com.example.demo.service.impl;

import com.example.demo.model.Employee;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.DepartmentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    @Transactional
    public void deleteDepartment(Long id, RedirectAttributes redirectAttributes) {
        List<Employee> employees = employeeRepository.findByDepartmentId(id);
        int count = employees.size();

        for (Employee employee : employees) {
            employee.setDepartment(null);
            employeeRepository.save(employee);
        }

        departmentRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("success", "Đã xóa phòng ban và cập nhật trạng thái cho " + count + " nhân viên");
    }
}
