package com.parimal.firstProject.SpringBootFirstProject;

import com.parimal.firstProject.SpringBootFirstProject.ClassesHub.Apple;
import com.parimal.firstProject.SpringBootFirstProject.ClassesHub.Banana;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.net.http.WebSocket;

@SpringBootApplication
public class SpringBootFirstProjectApplication implements CommandLineRunner {

	@Autowired		// notifies spring boot app when we want to inject a bean, wherever it is required.
	Apple a;

	@Autowired
	Banana b;

	@Autowired
	Banana b2;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootFirstProjectApplication.class, args);
	}

	@Override
	public void run(String[] args){
		a.eatApple();
		b.eatBanana();

		// as by default, only one bean is created irrespective of no.of objects created of the same class
		// to create multiple beans of the same class, use the @Scope("prototype"), in the AppConfig class.
		b2.eatBanana();
		// cross-checking by printing the hashCodes of both b and b2.
		System.out.println("HashCode of b: " + b.hashCode());
		System.out.println("HashCode of b2: " + b2.hashCode());


	}

}

