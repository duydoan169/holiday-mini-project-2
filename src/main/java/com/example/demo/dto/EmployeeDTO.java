package com.example.demo.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

    @NotBlank(message = "Tên không được để trống")
    private String name;

    @NotNull(message = "Tuổi không được để trống")
    @Min(value = 1, message = "Tuổi không được âm")
    private Integer age;

    @NotBlank(message = "Trạng thái không được để trống")
    private String status;

    @NotNull(message = "Phòng ban không được để trống")
    private Long departmentId;

    private MultipartFile file;
}