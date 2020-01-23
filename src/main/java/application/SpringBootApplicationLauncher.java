package application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import controller.SpringBootController;

@SpringBootApplication
public class SpringBootApplicationLauncher {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootController.class, args);
	}
}