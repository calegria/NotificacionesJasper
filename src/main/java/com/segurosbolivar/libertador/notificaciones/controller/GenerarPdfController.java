package com.segurosbolivar.libertador.notificaciones.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.segurosbolivar.libertador.notificaciones.dto.RequestDto;
import com.segurosbolivar.libertador.notificaciones.dto.ResponseDto;
import com.segurosbolivar.libertador.notificaciones.service.impl.ServiceJasperInterfaceImpl;

@RestController
public class GenerarPdfController {
	
	@Autowired
	ServiceJasperInterfaceImpl serviceJasper;
	
	@GetMapping("${url-pdf}")
	public ResponseDto getString(@Validated @RequestBody RequestDto request) {
		return ResponseDto.builder().bindatoPDF( serviceJasper.genearReporte(request) ).build();
	}

}
