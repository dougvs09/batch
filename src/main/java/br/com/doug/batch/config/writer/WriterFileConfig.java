package br.com.doug.batch.config.writer;

import br.com.doug.batch.domain.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
@Slf4j
public class WriterFileConfig {

    @Bean
    public FlatFileItemWriter<Employee> writerFile() {
        log.info("Escrevendo no arquivo");

        return new FlatFileItemWriterBuilder<Employee>()
                .name("File Writer")
                .resource(new FileSystemResource("/home/doug/Projects/batch/src/main/resources/employees.csv"))
                .delimited()
                .delimiter(";")
                .names("employeeId", "name", "role", "login", "salary")
                .build();
    }
}
