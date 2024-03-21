package br.com.doug.batch.config.writer;

import br.com.doug.batch.domain.Employee;
import lombok.AllArgsConstructor;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.support.CompositeItemWriter;
import org.springframework.batch.item.support.builder.CompositeItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class WriterCompositeConfig {

    private final JdbcBatchItemWriter<Employee> writerDatabase;
    private final FlatFileItemWriter<Employee> writerFile;

    @Bean
    public CompositeItemWriter<Employee> writerComposite() {
        return new CompositeItemWriterBuilder<Employee>()
                .delegates(writerDatabase, writerFile)
                .build();
    }
}
