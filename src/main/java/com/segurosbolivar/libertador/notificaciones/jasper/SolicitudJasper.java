
package com.segurosbolivar.libertador.notificaciones.jasper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.segurosbolivar.libertador.notificaciones.jasper.dto.ResultadoSolicitud;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

@Data
@Slf4j
@Component
public class SolicitudJasper implements JRDataSource{
	
	private List<ResultadoSolicitud> resultados = new ArrayList<ResultadoSolicitud>();
	private int index = -1;
	
	@Override
	public boolean next() throws JRException {
		this.index++;
		return (index < this.resultados.size() );
	}
	
	@Override
	public Object getFieldValue(JRField jrField) throws JRException {
		
		Object resultado = null;
		String nombreCampo = jrField.getName();
		//log.info("Pintando resultado: ["+index+"] - "+nombreCampo);
		
		switch (nombreCampo) {
			case "dato1":
				resultado = resultados.get(index).getTipo();
				break;
			case "dato2":
				resultado = resultados.get(index).getIdentificacion();
				break;
			case "dato3":
				resultado = resultados.get(index).getNombre();
				break;
			case "dato4":
				resultado = resultados.get(index).getSolicitud();
				break;
			case "dato5":
				resultado = resultados.get(index).getResultado();
				break;
		}
		 return resultado;
	}
	
	public void addResultado(ResultadoSolicitud resultadoSolicitud) {
		this.resultados.add(resultadoSolicitud);
		
	}
	
	
}
