package com.segurosbolivar.libertador.notificaciones.jasper.dto;


import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class SolicitudParametros {
	
	private String inmobiliaria;
	private String agencia;
	private String fechaResultado;
	private String canon;
	private String administracion;
	private String direccion;
	
}