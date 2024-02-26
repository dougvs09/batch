package br.com.doug.batch.config.step;

import br.com.doug.batch.domain.Employee;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@AllArgsConstructor
public class StepConfig {

    private final PlatformTransactionManager transactionManagerApp;
    private final StepBuilderFactory stepBuilderFactory;
    private final FlatFileItemReader<Employee> reader;
    private final ItemProcessor<Employee, Employee> processor;
    private final JdbcBatchItemWriter<Employee> writer;

    @Bean
    public Step step() {
        return stepBuilderFactory
                .get("step")
                .<Employee, Employee>chunk(2)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .transactionManager(transactionManagerApp)
                .build();
    }
}
