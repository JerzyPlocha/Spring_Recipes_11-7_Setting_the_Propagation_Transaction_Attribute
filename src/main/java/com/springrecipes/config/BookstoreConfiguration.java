package com.springrecipes.config;

import com.springrecipes.bookshop.JdbcBookShop;
import com.springrecipes.bookshop.TransactionalJdbcBookShop;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * Author: Jerzy Plocha on 07/06/16.
 */
@Configuration
public class BookstoreConfiguration {

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/BOOKSHOP");
        dataSource.setUsername("postgres");
        dataSource.setPassword("password");
        return dataSource;
    }

    @Bean(name = "TBookShop")
    public TransactionalJdbcBookShop bookShop(){
        TransactionalJdbcBookShop shop = new TransactionalJdbcBookShop();
        shop.setDataSource(dataSource());
        shop.setTransactionManager(transactionManager());
        return shop;
    }

    @Bean
    public PlatformTransactionManager transactionManager(){
        DataSourceTransactionManager tm = new DataSourceTransactionManager();
        tm.setDataSource(dataSource());
        return tm;
    }
}
