package com.segurosbolivar.libertador.notificaciones.service;

import java.util.Map;

import com.segurosbolivar.libertador.notificaciones.jasper.dto.SolicitudParametros;

import net.sf.jasperreports.engine.JasperPrint;


public interface ServiceJasperInterface { 
	
	public String genearReporte();
	
	public Map<String, Object> setParametros();
	
	public void addResultdoJasper();
	
	public SolicitudParametros getParametros() ;
	
	public void exportarPDF(JasperPrint jasperPrint);
}
