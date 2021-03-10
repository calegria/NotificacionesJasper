package com.bolivar.estudio.solicitud.jasper.service;

import com.bolivar.error.handling.exception.BolivarBusinessException;
import com.bolivar.error.handling.model.ExceptionDetailModel;
import com.bolivar.estudio.solicitud.jasper.dto.ParametrosSolicitudDto;
import com.bolivar.estudio.solicitud.jasper.dto.RequestDto;
import com.bolivar.estudio.solicitud.jasper.util.SolicitudJasperUtil;
import com.google.gson.Gson;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource({ "classpath:test.yaml", "classpath:application.yaml" })
class SolicitudJasperServiceTest {
	@Value("${jasper.service.object1}")
	String requestObject1;
	@Value("${jasper.service.binpdf1}")
	String binPdf1;
	@Value("${jasper.pathJasperSolicitud}")
	String pathJasper;
	@Value("${jasper.parametros.inmobiliria}")
	String inmobiliria;
	@Value("${jasper.parametros.agencia}")
	String agencia;
	@Value("${jasper.parametros.direccion}")
	String direccion;
	@Value("${jasper.parametros.fechaResultado}")
	String fechaResultado;
	@Value("${jasper.parametros.canon}")
	String canon;
	@Value("${jasper.parametros.admon}")
	String admon;
	@Value("${jasper.parametros.fecha}")
	String fecha;
	@Value("${jasper.resultado.dato1}")
	String dato1;
	@Value("${jasper.resultado.dato2}")
	String dato2;
	@Value("${jasper.resultado.dato3}")
	String dato3;
	@Value("${jasper.resultado.dato4}")
	String dato4;
	@Value("${jasper.resultado.dato5}")
	String dato5;

	SolicitudJasperUtil dataSource = new SolicitudJasperUtil();
	String pdfBindato = "";
	Map<String, Object> parametrosJasper = new HashMap();

	@Autowired
	SolicitudJasperService solicitudJasperService;

	@Autowired
	Environment env;
	RequestDto request;
	String esperado;
	String actual;

	private final double seed = 0.7D;

	@Test
	void generarReporteTestCase() {
		this.request = new RequestDto();
		this.request = (RequestDto) (new Gson()).fromJson(this.requestObject1, RequestDto.class);
		int muestraBin = (int) (this.binPdf1.length() * 0.7D);

		this.esperado = this.binPdf1.substring(0, muestraBin);
		this.actual = this.solicitudJasperService.generarReporte(this.request);

		Assertions.assertEquals(this.esperado, this.actual.subSequence(0, muestraBin));
	}

	@Test
	void setParametrosTestCase() {
		this.request = new RequestDto();
		this.request = (RequestDto) (new Gson()).fromJson(this.requestObject1, RequestDto.class);

		ParametrosSolicitudDto parametrosSolicitudDto = new ParametrosSolicitudDto();
		parametrosSolicitudDto = this.request.solicitudToPdf.getParametrosSolicitud();

		this.parametrosJasper = new HashMap();
		this.dataSource = new SolicitudJasperUtil();

		this.solicitudJasperService.setParametrosJasper(this.parametrosJasper);
		this.parametrosJasper = this.solicitudJasperService.setParametros(parametrosSolicitudDto);

		Assertions.assertEquals(parametrosSolicitudDto.getInmobiliria(), this.parametrosJasper.get(this.inmobiliria));
		Assertions.assertEquals(parametrosSolicitudDto.getAgencia(), this.parametrosJasper.get(this.agencia));
		Assertions.assertEquals(parametrosSolicitudDto.getDireccion(), this.parametrosJasper.get(this.direccion));
		Assertions.assertEquals(parametrosSolicitudDto.getFechaResultado(),
				this.parametrosJasper.get(this.fechaResultado));
		Assertions.assertEquals(parametrosSolicitudDto.getCanon(), this.parametrosJasper.get(this.canon));
		Assertions.assertEquals(parametrosSolicitudDto.getAdmon(), this.parametrosJasper.get(this.admon));
		Assertions.assertEquals(parametrosSolicitudDto.getFecha(), this.parametrosJasper.get(this.fecha));
	}

	@Test
	void exceptionBolivarTestCase() {
		try {
			setterGetterTestCase();
			throw new JRException(this.env.getProperty("BolivarException.errores.error1"));
		} catch (JRException e) {

			BolivarBusinessException exception = BolivarBusinessException.builder()
					.codigo(this.env.getProperty("BolivarException.codigo"))
					.mensaje(this.env.getProperty("BolivarException.controller.mensaje"))
					.errores(Arrays.asList(new ExceptionDetailModel[] {
							ExceptionDetailModel.builder().descripcion(e.getMessage()).build() }))
					.build();

			Assertions.assertEquals(this.env.getProperty("BolivarException.codigo"), exception.getCodigo());
			Assertions.assertEquals(this.env.getProperty("BolivarException.controller.mensaje"),
					exception.getMensaje());
			Assertions.assertEquals(this.env.getProperty("BolivarException.errores.error1"),
					((ExceptionDetailModel) exception.getErrores().get(0)).getDescripcion());
			return;
		}
	}

	void setterGetterTestCase() {
		this.solicitudJasperService.setParametrosJasper(this.parametrosJasper);
		this.solicitudJasperService.setDataSource(this.dataSource);
		this.solicitudJasperService.setPdfBindato(this.pdfBindato);
		this.solicitudJasperService.setPathJasper(this.pathJasper);

		this.solicitudJasperService.setInmobiliria(this.inmobiliria);
		this.solicitudJasperService.setAgencia(this.agencia);
		this.solicitudJasperService.setCanon(this.canon);
		this.solicitudJasperService.setFecha(this.fecha);
		this.solicitudJasperService.setFechaResultado(this.fechaResultado);
		this.solicitudJasperService.setDireccion(this.direccion);

		this.solicitudJasperService.setDato1(this.dato1);
		this.solicitudJasperService.setDato2(this.dato2);
		this.solicitudJasperService.setDato3(this.dato3);
		this.solicitudJasperService.setDato4(this.dato4);
		this.solicitudJasperService.setDato5(this.dato5);

		this.solicitudJasperService.getParametrosJasper();
		this.solicitudJasperService.getDataSource();
		this.solicitudJasperService.getPdfBindato();
		this.solicitudJasperService.getPathJasper();

		this.solicitudJasperService.getInmobiliria();
		this.solicitudJasperService.getAgencia();
		this.solicitudJasperService.getCanon();
		this.solicitudJasperService.getFecha();
		this.solicitudJasperService.getFechaResultado();
		this.solicitudJasperService.getDireccion();

		this.solicitudJasperService.getDato1();
		this.solicitudJasperService.getDato2();
		this.solicitudJasperService.getDato3();
		this.solicitudJasperService.getDato4();
		this.solicitudJasperService.getDato5();
	}
}
