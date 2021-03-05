package com.segurosbolivar.libertador.notificaciones.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.segurosbolivar.libertador.notificaciones.dto.ResponseDto;
import com.segurosbolivar.libertador.notificaciones.service.impl.ServiceJasperInterfaceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class GenerarPdfController {
	
	@Autowired
	ServiceJasperInterfaceImpl serviceJasper;
	
	@GetMapping("/string")
	public ResponseDto getString() throws IOException {
		log.info("ResponseDto getString() - PdfJasper - getJasper()");
		return ResponseDto.builder().bindatoPDF(serviceJasper.genearReporte() ).build();
	}

}
