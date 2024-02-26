package br.com.doug.batch.domain;

import lombok.Data;

@Data
public class Employee {

    private Integer id;
    private Integer employeeId;
    private String name;
    private String role;
    private String login;
    private Integer salary;
}
