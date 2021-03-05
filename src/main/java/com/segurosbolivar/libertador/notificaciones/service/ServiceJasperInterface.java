package com.segurosbolivar.libertador.notificaciones.service;

import java.util.List;
import java.util.Map;

import com.segurosbolivar.libertador.notificaciones.dto.ParametrosSolicitud;
import com.segurosbolivar.libertador.notificaciones.dto.RequestDto;
import com.segurosbolivar.libertador.notificaciones.dto.ResultadoSolicitud;

import net.sf.jasperreports.engine.JasperPrint;


public interface ServiceJasperInterface { 
	
	public String genearReporte(RequestDto request);
	
	public Map<String, Object> setParametros(ParametrosSolicitud parametros);
	
	public void addResultdoJasper(List<ResultadoSolicitud> resultados);
	
	public void exportarPDF(JasperPrint jasperPrint);
}
