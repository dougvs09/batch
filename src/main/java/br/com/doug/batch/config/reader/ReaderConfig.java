package br.com.doug.batch.config.reader;

import br.com.doug.batch.domain.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
@Slf4j
public class ReaderConfig {

    @Bean
    public FlatFileItemReader<Employee> reader() {
        log.info("Lendo arquivo");

        FlatFileItemReader<Employee> flatFileItemReader = new FlatFileItemReader<>();

        flatFileItemReader.setName("reader");
        flatFileItemReader.setResource(new FileSystemResource("/home/doug/Projects/batch/src/main/resources/spreadsheet.csv"));
        flatFileItemReader.setLineMapper(lineMapper());

        return flatFileItemReader;
    }

    private DefaultLineMapper<Employee> lineMapper() {
        DefaultLineMapper<Employee> lineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames("employeeId", "name", "role", "login", "salary");
        tokenizer.setDelimiter(";");

        BeanWrapperFieldSetMapper<Employee> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Employee.class);

        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);

        return lineMapper;
    }
}
