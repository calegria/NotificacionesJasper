package com.bolivar.estudio.solicitud.jasper.util;

import com.bolivar.estudio.solicitud.jasper.dto.ResultadoSolicitudDto;
import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;



public class SolicitudJasperUtil
  implements JRDataSource
{
  private List<ResultadoSolicitudDto> resultados = new ArrayList<>();
  private int index = -1;
  
  private String dato1;
  
  private String dato2;
  private String dato3;
  private String dato4;
  private String dato5;
  
  public boolean next() throws JRException {
    this.index++;
    return (this.index < this.resultados.size());
  }

  public Object getFieldValue(JRField jrField) throws JRException {
    Object resultado = null;
    String nombreCampo = jrField.getName();
    
    if (nombreCampo.equalsIgnoreCase(this.dato1)) {
      resultado = ((ResultadoSolicitudDto)this.resultados.get(this.index)).getTipo();
    }
    if (nombreCampo.equalsIgnoreCase(this.dato2)) {
      resultado = ((ResultadoSolicitudDto)this.resultados.get(this.index)).getIdentificacion();
    }
    if (nombreCampo.equalsIgnoreCase(this.dato3)) {
      resultado = ((ResultadoSolicitudDto)this.resultados.get(this.index)).getNombre();
    }
    if (nombreCampo.equalsIgnoreCase(this.dato4)) {
      resultado = ((ResultadoSolicitudDto)this.resultados.get(this.index)).getSolicitud();
    }
    if (nombreCampo.equalsIgnoreCase(this.dato5)) {
      resultado = ((ResultadoSolicitudDto)this.resultados.get(this.index)).getResultado();
    }
    return resultado;
  }

  
  public void addResultado(ResultadoSolicitudDto resultadoSolicitud) { 
	  this.resultados.add(resultadoSolicitud); 
  }
  
  public void inicilizarParametrosJasper(String datosP1, String datosP2, String datosP3, String datosP4, String datosP5) {
	    
	  this.dato1 = datosP1;
	  this.dato2 = datosP2;
	  this.dato3 = datosP3;
	  this.dato4 = datosP4;
	  this.dato5 = datosP5;
  }
}
