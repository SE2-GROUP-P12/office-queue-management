package it.polito.ezqueue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class EzqueueApplication {

	public static void main(String[] args)
	{
		SpringApplication.run(EzqueueApplication.class, args);
	}

}
