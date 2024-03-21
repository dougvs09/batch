package br.com.doug.batch.config.writer;

import br.com.doug.batch.domain.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.database.ItemPreparedStatementSetter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@Slf4j
public class WriterDatabaseConfig {

    private static final String INSERT_EMPLOYEE_SQL = "INSERT INTO employee (employeeId, name, role, login, salary)\n" +
            "SELECT * FROM (SELECT ?, ?, ?, ?, ?) AS tmp\n" +
            "WHERE NOT EXISTS (\n" +
            "    SELECT employeeId FROM employee WHERE employeeId = ?\n" +
            ") LIMIT 1";

    @Bean
    public JdbcBatchItemWriter<Employee> writerDatabase(@Qualifier("springDataSource") DataSource appDataSource) {
        log.info("Salvando no banco de dados");

        return new JdbcBatchItemWriterBuilder<Employee>()
                .sql(INSERT_EMPLOYEE_SQL)
                .dataSource(appDataSource)
                .itemPreparedStatementSetter(itemPreparedStatementSetter())
                .assertUpdates(false)
                .build();
    }

    private ItemPreparedStatementSetter<Employee> itemPreparedStatementSetter() {
        return (employee, preparedStatement) -> {
            preparedStatement.setInt(1, employee.getEmployeeId());
            preparedStatement.setString(2, employee.getName());
            preparedStatement.setString(3, employee.getRole());
            preparedStatement.setString(4, employee.getLogin());
            preparedStatement.setInt(5, employee.getSalary());
            preparedStatement.setInt(6, employee.getEmployeeId());
        };
    }
}
