package com.parimal.firstProject.SpringBootFirstProject;

import com.parimal.firstProject.SpringBootFirstProject.ClassesHub.Apple;
import com.parimal.firstProject.SpringBootFirstProject.ClassesHub.Banana;
import com.parimal.firstProject.SpringBootFirstProject.SpringBoot_MVC_And_RestAPIs.services.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication()
public class SpringBootFirstProjectApplication implements CommandLineRunner {

	@Autowired		// notifies spring boot app when we want to inject a bean, wherever it is required.
	Apple a;

	@Autowired
	Banana b;

	@Autowired
	Banana b2;

	private SampleService service;


	public static void main(String[] args) {
		SpringApplication.run(SpringBootFirstProjectApplication.class, args);
	}

	@Override
	public void run(String[] args){

//		a.eatApple();
//		b.eatBanana();
//
//		// as by default, only one bean is created irrespective of no.of objects created of the same class
//		// to create multiple beans of the same class, use the @Scope("prototype"), in the AppConfig class.
//		b2.eatBanana();
//		// cross-checking by printing the hashCodes of both b and b2.
//		System.out.println("HashCode of b: " + b.hashCode());
//		System.out.println("HashCode of b2: " + b2.hashCode());

		// loosely coupled system demo.
//		System.out.println("The fetched data is: " + dbService.getData());

		// SpringBoot MVC and REST APIs

		// Now, installing the MySQL and DBeaver for real database setup.

		/*
			Using the concept of 'Profiles' in spring and the @Profile annotation.
			Commands:
					1) SPRING_PROFILES_ACTIVE=prod ./mvnw spring-boot:run		replace the 'prod' by your profile name.
					2) ./mvnw spring-boot:run -Dspring-boot.run.profiles=dev	replace the 'dev' by your profile name.

			You can also create separate .properties files for your profiles.
				e.g. application-dev.properties or application-prod.properties
			You can also create the configurations in the IDE with the environment variables in it.
		 */


		/*	 Now, Deployment of project.
			Steps:
				1. Configure the Elastic Beanstalk in the AWS.
				2. Create the HealthCheckController in the controllers package.
				3. Change the Java version in pom.xml from 22 to 21 as the elastic beanstalk supports the 21 version.
				4. Configure the database information in the application.properties file so that to deploy on cloud.
				5. In the setting of the IDE, 'Build, Execution, Deployment / Compiler / Java Compiler' make the
					target bytecode version to 21 so that the jar file after project building will be compatible to deploy
					and also due to step no.3.
				6. Press 'Command + ;' to go to the project settings.
				7. In it, change the SDK to corretto-21, due to step 3 & 5 and Language level to '21 - Record patterns,
				   pattern matching for switch' ,as we are going to deploy oon AWS.
				8. Now, in the 'M' icon, right to the Intellij IDEA editor, click the icon.
				9. Inside the project folder, go to Lifecycle, double click 'clean' and 'compile' option
				10. Finally, double-click on the 'package' and hence the .jar file will be created in the 'target' folder.
				11. This .jar file is supposed to upload as a project to deploy.
				12. But before deployment just make sure that the environment variables you created on local machine
					configuration are mentioned in the environment variable section
					in the cloud project environment(basically in the configuration of the cloud project).
				13. Also, add/configure the inbound rules of the security group, as only one rule is there which represents
				 	our ip address to connect to database,
					hence, after deployment the server on which our project will be running won't able to access the database.
					Hence, add the inbound rule for the server to access our cloud database for working of our spring boot project.
		 */
		/*		Now, Setup Code Pipeline in AWS.
					Information about Code Pipeline:
						AWS CodePipeline is a fully managed continuous delivery service that helps you automate the release
						pipelines for your applications and infrastructure updates. It allows you to model, visualize, and
						automate the steps required to release your software changes continuously.
						Basically, when you link the GitHub repo with it, it auto-updates the project it consists and reflects
						the changes in the running project on the cloud(elastic beanstalk).

				Steps:
					add yml file.
		 */


		/*
				Testing:
					We use the JUnit framework for writing out tests.
					And for better Assertions we use the AssertJ library.
		 */

		/*
							Aspect Oriented Programming.

				Aspect: A class that defines the cross-cutting concern. It contains advice and pointcuts.

				Advice: The action taken by the aspect at a particular join point. Types of advice include:
					Before advice: Executes before the join point.
					After advice: Executes after the join point, regardless of whether the join point completes normally
						or throws an exception.
				After returning advice: Executes after the join point completes normally.

				After throwing advice: Executes after the join point throws an exception.

				Around advice: Surrounds the join point and can control the execution of the join point.

				Join point: A point in the execution of the application where the advice can be applied.
					Examples of join points include method calls, constructor calls, and field access.
				Pointcut: A predicate that defines the set of join points that an advice should be applied to.
				 	Pointcuts are typically expressed using AspectJ pointcut expressions.

		 */




	}

}

