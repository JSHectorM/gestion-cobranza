package com.lf.gestioncobranza.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImportDataService {
    void importExcelToDatabase(MultipartFile file) throws IOException;
}
