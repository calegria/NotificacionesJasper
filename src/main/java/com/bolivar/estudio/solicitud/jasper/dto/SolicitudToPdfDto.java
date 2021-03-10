
package com.bolivar.estudio.solicitud.jasper.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SolicitudToPdfDto{
    public ParametrosSolicitudDto parametrosSolicitud;
    public List<ResultadoSolicitudDto> resultadoSolicitud;
}
