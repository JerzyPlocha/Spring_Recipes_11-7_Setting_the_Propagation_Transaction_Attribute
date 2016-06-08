package com.springrecipes;

import com.springrecipes.bookshop.TransactionalJdbcBookShop;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(Application.class, args);

		TransactionalJdbcBookShop shop = (TransactionalJdbcBookShop) context.getBean("TBookShop");
		shop.purchase("0001", "user1");
	}
}

