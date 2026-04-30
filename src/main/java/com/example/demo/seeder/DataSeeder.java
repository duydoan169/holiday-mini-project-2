package com.example.demo.seeder;

import com.example.demo.model.Department;
import com.example.demo.model.Employee;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public void run(String... args) throws Exception {
        if (departmentRepository.count() == 0 && employeeRepository.count() == 0) {

            Department engineering = new Department(null, "Engineering", "Hanoi", null);
            Department marketing = new Department(null, "Marketing", "Ho Chi Minh", null);
            Department hr = new Department(null, "Human Resources", "Da Nang", null);

            departmentRepository.save(engineering);
            departmentRepository.save(marketing);
            departmentRepository.save(hr);

            employeeRepository.save(new Employee(null, "Nguyen Van A", 28, "avatar1.png", "ACTIVE", engineering));
            employeeRepository.save(new Employee(null, "Tran Thi B", 32, "avatar2.png", "ACTIVE", marketing));
            employeeRepository.save(new Employee(null, "Le Van C", 25, "avatar3.png", "INACTIVE", engineering));
            employeeRepository.save(new Employee(null, "Pham Thi D", 30, "avatar4.png", "ACTIVE", hr));
            employeeRepository.save(new Employee(null, "Hoang Van E", 27, "avatar5.png", "ACTIVE", marketing));
        }
    }
}