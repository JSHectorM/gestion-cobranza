package com.lf.gestioncobranza.service;

import com.lf.gestioncobranza.dto.ResponseFianza;

import java.util.List;

public interface FianzaService {
    List<ResponseFianza> getAllFianzasOficina(Integer idOficina);
}
