package com.bolivar.estudio.solicitud.jasper.service;

import com.bolivar.error.handling.exception.BolivarBusinessException;
import com.bolivar.error.handling.model.ExceptionDetailModel;


import java.io.File;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.bolivar.estudio.solicitud.jasper.dto.ParametrosSolicitudDto;
import com.bolivar.estudio.solicitud.jasper.dto.RequestDto;
import com.bolivar.estudio.solicitud.jasper.dto.ResultadoSolicitudDto;
import com.bolivar.estudio.solicitud.jasper.util.SolicitudJasperUtil;

import lombok.Data;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

@Service
@Data
public class SolicitudJasperService {
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

	@Value("${jasper.resultado.dato1}")
	private String dato1;
	@Value("${jasper.resultado.dato2}")
	private String dato2;
	@Value("${jasper.resultado.dato3}")
	private String dato3;
	@Value("${jasper.resultado.dato4}")
	private String dato4;
	@Value("${jasper.resultado.dato5}")
	private String dato5;
	
	@Autowired
	Environment env;
	
	private SolicitudJasperUtil dataSource;
	private String pdfBindato;
	private Map<String, Object> parametrosJasper;
	
	public String generarReporte(RequestDto request) {
		
		this.dataSource = new SolicitudJasperUtil();
		this.parametrosJasper = new HashMap<>();

		try {
			File resource = Paths.get(this.pathJasper, new String[0]).toFile();
			JasperReport report = (JasperReport) JRLoader.loadObject(resource);

			this.dataSource.inicilizarParametrosJasper(this.dato1, this.dato2, this.dato3, this.dato4, this.dato5);
			addResultdoJasper(request.getSolicitudToPdf().getResultadoSolicitud());

			JasperPrint jprint = JasperFillManager.fillReport(report,
					setParametros(request.getSolicitudToPdf().getParametrosSolicitud()), this.dataSource);

			byte[] pdfBytes = Base64.getEncoder().encode(JasperExportManager.exportReportToPdf(jprint));
			this.pdfBindato = new String(pdfBytes);
			
		} catch (JRException e) {
			throw BolivarBusinessException.builder().codigo(this.env.getProperty("BolivarException.codigo"))
					.mensaje(this.env.getProperty("BolivarException.controller.mensaje"))
					.errores(Arrays.asList(new ExceptionDetailModel[] {
							ExceptionDetailModel.builder().descripcion(e.getLocalizedMessage()).build() }))
					.build();
		}

		return this.pdfBindato;
	}

	public Map<String, Object> setParametros(ParametrosSolicitudDto parametros) {
		this.parametrosJasper.put(this.inmobiliria, parametros.getInmobiliria());
		this.parametrosJasper.put(this.agencia, parametros.getAgencia());
		this.parametrosJasper.put(this.direccion, parametros.getDireccion());
		this.parametrosJasper.put(this.fechaResultado, parametros.getFechaResultado());
		this.parametrosJasper.put(this.canon, parametros.getCanon());
		this.parametrosJasper.put(this.admon, parametros.getAdmon());
		this.parametrosJasper.put(this.fecha, parametros.getFecha());

		return this.parametrosJasper;
	}

	public void addResultdoJasper(List<ResultadoSolicitudDto> resultados) {
		for (ResultadoSolicitudDto resultado : resultados)
			this.dataSource.addResultado(resultado);
	}
}
