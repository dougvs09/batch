package br.com.doug.batch.config.writer;

import br.com.doug.batch.domain.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.database.ItemPreparedStatementSetter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class WriterConfig {

    @Bean
    public JdbcBatchItemWriter<Employee> writer(@Qualifier("appDataSource") DataSource appDataSource) {
        JdbcBatchItemWriterBuilder<Employee> itemWriter = new JdbcBatchItemWriterBuilder<>();
        itemWriter.sql("IF NOT EXISTS(SELECT FROM employee WHERE employeeId = ?) INSERT INTO employee (name, role, login, salary) VALUES (?, ?, ?, ?)");
        itemWriter.dataSource(appDataSource);
        itemWriter.itemPreparedStatementSetter(itemPreparedStatementSetter());
        return itemWriter.build();
    }

    private ItemPreparedStatementSetter<Employee> itemPreparedStatementSetter() {
        return (employee, preparedStatement) -> {
            preparedStatement.setInt(1, employee.getEmployeeId());
            preparedStatement.setString(2, employee.getName());
            preparedStatement.setString(3, employee.getRole());
            preparedStatement.setString(4, employee.getLogin());
            preparedStatement.setInt(5, employee.getSalary());
        };
    }
}
