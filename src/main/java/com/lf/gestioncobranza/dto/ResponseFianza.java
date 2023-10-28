package com.lf.gestioncobranza.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseFianza {
    private Long id_fianza;
    private String fianza;
    private String movimiento;
    private String fiado;
    private Long id_pago_pendiente;
    private String fecha_movimiento;
    private String fecha_limite;
    private String importe;
}
