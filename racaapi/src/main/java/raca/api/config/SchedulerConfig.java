package raca.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import raca.api.Scheduler.SchedulerMovimento;

@Configuration
@EnableScheduling
public class SchedulerConfig {

    @Bean
    public SchedulerMovimento processaMovimentacao() {
        return new SchedulerMovimento();
    }
    @Scheduled(fixedRate = 500000) // Executa a cada 5 segundos
    public void scheduledTask() {
        processaMovimentacao().run();
    }
}
