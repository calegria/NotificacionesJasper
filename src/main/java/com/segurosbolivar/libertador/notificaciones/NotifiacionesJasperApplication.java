package com.segurosbolivar.libertador.notificaciones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource (value = {"classpath:application.yaml"})
public class NotifiacionesJasperApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotifiacionesJasperApplication.class, args);
	}

}
