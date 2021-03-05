package com.segurosbolivar.libertador.notificaciones.jasper.dto;

import org.springframework.stereotype.Component;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Component
public class ResultadoSolicitud {
	
	private String tipo;
	private String identificacion;
	private String nombre;
	private String solicitud;
	private String resultado;

}
