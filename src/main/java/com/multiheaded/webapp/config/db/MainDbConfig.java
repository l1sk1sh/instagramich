package com.multiheaded.webapp.config.db;

import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
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
        entityManagerFactoryRef = "entityManagerFactoryMain",
        transactionManagerRef = "transactionManagerMain",
        basePackages = "com.multiheaded.webapp.model.main"
)
public class MainDbConfig {

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.main.datasource")
    public DataSource dataSourceMain() {
        return DataSourceBuilder
                .create()
                .build();
    }

    @Primary
    @Bean(name = "entityManagerFactoryMain")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryMain(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(dataSourceMain())
                .packages("com.multiheaded.webapp.model.main")
                .persistenceUnit("mainPU")
                .build();
    }

    @Primary
    @Bean(name = "transactionManagerMain")
    public PlatformTransactionManager transactionManagerMain(
            @Qualifier("entityManagerFactoryMain") EntityManagerFactory entityManagerFactoryMain) {
        return new JpaTransactionManager(entityManagerFactoryMain);
    }
}
