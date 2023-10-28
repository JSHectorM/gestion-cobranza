package com.lf.gestioncobranza.controller;

import com.lf.gestioncobranza.dto.Response;
import com.lf.gestioncobranza.service.FianzaService;
import com.lf.gestioncobranza.service.ImportDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/fianzas")
public class FianzaController {
    @Autowired
    private ImportDataService transactionService;

    @Autowired
    private FianzaService fianzaService;

    @GetMapping("/buscar/{idOficina}")
    public Response buscarFianza( @PathVariable Integer idOficina) {
        System.out.println("/fianzas/buscar/{idOficina} invoked ---> idOficina = " + idOficina);
        if (idOficina <= 0) {
            Response response = new Response();
            response.setCode(HttpStatus.BAD_REQUEST.toString());
            response.setMessage("Id oficina no es valido");
            return ResponseEntity.badRequest().body(response).getBody();
        }
        Response response = new Response();
        response.setData(fianzaService.getAllFianzasOficina(idOficina));
        response.setCode(HttpStatus.OK.toString());
        response.setMessage("Fianzas encontradas");
        return response;
    }

    @PostMapping("/uploadExcelFile/{idOficina}")
    public Response uploadExcelFile(@RequestPart(required = true) MultipartFile file, @PathVariable(required = true) String idOficina) {
        System.out.println("/fianzas/uploadExcelFile invoked ---> file ");
        if (file.isEmpty()) {
            Response response = new Response();
            response.setCode(HttpStatus.BAD_REQUEST.toString());
            response.setMessage("File is empty");
            return response;
        }
        if (Integer.parseInt(idOficina) <= 0) {
            Response response = new Response();
            response.setCode(HttpStatus.BAD_REQUEST.toString());
            response.setMessage("Id oficina no es valido");
            return response;
        }
            try{
                transactionService.importExcelToDatabase(file, idOficina);
            }catch (Exception e){
                e.printStackTrace();
            }
            Response response = new Response();
            response.setCode(HttpStatus.OK.toString());
            response.setMessage("Upload successful");
            HttpHeaders headers = new HttpHeaders();

        return new ResponseEntity<>(response,headers, HttpStatus.OK).getBody();
    }

}
