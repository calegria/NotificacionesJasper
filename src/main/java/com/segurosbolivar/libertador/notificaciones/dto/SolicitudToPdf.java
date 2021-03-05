
package com.segurosbolivar.libertador.notificaciones.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SolicitudToPdf{
    public ParametrosSolicitud parametrosSolicitud;
    public List<ResultadoSolicitud> resultadoSolicitud;
}
