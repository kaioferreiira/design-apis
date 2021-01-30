package com.example.design.app.business.business;

import com.example.design.app.business.dto.EmployeeDTO;
import java.util.List;
import java.util.Optional;

public interface EmployeeBusiness {


    List<EmployeeDTO> findAllEmployees();

    Optional<EmployeeDTO> findEmployee(Long codigoFuncionario);

    void adicionaFuncionario(EmployeeDTO associado);

    void updateEmployee(Long codigoFuncionario, EmployeeDTO funcionario);



}
