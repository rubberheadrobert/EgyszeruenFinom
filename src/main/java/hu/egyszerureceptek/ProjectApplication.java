package hu.egyszerureceptek;

import hu.egyszerureceptek.controller.AppController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.io.File;

@SpringBootApplication
@ComponentScan({"hu.egyszerureceptek", "controller"})
public class ProjectApplication {



	public static void main(String[] args) {
		new File(AppController.uploadDirectory).mkdir();
		SpringApplication.run(ProjectApplication.class, args);
	}


}
