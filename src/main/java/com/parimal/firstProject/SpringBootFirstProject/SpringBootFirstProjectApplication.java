package com.parimal.firstProject.SpringBootFirstProject;

import com.parimal.firstProject.SpringBootFirstProject.ClassesHub.Apple;
import com.parimal.firstProject.SpringBootFirstProject.ClassesHub.Banana;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class SpringBootFirstProjectApplication implements CommandLineRunner {

	@Autowired		// notifies spring boot app when we want to inject a bean, wherever it is required.
	Apple a;

	Banana b;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootFirstProjectApplication.class, args);
	}

	@Override
	public void run(String[] args){
		a.eatApple();
		b.eatBanana();
	}

}

