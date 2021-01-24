package com.example.design.api.business.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EmployeeDTO {

    private String nome;
    private String email;
    private String celular;
    private Integer idade;

}
