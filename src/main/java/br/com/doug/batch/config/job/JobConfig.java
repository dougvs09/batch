package br.com.doug.batch.config.job;

import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class JobConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final Step step;

    @Bean
    public Job job() {
        return jobBuilderFactory
                .get("job")
                .start(step)
                .build();
    }
}
