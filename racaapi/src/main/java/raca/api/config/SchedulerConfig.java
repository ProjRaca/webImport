package raca.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import raca.api.Scheduler.SchedulerMovimento;
import raca.api.service.MovimentacaoService;

@Configuration
@EnableScheduling
public class SchedulerConfig {

    private MovimentacaoService service;

    @Bean
    public SchedulerMovimento processaMovimentacao() {
        return new SchedulerMovimento();
    }
    @Scheduled(cron = "0 3 * * *") // Executa as am todos os dias
    public void scheduledTask() {
        processaMovimentacao().run();
    }

    /**
     * Na expressão cron "0 3 * * *", os campos representam os seguintes valores:
     *
     * Minuto: 0 (representa o minuto 0)
     * Hora: 3 (representa a hora 3, ou seja, 3 horas da manhã)
     * Dia do mês: * (todos os dias do mês)
     * Mês: * (todos os meses)
     * Dia da semana: * (todos os dias da semana)
     */


}
