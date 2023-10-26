package com.lf.gestioncobranza.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "fianzas")
public class Fianza {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; // ID de la fianza
    private String tipoFianza;
    private String movimiento;
    private String fiado;
    private String mail;
}
