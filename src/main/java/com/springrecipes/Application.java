package com.springrecipes;

import com.springrecipes.bookshop.BookShop;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Application.class, args);

		BookShop shop = (BookShop) context.getBean("bookShop");
		shop.purchase("1", null);

	}
}

