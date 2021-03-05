
package com.segurosbolivar.libertador.notificaciones.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParametrosSolicitud{
    public String inmobiliria;
    public String agencia;
    public String direccion;
    public String fechaResultado;
    public String canon;
    public String admon;
    public String fecha;
}
