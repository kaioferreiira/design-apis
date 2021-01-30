package com.example.design.app.business.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String cellphone;
    private Integer age;
    private String cpf;

    public Employee(String name, String email, String cellphone, Integer age, String cpf) {
        this.name = name;
        this.email = email;
        this.cellphone = cellphone;
        this.age = age;
        this.cpf = cpf;
    }
}
