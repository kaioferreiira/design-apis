package com.example.design.api.business.dto;


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
    private String nome;
    private String email;
    private String celular;
    private Integer idade;

}
