package br.com.doug.batch.config.processor;

import br.com.doug.batch.domain.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class ProcessorConfig {

    @Bean
    public ItemProcessor<Employee, Employee> processor() {
        return employee -> {
            log.info("Employee: {}", employee);
            return employee;
        };
    }
}
