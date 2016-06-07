package com.springrecipes.config;

import com.springrecipes.bookshop.BookShop;
import com.springrecipes.bookshop.JdbcBookShop;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

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
        dataSource.setUrl("jdbc:mysql://localhost:3306/BOOKSHOP");
        dataSource.setUsername("***");
        dataSource.setPassword("***");
        return dataSource;
    }

    @Bean
    public BookShop bookShop(){
        JdbcBookShop shop = new JdbcBookShop();
        shop.setDataSource(dataSource());
        return shop;
    }
}
