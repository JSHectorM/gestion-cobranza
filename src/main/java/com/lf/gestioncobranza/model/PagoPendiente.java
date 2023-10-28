package com.lf.gestioncobranza.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pago_pendiente")
public class PagoPendiente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date fechaMovimiento;
    private Date fechaLimite;
    private BigDecimal importe;

    @ManyToOne // Muchos pagos pendientes pueden pertenecer a una fianza.
    @JoinColumn(name = "id_fianza")
    private Fianza fianza;

    private Integer idMoneda;
    private Integer idOficina;

}
