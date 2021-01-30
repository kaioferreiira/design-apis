package com.example.design.app.api;

import com.example.design.app.business.dto.EmployeeDTO;
import com.example.design.app.business.business.EmployeeBusiness;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import java.util.Objects;
import javax.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = EmployeeRest.BASE_PATH)
public class EmployeeRest implements EmployeeRestEndpoint {

    public static final String BASE_PATH = "/employees";

    private EmployeeBusiness employeeBusiness;

    public EmployeeRest(EmployeeBusiness employeeBusiness) {
        this.employeeBusiness = employeeBusiness;
    }

    @Override
    @PostMapping(path ="/")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "")
    })
    public ResponseEntity<Void> addEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {

        employeeBusiness.adicionaFuncionario(employeeDTO);
        return ResponseEntity.ok().build();
    }

    @Override
    @GetMapping(path ="/{employeeId}")
    public ResponseEntity<EmployeeDTO> findEmployee(@PathVariable Long employeeId) {

        ResponseEntity<EmployeeDTO> funcionarioDTOResponseEntity = employeeBusiness.findEmployee(employeeId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());

        return funcionarioDTOResponseEntity;
    }

    @Override
    @Operation(summary = "This is to fetch All the Books stored in Db")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",
            description = "Fetched All the Books form Db",
            content = {@Content(mediaType = "application/json")}),
        @ApiResponse(responseCode = "404",
            description = "NOt Available",
            content = @Content)
    })
    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EmployeeDTO>> findAllEmployees() {

        ResponseEntity<List<EmployeeDTO>> response = ResponseEntity.ok(employeeBusiness.findAllEmployees());
        if (Objects.isNull(response.getBody())) {
            response = ResponseEntity.noContent().build();
        }
        return response;
    }

    @Override
    @PutMapping(path ="/{employeeId}")
    public ResponseEntity<Void> updateEmployee(Long codigoFuncionario, EmployeeDTO employeeDTO) {

        employeeBusiness.updateEmployee(codigoFuncionario, employeeDTO);

        return null;
    }

    @Override
    @PatchMapping(path ="/{employeeId}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "")
    })
    public ResponseEntity<Void> updatePartiallyEmployee(Long employeeId, EmployeeDTO employeeDTO) {

        employeeBusiness.updateEmployee(employeeId, employeeDTO);

        return null;
    }

    @Override
    @DeleteMapping(path = "/{employeeId}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "")
    })
    public ResponseEntity<Void> deleteEmployee(Long employeeId) {
        return null;
    }


}
