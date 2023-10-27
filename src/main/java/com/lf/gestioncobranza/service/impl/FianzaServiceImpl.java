package com.lf.gestioncobranza.service.impl;

import com.lf.gestioncobranza.dao.FianzaRepository;
import com.lf.gestioncobranza.model.Fianza;
import com.lf.gestioncobranza.service.FianzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FianzaServiceImpl implements FianzaService {
    @Autowired
    private FianzaRepository fianzaRepository;

    @Override
    public List<Fianza> listarFianzas() {
        return fianzaRepository.findAll();
    }

    @Override
    public Fianza obtenerFianzaPorId(Long id) {
        return fianzaRepository.findById(id).orElse(null);
    }

    @Override
    public List<Fianza> buscarFianzasPorTipo(String tipoFianza) {
        return fianzaRepository.findByTipoFianza(tipoFianza);
    }

    @Override
    public Fianza crearFianza(Fianza fianza) {
        return fianzaRepository.save(fianza);
    }

    @Override
    public Fianza actualizarFianza(Long id, Fianza fianza) {
        if (fianzaRepository.existsById(id)) {
            fianza.setId(id);
            return fianzaRepository.save(fianza);
        }
        return null;
    }

    @Override
    public void eliminarFianza(Long id) {
        if (fianzaRepository.existsById(id)) {
            fianzaRepository.deleteById(id);
        }
    }
}
