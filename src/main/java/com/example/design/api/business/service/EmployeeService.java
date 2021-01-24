package com.example.design.api.business.service;

import com.example.design.api.business.dto.EmployeeDTO;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {


    List<EmployeeDTO> buscaFuncionariosList();

    Optional<EmployeeDTO> buscaFuncionario(Long codigoFuncionario);

    void adicionaFuncionario(EmployeeDTO associado);

    void atualizaFuncionario(Long codigoFuncionario, EmployeeDTO funcionario);



}
