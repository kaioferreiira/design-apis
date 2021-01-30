package com.example.design.app.business.dto;


import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EmployeeDTO {

    @NotBlank
    private String name;
    private String email;
    private String cellphone;
    private Integer age;
    private String cpf;

}
