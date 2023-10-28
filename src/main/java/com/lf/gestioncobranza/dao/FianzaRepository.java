package com.lf.gestioncobranza.dao;

import com.lf.gestioncobranza.model.Fianza;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FianzaRepository extends JpaRepository<Fianza, Long> {

    List<Fianza> findByTipoFianza(String tipoFianza);
}
