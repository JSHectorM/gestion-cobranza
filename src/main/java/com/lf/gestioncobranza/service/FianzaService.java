package com.lf.gestioncobranza.service;

import com.lf.gestioncobranza.model.Fianza;

import java.util.List;

public interface FianzaService {
    List<Fianza> listarFianzas();
    Fianza obtenerFianzaPorId(Long id);
    List<Fianza> buscarFianzasPorTipo(String tipoFianza);
    Fianza crearFianza(Fianza fianza);
    Fianza actualizarFianza(Long id, Fianza fianza);
    void eliminarFianza(Long id);
}
