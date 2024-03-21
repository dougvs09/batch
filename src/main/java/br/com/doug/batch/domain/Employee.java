package br.com.doug.batch.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Employee {

    private Integer employeeId;
    private String name;
    private String role;
    private String login;
    private Integer salary;
}
