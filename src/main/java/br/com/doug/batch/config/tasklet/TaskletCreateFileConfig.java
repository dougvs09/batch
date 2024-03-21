package br.com.doug.batch.config.tasklet;

import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;

@Configuration
public class TaskletCreateFileConfig {

    @Bean
    public Tasklet tasklet() {
        return (stepContribution, chunkContext) -> {
            File file = new File("/home/doug/Projects/batch/src/main/resources/employees.csv");
            Boolean created = file.createNewFile();

            if (Boolean.TRUE.equals(created)) {
                return RepeatStatus.FINISHED;
            }

            throw new IOException("File do not created");
        };
    }
}
