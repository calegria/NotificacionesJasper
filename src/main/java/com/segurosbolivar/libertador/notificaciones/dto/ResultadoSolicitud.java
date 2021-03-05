
package com.segurosbolivar.libertador.notificaciones.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResultadoSolicitud{
    public String tipo;
    public String identificacion;
    public String nombre;
    public String solicitud;
    public String resultado;
}
