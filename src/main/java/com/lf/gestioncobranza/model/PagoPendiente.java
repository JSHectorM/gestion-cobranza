package com.lf.gestioncobranza.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "pago_pendiente")
public class PagoPendiente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String fechaMovimiento;
    private String fechaLimite;
    private BigDecimal importe;

    @ManyToOne
    @JoinColumn(name = "id_fianza")
    private Fianza fianza;
}
