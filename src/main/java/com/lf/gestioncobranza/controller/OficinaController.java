package com.lf.gestioncobranza.controller;

import com.lf.gestioncobranza.dto.Response;
import com.lf.gestioncobranza.model.Oficina;
import com.lf.gestioncobranza.service.OficinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/oficina")
public class OficinaController {

    private final OficinaService oficinaService;
    @Autowired
    public OficinaController(OficinaService oficinaService) {
        this.oficinaService = oficinaService;
    }


    @GetMapping("/all")
    public Response getAll() {
        System.out.println("/oficina/all invoked");
        Response response = new Response();
        List<Oficina> oficinas = oficinaService.getAllOficinas();
        System.out.println("oficinas = " + oficinas);
        response.setData(oficinas);
        response.setCode("200");
        response.setMessage("Oficinas encontradas");
        HttpHeaders headers = new HttpHeaders();

        return new ResponseEntity<>(response,headers, HttpStatus.OK).getBody();
    }

}
