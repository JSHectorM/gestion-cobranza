package com.lf.gestioncobranza.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "fianzas")
public class Fianza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // ID de la fianza
    private String fianza;
    private String tipoFianza;
    private String movimiento;
    private String fiado;
    private String mail;


}
