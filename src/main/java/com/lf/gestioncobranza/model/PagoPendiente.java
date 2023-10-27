package com.lf.gestioncobranza.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "pago_pendiente")
public class PagoPendiente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date fechaMovimiento;
    private Date fechaLimite;
    private BigDecimal importe;

    @ManyToOne
    @JoinColumn(name = "id_fianza")
    private Fianza fianza;

    public PagoPendiente() {
    }
    public PagoPendiente(Long id, Date fechaMovimiento, Date fechaLimite, BigDecimal importe, Fianza fianza) {
        this.id = id;
        this.fechaMovimiento = fechaMovimiento;
        this.fechaLimite = fechaLimite;
        this.importe = importe;
        this.fianza = fianza;
    }
}
