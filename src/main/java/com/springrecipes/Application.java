package com.springrecipes;

import com.springrecipes.bookshop.Cashier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(Application.class, args);

		Cashier cashier = (Cashier) context.getBean("cashier");
		List<String> isbnList = Arrays.asList(new String[]{"0001", "0002"});
		cashier.checkout(isbnList, "user1");
	}
}

