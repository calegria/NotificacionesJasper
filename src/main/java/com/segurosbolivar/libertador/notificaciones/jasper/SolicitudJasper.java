
package com.segurosbolivar.libertador.notificaciones.jasper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.segurosbolivar.libertador.notificaciones.dto.ResultadoSolicitud;

import lombok.Data;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

@Data
@Component
public class SolicitudJasper implements JRDataSource{
	
	private List<ResultadoSolicitud> resultados = new ArrayList<ResultadoSolicitud>();
	private int index = -1;
	
	private String dato1;
	private String dato2;
	private String dato3;
	private String dato4;
	private String dato5;
	
	@Override
	public boolean next() throws JRException {
		this.index++;
		return (index < this.resultados.size() );
	}
	
	@Override
	public Object getFieldValue(JRField jrField) throws JRException {
		
		Object resultado = null;
		String nombreCampo = jrField.getName();
		
		if (nombreCampo.equalsIgnoreCase(dato1))
			resultado = resultados.get(index).getTipo();
			
		if (nombreCampo.equalsIgnoreCase(dato2)) 
			resultado = resultados.get(index).getIdentificacion();
		
		if (nombreCampo.equalsIgnoreCase(dato3)) 
			resultado = resultados.get(index).getNombre();
		
		if (nombreCampo.equalsIgnoreCase(dato4)) 
			resultado = resultados.get(index).getSolicitud();
		
		if (nombreCampo.equalsIgnoreCase(dato5)) 
			resultado = resultados.get(index).getResultado();
		
		 return resultado;
	}
	
	public void addResultado(ResultadoSolicitud resultadoSolicitud) {
		this.resultados.add(resultadoSolicitud);
		
	}
	
	public void inicilizarParametrosJasper(String datosP1, String datosP2, String datosP3, String datosP4, String datosP5) {
		dato1 = datosP1;
		dato2 = datosP2;
		dato3 = datosP3;
		dato4 = datosP4;
		dato5 = datosP5;
	}
	
	
}
