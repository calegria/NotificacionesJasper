package com.bolivar.estudio.solicitud.jasper.controller;

import com.bolivar.estudio.solicitud.jasper.dto.RequestDto;
import com.bolivar.estudio.solicitud.jasper.dto.ResponseDto;
import com.bolivar.estudio.solicitud.jasper.service.SolicitudJasperService;
import com.google.gson.Gson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource({"classpath:test.yaml"})
class SolicitudJasperControllerTest
{
  @Autowired
  SolicitudJasperController solicitudJasperControllerTest;
  @Autowired
  SolicitudJasperService solicitudJasperService;
  
  @Value("${jasper.json.request1}")
  String request1;
  @Value("${jasper.json.response1}")
  String response1;
  @Value("${jasper.json.request2}")
  String request2;
  @Value("${jasper.json.response2}")
  String response2;
  @Value("${jasper.json.request3}")
  String request3;
  @Value("${jasper.json.response3}")  
  String response3;
  
  RequestDto requestDto;
  ResponseDto responseDto;
  String actual;
  String esperado;
  
  private final double seed = 0.7D;
  
  @Test
  void SolicitudJasperControllerTestCase1() {
    this.requestDto = (RequestDto)(new Gson()).fromJson(this.request1, RequestDto.class);
    this.responseDto = (ResponseDto)(new Gson()).fromJson(this.response1, ResponseDto.class);
    
    this.esperado = this.responseDto.getBindatoPDF();
    this.actual = this.solicitudJasperControllerTest.getBinPdf(this.requestDto).getBindatoPDF();
    
    int muestraBin = (int)(this.responseDto.getBindatoPDF().length() * 0.7D);
    Assertions.assertEquals(this.esperado.substring(0, muestraBin), this.actual.subSequence(0, muestraBin));
  }

  @Test
  void SolicitudJasperControllerTestCase2() {
    this.requestDto = (RequestDto)(new Gson()).fromJson(this.request2, RequestDto.class);
    this.responseDto = (ResponseDto)(new Gson()).fromJson(this.response2, ResponseDto.class);
    
    this.esperado = this.responseDto.getBindatoPDF();
    this.actual = this.solicitudJasperControllerTest.getBinPdf(this.requestDto).getBindatoPDF();
    
    int muestraBin = (int)(this.responseDto.getBindatoPDF().length() * 0.7D);
    Assertions.assertEquals(this.esperado.substring(0, muestraBin), this.actual.subSequence(0, muestraBin));
  }

  @Test
  void SolicitudJasperControllerTestCase3() {
    this.requestDto = (RequestDto)(new Gson()).fromJson(this.request3, RequestDto.class);
    this.responseDto = (ResponseDto)(new Gson()).fromJson(this.response3, ResponseDto.class);
    
    this.esperado = this.responseDto.getBindatoPDF();
    this.actual = this.solicitudJasperControllerTest.getBinPdf(this.requestDto).getBindatoPDF();
    
    int muestraBin = (int)(this.responseDto.getBindatoPDF().length() * 0.7D);
    Assertions.assertEquals(this.esperado.substring(0, muestraBin), this.actual.subSequence(0, muestraBin));
  }
}
