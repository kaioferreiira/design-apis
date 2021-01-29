package com.example.design.app.business.service;

import com.example.design.app.business.dto.EmployeeDTO;
import com.example.design.app.business.entity.Employee;
import com.example.design.app.business.entity.converter.EmployeeConverter;
import com.example.design.app.business.exception.EmployeeExceptionMessage;
import com.example.design.app.business.exception.exceptions.ObjectNotFoundException;
import com.example.design.app.business.repository.EmployeeRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {


    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void adicionaFuncionario(EmployeeDTO employeeDTO) {
        saveEntity(EmployeeConverter.toEntity(employeeDTO));
    }

    @Override
    public List<EmployeeDTO> buscaFuncionariosList() {
        List<Employee> listaEmployees = employeeRepository.findAll();
        List<EmployeeDTO> listEmployeeDTO = listaEmployees.stream().map(EmployeeConverter::toDTO).collect(Collectors.toList());
        return listEmployeeDTO;
    }

    @Override
    public Optional<EmployeeDTO> buscaFuncionario(Long codigoFuncionario) {
         return employeeRepository.findById(codigoFuncionario).map(EmployeeConverter::toDTO);
    }

    @Override
    public void atualizaFuncionario(Long codigoFuncionario, EmployeeDTO employeeDTO) {

        employeeRepository.findById(codigoFuncionario).orElseThrow(() -> new ObjectNotFoundException(
            EmployeeExceptionMessage.ERRO_ENCONTRAR_DADOS_DO_FUNCIONARIO));

        Employee employeeEntity = EmployeeConverter.toEntity(employeeDTO);
        employeeEntity.setId(codigoFuncionario);
        saveEntity(employeeEntity);
    }

    private void saveEntity(Employee employee) {
        employeeRepository.save(employee);
    }

}
