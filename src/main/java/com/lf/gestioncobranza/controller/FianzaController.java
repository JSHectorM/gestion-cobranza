package com.lf.gestioncobranza.controller;

import com.lf.gestioncobranza.dto.Response;
import com.lf.gestioncobranza.service.ImportDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/fianzas")
public class FianzaController {
    @Autowired()
    private ImportDataService transactionService;

    @GetMapping("/buscar")
    public Response buscarFianza(@RequestParam(name = "oficina", required = false) String nombreOficina,
                                 @RequestParam(name = "agente", required = false) String nombreAgente,
                                 @RequestParam(name = "ejecutiva", required = false) String nombreEjecutiva) {
        System.out.println("/fianzas/buscar invoked ---> nombreOficina = " + nombreOficina + ", nombreAgente = " + nombreAgente + ", nombreEjecutiva = " + nombreEjecutiva);
        Response response = new Response();

        response.setCode(HttpStatus.OK.toString());
        response.setMessage("Fianzas encontradas");
        return response;
    }

    @PostMapping("/uploadExcelFile")
    public Response uploadExcelFile(@RequestPart(required = true) MultipartFile file) {
        System.out.println("/fianzas/uploadExcelFile invoked ---> file ");

        try{
            transactionService.importExcelToDatabase(file);
        }catch (Exception e){
            e.printStackTrace();
        }
        Response response = new Response();
        response.setCode(HttpStatus.OK.toString());
        response.setMessage("Upload successful");
        return response;
    }

}
