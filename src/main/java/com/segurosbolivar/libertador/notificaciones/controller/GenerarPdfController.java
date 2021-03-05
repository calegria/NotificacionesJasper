package com.segurosbolivar.libertador.notificaciones.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.segurosbolivar.libertador.notificaciones.dto.ResponseDto;
import com.segurosbolivar.libertador.notificaciones.service.impl.ServiceJasperInterfaceImpl;

@RestController
public class GenerarPdfController {
	
	@Autowired
	ServiceJasperInterfaceImpl serviceJasper;
	
	@GetMapping("${server.url}")
	public ResponseDto getString() {
		return ResponseDto.builder().bindatoPDF(serviceJasper.genearReporte() ).build();
	}

}
