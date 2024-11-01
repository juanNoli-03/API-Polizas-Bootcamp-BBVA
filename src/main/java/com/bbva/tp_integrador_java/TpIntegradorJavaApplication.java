package com.bbva.tp_integrador_java;

import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableCaching
public class TpIntegradorJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TpIntegradorJavaApplication.class, args);
	}

	//Agregamos Bean para que nos avise por consola que efectivamente la aplicacion se inicio con éxito.
	@Bean
	public CommandLineRunner commandLineRunner (ApplicationContext ctx) {

		return args -> {
			System.out.printf("Inició la aplicacion con exito");
		};
	}
}
