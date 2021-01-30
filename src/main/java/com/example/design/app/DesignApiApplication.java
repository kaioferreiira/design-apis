package com.example.design.app;

import com.example.design.app.business.dto.EmployeeDTO;
import com.example.design.app.business.business.EmployeeBusinessImpl;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class DesignApiApplication  implements CommandLineRunner {


	@Autowired
	private EmployeeBusinessImpl employeeService;

	public static void main(String[] args) {
		SpringApplication.run(DesignApiApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {

		EmployeeDTO employeeDTO = new EmployeeDTO()
				.builder()
				.name("Kaio Ferreira")
				.email("employee.costa@teste.com.br")
				.age(27)
				.cellphone("3242-8985")
				.build();

		employeeService.adicionaFuncionario(employeeDTO);


	}

	}
