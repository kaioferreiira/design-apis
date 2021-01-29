package com.example.design.app.business.entity.converter;

import com.example.design.app.business.dto.EmployeeDTO;
import com.example.design.app.business.entity.Employee;
import com.example.design.app.business.exception.EmployeeExceptionMessage;
import com.example.design.app.business.exception.exceptions.ValidationException;
import java.util.Objects;


public class EmployeeConverter {

    public static Employee toEntity(EmployeeDTO employeeDTO) {
        if (Objects.isNull(employeeDTO)) {
            throw new ValidationException(EmployeeExceptionMessage.ERRO_DADOS_INVALIDOS_ASSOCIADO);
        }
        return new Employee(employeeDTO.getNome(), employeeDTO.getEmail(), employeeDTO
            .getCelular(), employeeDTO.getIdade());
    }



    public static EmployeeDTO toDTO(Employee employee){
        if(Objects.isNull(employee)){
            return null;
        }
        return new EmployeeDTO()
                .builder()
                .nome(employee.getNome())
                .email(employee.getEmail())
                .idade(employee.getIdade())
                .celular(employee.getCelular())
                .build();
    }

}
