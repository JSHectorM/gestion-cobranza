package com.lf.gestioncobranza.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImportData {
    private String fianza;
    private String tipoFianza;
    private String movimiento;
    private String fiado;
    private String mail;
    private Date fechaMovimiento;
    private Date fechaLimite;
    private BigDecimal importe;
    private String Moneda;

}
