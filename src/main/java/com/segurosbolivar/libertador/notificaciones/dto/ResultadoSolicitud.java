
package com.segurosbolivar.libertador.notificaciones.dto;

import lombok.Data;

@Data
public class ResultadoSolicitud{
    public String tipo;
    public String identificacion;
    public String nombre;
    public String solicitud;
    public String resultado;
}
