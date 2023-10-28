package com.lf.gestioncobranza.service.impl;

import com.lf.gestioncobranza.dao.OficinaRepository;
import com.lf.gestioncobranza.model.Oficina;
import com.lf.gestioncobranza.service.OficinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OficinaServiceImpl implements OficinaService {

    @Autowired
    private OficinaRepository oficinaRepository;

    @Autowired
    public OficinaServiceImpl(OficinaRepository oficinaRepository) {
        this.oficinaRepository = oficinaRepository;
    }
    @Override
    public List<Oficina> getAllOficinas() {
        return oficinaRepository.findAll();
    }
}
