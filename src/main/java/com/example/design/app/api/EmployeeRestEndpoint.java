package com.example.design.app.api;

import com.example.design.app.business.dto.EmployeeDTO;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface EmployeeRestEndpoint {

    ResponseEntity<List<EmployeeDTO>> findAllEmployees();

    ResponseEntity<EmployeeDTO> findEmployee(Long employeeId);

    ResponseEntity<Void> addEmployee(EmployeeDTO employeeDTO);

    ResponseEntity<Void> updateEmployee(Long employeeId, EmployeeDTO employeeDTO);

    ResponseEntity<Void> updatePartiallyEmployee(Long employeeId, EmployeeDTO employeeDTO);

    ResponseEntity<Void> deleteEmployee(Long employeeId);

}
