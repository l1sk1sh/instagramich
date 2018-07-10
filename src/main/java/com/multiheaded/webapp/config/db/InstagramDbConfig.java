package com.multiheaded.webapp.config.db;

import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
        entityManagerFactoryRef = "entityManagerFactoryInstagram",
        transactionManagerRef = "transactionManagerInstagram",
        basePackages = "com.multiheaded.webapp.model.instagram"
)
public class InstagramDbConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.instagram.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder
                .create()
                .build();
    }

    @Bean(name = "entityManagerFactoryInstagram")
    public LocalContainerEntityManagerFactoryBean
    entityManagerFactoryInstagram(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(dataSource())
                .packages("com.multiheaded.webapp.model.instagram")
                .persistenceUnit("instagramPU")
                .build();
    }

    @Bean(name = "transactionManagerInstagram")
    public PlatformTransactionManager transactionManagerInstagram(
            @Qualifier("entityManagerFactoryInstagram") EntityManagerFactory
                    entityManagerFactoryInstagram) {
        return new JpaTransactionManager(entityManagerFactoryInstagram);
    }
}