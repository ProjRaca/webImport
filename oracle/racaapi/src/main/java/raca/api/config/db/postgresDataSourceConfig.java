package raca.api.config.db;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "postgresEntityManagerFactory",
        transactionManagerRef = "postgresTransactionManager",
        basePackages = "raca.api.repository.postgres")
public class postgresDataSourceConfig {
    @Primary
    @Bean(name = "postgresDataSource")
    @ConfigurationProperties(prefix = "postgres.datasource")
    public HikariDataSource mysqlDataSource() {
        return DataSourceBuilder.create()
                .type(HikariDataSource.class)
                .build();
    }
    @Primary
    @Bean(name = "postgresEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean postgresEntityManagerFactory(EntityManagerFactoryBuilder builder,
                                                                               @Qualifier("postgresDataSource") DataSource dataSource) {
        return builder.dataSource(dataSource)
                .packages("raca.api.domain.entity.postgres")
                .persistenceUnit("postgresPU")
                .build();
    }
    @Primary

    @Bean(name = "postgresTransactionManager")
    public PlatformTransactionManager postgresTransactionManager(@Qualifier("postgresEntityManagerFactory")
                                                                 EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
