package com.segurosbolivar.libertador.notificaciones.service.impl;

import java.io.File;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.segurosbolivar.libertador.notificaciones.jasper.SolicitudJasper;
import com.segurosbolivar.libertador.notificaciones.jasper.dto.ResultadoSolicitud;
import com.segurosbolivar.libertador.notificaciones.jasper.dto.SolicitudParametros;
import com.segurosbolivar.libertador.notificaciones.service.ServiceJasperInterface;

import lombok.Data;
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
	
	SolicitudJasper dataSource = new SolicitudJasper();
	SolicitudParametros parametros;
	String  pdfBindato;
	Map<String, Object> parametrosJasper = new HashMap<String,Object>();
	
	/*
	 * Genera reporte bindato/pdf desde plantilla jasper
	 * */
	@Override
	public String genearReporte() {
		log.info("Path jasper: "+pathJasper);	
		
		try {
		
			File resource = Paths.get(pathJasper).toFile();
			JasperReport report = (JasperReport) JRLoader.loadObject(resource);
			
			this.dataSource.inicilizarParametrosJasper(dato1, dato2, dato3, dato4, dato5);
			this.addResultdoJasper();
			
            JasperPrint jprint = JasperFillManager.fillReport(report, this.setParametros(), this.dataSource);
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
	public Map<String, Object> setParametros() {
		
		parametrosJasper.put(inmobiliria, this.getParametros().getInmobiliaria());
		parametrosJasper.put(agencia,this.getParametros().getAgencia());
		parametrosJasper.put(direccion,this.getParametros().getDireccion());
		parametrosJasper.put(fechaResultado,this.getParametros().getFechaResultado() );
		parametrosJasper.put(canon,this.getParametros().getCanon());
		parametrosJasper.put(admon,this.getParametros().getAdministracion());
		parametrosJasper.put(fecha,this.getParametros().getFechaResultado());
		
		return parametrosJasper;
	}
	/*
	 * Simula un resultado
	 * */
	@Override
	public void addResultdoJasper() {
		ResultadoSolicitud resultado = new ResultadoSolicitud();
		
		// Inquilino
		resultado.setIdentificacion("1.063.807.515");
		resultado.setNombre("Carlos Alegria");
		resultado.setTipo("INQUILINO");
		resultado.setSolicitud("674528");
		resultado.setResultado("RECHAZADO.\r\n" + "PRESENTE DEUDOR ADICIONAL CON INGRESOS\r\n"
				+ "DEMOSTRADOS POR EL DOBLE DEL CANON DE\r\n" + "ARRENDAMIENTO.\r\n" + "O LMI DE 6 MESES.");
		this.dataSource.addResultado(resultado);
		
		//Deudor Solidario
		resultado = new ResultadoSolicitud();
		resultado.setIdentificacion("1.003.807.667");
		resultado.setNombre("Filomeno Morris");
		resultado.setTipo("DEUDOR SOLIDARIO");
		resultado.setSolicitud("674529");
		resultado.setResultado("RECHAZADO.\r\n" + "PRESENTE DEUDOR ADICIONAL CON INGRESOS\r\n"
				+ "MENORES AL DOBLE DEL CANON DE\r\n" + "ARRENDAMIENTO.\r\n" + "O LMI DE 6 MESES.");
		this.dataSource.addResultado(resultado);
		
	}
	/*
	 * Simula los parametros de la solicitud
	 * */
	@Override
	public SolicitudParametros getParametros() {
		SimpleDateFormat fecha = new SimpleDateFormat("yyyy/MM/dd");
		String fechaResultado = fecha.format(new Date());

		parametros = new SolicitudParametros();

		parametros.setAgencia("10044");
		parametros.setInmobiliaria("CONTACTO INMOBILIARIO CIA Y LTDA");
		parametros.setCanon("$500.000");
		parametros.setFechaResultado(fechaResultado);
		parametros.setDireccion("DG 28C 42C 75");
		parametros.setAdministracion("$0");

		return parametros;
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
