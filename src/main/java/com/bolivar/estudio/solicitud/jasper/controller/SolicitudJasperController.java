package com.bolivar.estudio.solicitud.jasper.controller;

import com.bolivar.estudio.solicitud.jasper.dto.RequestDto;
import com.bolivar.estudio.solicitud.jasper.dto.ResponseDto;
import com.bolivar.estudio.solicitud.jasper.service.SolicitudJasperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SolicitudJasperController {
	@Autowired
	SolicitudJasperService serviceJasper;

	@GetMapping({ "${jasper.url}" })
	public ResponseDto getBinPdf(@Validated @RequestBody RequestDto request) {
		return ResponseDto.builder().bindatoPDF(this.serviceJasper.generarReporte(request)).build();
	}
}