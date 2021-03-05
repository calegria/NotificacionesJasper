package com.segurosbolivar.libertador.notificaciones.service.impl;

import java.io.File;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.segurosbolivar.libertador.notificaciones.dto.ParametrosSolicitud;
import com.segurosbolivar.libertador.notificaciones.dto.RequestDto;
import com.segurosbolivar.libertador.notificaciones.dto.ResultadoSolicitud;
import com.segurosbolivar.libertador.notificaciones.jasper.SolicitudJasper;
import com.segurosbolivar.libertador.notificaciones.service.ServiceJasperInterface;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;

@Data
@Slf4j
@NoArgsConstructor
@Service
public class ServiceJasperInterfaceImpl implements ServiceJasperInterface {
	
	@Value("${jasper.pathJasperSolicitud}")
	private String pathJasper;
	
	@Value("${jasper.parametros.inmobiliria}")
	private String inmobiliria;
	@Value("${jasper.parametros.agencia}")
	private String agencia;
	@Value("${jasper.parametros.direccion}")
	private String direccion;
	@Value("${jasper.parametros.fechaResultado}")
	private String fechaResultado;
	@Value("${jasper.parametros.canon}")
	private String canon;
	@Value("${jasper.parametros.admon}")
	private String admon;
	@Value("${jasper.parametros.fecha}")
	private String fecha;
	
	@Value ("${jasper.resultado.dato1}")
	String dato1;
	@Value ("${jasper.resultado.dato2}")
	String dato2;
	@Value ("${jasper.resultado.dato3}")
	String dato3;
	@Value ("${jasper.resultado.dato4}")
	String dato4;
	@Value ("${jasper.resultado.dato5}")
	String dato5;
	
	SolicitudJasper dataSource;
	String  pdfBindato;
	Map<String, Object> parametrosJasper;
	
	/*
	 * Genera reporte bindato/pdf desde plantilla jasper
	 * */
	@Override
	public String generarReporte(RequestDto request) {

		 dataSource = new SolicitudJasper();
		 parametrosJasper = new HashMap<>();
		
		try {
		
			File resource = Paths.get(pathJasper).toFile();
			JasperReport report = (JasperReport) JRLoader.loadObject(resource);
			
			this.dataSource.inicilizarParametrosJasper(dato1, dato2, dato3, dato4, dato5);
			this.addResultdoJasper(request.getSolicitudToPdf().getResultadoSolicitud());
			
            JasperPrint jprint = JasperFillManager.fillReport(
            		report, this.setParametros(request.getSolicitudToPdf().getParametrosSolicitud()),
            		this.dataSource);
            
            this.exportarPDF(jprint);
            
            byte[] pdfBytes =  Base64.getEncoder().encode(JasperExportManager.exportReportToPdf(jprint));
            pdfBindato = new String(pdfBytes);
            
		} catch (JRException  e) {
			log.info("JRException Error: "+e.getMessage());
		}
		
		return pdfBindato;
	}
	/*
	 * Setea Parametros del Jasper
	 * */
	@Override
	public Map<String, Object> setParametros(ParametrosSolicitud parametros) {
		
		parametrosJasper.put(inmobiliria, parametros.getInmobiliria());
		parametrosJasper.put(agencia,parametros.getAgencia());
		parametrosJasper.put(direccion,parametros.getDireccion());
		parametrosJasper.put(fechaResultado,parametros.getFechaResultado() );
		parametrosJasper.put(canon,parametros.getCanon());
		parametrosJasper.put(admon,parametros.getAdmon());
		parametrosJasper.put(fecha,parametros.getFecha());
		
		return parametrosJasper;
	}
	/*
	 * Setea los resultados
	 * */
	@Override
	public void addResultdoJasper(List<ResultadoSolicitud> resultados) {
		
		for (ResultadoSolicitud resultado: resultados) {
			this.dataSource.addResultado(resultado);
		}
		
	}
	/*
	 * Exportar PDF
	 * */
	@Override
	public void exportarPDF(JasperPrint jasperPrint) {
		JRExporter exporter = new JRPdfExporter();  
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint); 
        exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File("Solicitud.pdf")); 
        try {
			exporter.exportReport();
		} catch (JRException e) {
			e.printStackTrace();
		} 
		
	}

}
