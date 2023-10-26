package com.lf.gestioncobranza.controller;

import com.lf.gestioncobranza.dao.FianzaRepository;
import com.lf.gestioncobranza.dto.Response;
import com.lf.gestioncobranza.model.Fianza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/fianzas")
public class FianzaController {
    @Autowired
    private FianzaRepository fianzaRepository;

    @GetMapping("/buscar")
    public Response buscarFianza(@RequestParam(name = "oficina", required = false) String nombreOficina,
                                 @RequestParam(name = "agente", required = false) String nombreAgente,
                                 @RequestParam(name = "ejecutiva", required = false) String nombreEjecutiva) {
        System.out.println("/fianzas/buscar invoked ---> nombreOficina = " + nombreOficina + ", nombreAgente = " + nombreAgente + ", nombreEjecutiva = " + nombreEjecutiva);
        Response response = new Response();

        return response;
    }

}
