package com.bolivar.estudio.solicitud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;


@SpringBootApplication
@PropertySource({"classpath:application.yaml"})
public class ResultadoEstudioApplication
{
  public static void main(String[] args) { SpringApplication.run(ResultadoEstudioApplication.class, args); }
}
