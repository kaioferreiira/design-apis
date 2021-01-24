package com.example.design.api.rest;

import com.example.design.api.business.dto.EmployeeDTO;
import com.example.design.api.business.service.EmployeeService;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = EmployeeRest.BASE_PATH)
public class EmployeeRest implements EmployeeRestEndpoint {

    public static final String BASE_PATH = "/employees";

    private EmployeeService employeeService;

    @Autowired
    public EmployeeRest(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    @PostMapping("/")
    public ResponseEntity<Void> adicionaFuncionario(EmployeeDTO employeeDTO) {

        employeeService.adicionaFuncionario(employeeDTO);
        return ResponseEntity.ok().build();
    }

    @Override
    @GetMapping("/{codigoFuncionario}")
    public ResponseEntity<EmployeeDTO> buscaFuncionario(@PathVariable Long codigoFuncionario) {

        ResponseEntity<EmployeeDTO> funcionarioDTOResponseEntity = employeeService.buscaFuncionario(codigoFuncionario)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());

        return funcionarioDTOResponseEntity;
    }

    @Override
    @GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EmployeeDTO>> buscaFuncionariosList() {

        ResponseEntity<List<EmployeeDTO>> response = ResponseEntity.ok(employeeService.buscaFuncionariosList());
        if (Objects.isNull(response.getBody())) {
            response = ResponseEntity.noContent().build();
        }
        return response;
    }

    @Override
    @PutMapping("/{codigoFuncionario}")
    public ResponseEntity<Void> atualizaFuncionario(Long codigoFuncionario, EmployeeDTO employeeDTO) {

        employeeService.atualizaFuncionario(codigoFuncionario, employeeDTO);

        return null;
    }



}