package com.example.design.api;

import com.example.design.api.business.dto.EmployeeDTO;
import com.example.design.api.business.service.EmployeeServiceImpl;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class DesignApiApplication  implements CommandLineRunner {


	@Autowired
	private EmployeeServiceImpl employeeService;

	public static void main(String[] args) {
		SpringApplication.run(DesignApiApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {

		EmployeeDTO employeeDTO = new EmployeeDTO()
				.builder()
				.nome("Kaio Ferreira")
				.email("employee.costa@teste.com.br")
				.idade(27)
				.celular("3242-8985")
				.build();

		employeeService.adicionaFuncionario(employeeDTO);


	}

	}
