package com.segurosbolivar.libertador.notificaciones.controller;

import static org.mockito.Mockito.when;

import java.io.IOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import com.segurosbolivar.libertador.notificaciones.dto.ResponseDto;
import com.segurosbolivar.libertador.notificaciones.service.impl.ServiceJasperInterfaceImpl;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
@PropertySource
(value = {"classpath:test.yaml"})
class GenerarPdfControllerTest {
	
	@InjectMocks
	GenerarPdfController generarPdfController;
	
	@Mock
	ServiceJasperInterfaceImpl serviceJasper;
	
	@Value ("${bindatoPDF}")
	String bindatoPDF;

	@Test
	void GenerarPdfController_test1() {
		try {
			
			when(serviceJasper.genearReporte()).thenReturn(bindatoPDF);
			ResponseDto response = generarPdfController.getString();
			Assertions.assertEquals(bindatoPDF, response.getBindatoPDF());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
