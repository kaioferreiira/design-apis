package com.example.design.api.rest;

import com.example.design.api.business.dto.EmployeeDTO;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface EmployeeRestEndpoint {

    ResponseEntity<List<EmployeeDTO>> buscaFuncionariosList();

    ResponseEntity<EmployeeDTO> buscaFuncionario(Long employeeId);

    ResponseEntity<Void> adicionaFuncionario(EmployeeDTO employeeDTO);

    ResponseEntity<Void> atualizaFuncionario(Long employeeId, EmployeeDTO employeeDTO);

}
