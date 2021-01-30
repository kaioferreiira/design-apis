package com.example.design.app.business.business;

import com.example.design.app.business.dto.EmployeeDTO;
import com.example.design.app.business.entity.Employee;
import com.example.design.app.business.exception.EmployeeExceptionMessage;
import com.example.design.app.business.exception.exceptions.ObjectNotFoundException;
import com.example.design.app.business.repository.EmployeeRepository;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeBusinessImpl implements EmployeeBusiness {

  private EmployeeRepository employeeRepository;

  @Autowired
  public EmployeeBusinessImpl(EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }

  @Override
  public void adicionaFuncionario(EmployeeDTO employeeDTO) {
    var employeeEntity = toEntity.apply(employeeDTO);
    saveEntity(employeeEntity);
  }


  @Override
  public List<EmployeeDTO> findAllEmployees() {
    List<Employee> listaEmployees = employeeRepository.findAll();
    List<EmployeeDTO> listEmployeeDTO = listaEmployees.stream()
        .map(this::toDTO)
        .collect(Collectors.toList());
    return listEmployeeDTO;
  }

  @Override
  public Optional<EmployeeDTO> findEmployee(Long codigoFuncionario) {
    return employeeRepository.findById(codigoFuncionario).map(this::toDTO);
  }

  @Override
  public void updateEmployee(Long codigoFuncionario, EmployeeDTO employeeDTO) {

    employeeRepository.findById(codigoFuncionario).orElseThrow(() -> new ObjectNotFoundException(
        EmployeeExceptionMessage.ERRO_ENCONTRAR_DADOS_DO_FUNCIONARIO));

    var employeeEntity = this.toEntity(employeeDTO);
    employeeEntity.setId(codigoFuncionario);
    saveEntity(employeeEntity);
  }


  private void saveEntity(Employee employee) {
    employeeRepository.save(employee);
  }

  private Employee toEntity(EmployeeDTO employeeDTO) {
    return toEntity.apply(employeeDTO);
  }

  private EmployeeDTO toDTO(Employee employee) {
    return toDTO.apply(employee);
  }

  private Function<Employee, EmployeeDTO> toDTO = employee -> {

    return EmployeeDTO.builder()
        .age(employee.getAge())
        .cpf(employee.getCpf())
        .cellphone(employee.getCpf())
        .name(employee.getName())
        .email(employee.getEmail())
        .build();
  };

  private Function<EmployeeDTO, Employee> toEntity = dto -> {

    return Employee.builder()
        .name(dto.getName())
        .cpf(dto.getCpf())
        .age(dto.getAge())
        .cellphone(dto.getCellphone())
        .email(dto.getEmail()).build();
  };


}
