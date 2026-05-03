package com.example.demo.repository;

import com.example.demo.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("""
        select e from Employee e where 
        lower(e.name) like lower(concat('%', :search, '%'))
        and (:departmentId is null or e.department.id = :departmentId)
        and (:minAge is null or e.age >= :minAge)
        and (:maxAge is null or e.age <= :maxAge)
    """)
    Page<Employee> search(
            @Param("search") String search,
            @Param("departmentId") Long departmentId,
            @Param("minAge") Integer minAge,
            @Param("maxAge") Integer maxAge,
            Pageable pageable
    );

    List<Employee> findByDepartmentId(Long departmentId);
}