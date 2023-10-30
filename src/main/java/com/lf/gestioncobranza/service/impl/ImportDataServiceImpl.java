package com.lf.gestioncobranza.service.impl;

import com.lf.gestioncobranza.constants.TipoMoneda;
import com.lf.gestioncobranza.dao.FianzaRepository;
import com.lf.gestioncobranza.dao.PagoPendienteRepository;
import com.lf.gestioncobranza.model.Fianza;
import com.lf.gestioncobranza.model.ImportData;
import com.lf.gestioncobranza.model.PagoPendiente;
import com.lf.gestioncobranza.service.ImportDataService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class ImportDataServiceImpl implements ImportDataService {

    @Autowired
    private FianzaRepository fianzaRepository;

    @Autowired
    private PagoPendienteRepository pagoPendienteRepository;

    /**
     * Método que importa los datos de un archivo Excel a la base de datos.
     *
     * @param file Archivo Excel a importar.
     */
    @Override
    public void importExcelToDatabase(MultipartFile file, String oficina) {
        if (!file.isEmpty()) {
            List<ImportData> importDataList = new ArrayList<>();
            try {
                XSSFWorkbook workBook = new XSSFWorkbook(file.getInputStream());
                Sheet sheet = workBook.getSheetAt(0); // Suponiendo que el archivo XLSX tiene una única hoja.
                for (Row row : sheet) {
                    if (row.getRowNum() == 0) {
                        continue;
                    }

                    ImportData importData = new ImportData();
                    System.out.println("row = " + row.getCell(0) + " | "
                            + row.getCell(1) + " | " + row.getCell(2) + " | "
                            + row.getCell(3) + " | " + row.getCell(4) + " | "
                            + row.getCell(5) + " | " + row.getCell(6) + " | "
                            + row.getCell(7) + " | " + row.getCell(8));
                    importData.setFianza(row.getCell(0).getStringCellValue());
                    importData.setTipoFianza(row.getCell(1).getStringCellValue());
                    importData.setMovimiento(row.getCell(2).getStringCellValue());
                    importData.setFiado(row.getCell(3).getStringCellValue());
                    importData.setMail(row.getCell(4).getStringCellValue());
                    importData.setFechaMovimiento(row.getCell(5).getDateCellValue());
                    importData.setFechaLimite(
                            stringToDate(
                            row.getCell(5).getDateCellValue(),
                            row.getCell(6).getCellFormula()));

                    importData.setImporte(BigDecimal.valueOf(row.getCell(7).getNumericCellValue()));
                    importData.setMoneda(row.getCell(8).getStringCellValue());
                    System.out.println("importData = " + importData);
                    importDataList.add(importData);
                }

                for (ImportData importData : importDataList) {
                    // Crear objeto Fianza

                    Fianza fianza = new Fianza();
                    fianza.setFianza(importData.getFianza());
                    fianza.setTipoFianza(importData.getTipoFianza());
                    fianza.setMovimiento(importData.getMovimiento());
                    fianza.setFiado(importData.getFiado());
                    fianza.setMail(importData.getMail());

                    fianzaRepository.save(fianza);

                    Long idFianzaGenerado = fianza.getId();

                    fianza.setId(idFianzaGenerado);

                    System.out.println("idFianzaGenerado = " + idFianzaGenerado);

                    // Crear objeto PagoPendiente
                    PagoPendiente pagoPendiente = new PagoPendiente();
                    pagoPendiente.setFechaMovimiento(importData.getFechaMovimiento());
                    pagoPendiente.setFechaLimite(importData.getFechaLimite());
                    pagoPendiente.setImporte(importData.getImporte());
                    pagoPendiente.setFianza(fianza);
                    pagoPendiente.setIdMoneda(obtenerValorMoneda(importData.getMoneda()));
                    pagoPendiente.setIdOficina((int) Long.parseLong(oficina));


                    System.out.println("pagoPendiente = " + pagoPendiente);

                    pagoPendienteRepository.save(pagoPendiente);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Método que convierte un String a Date.
     *
     * @param fechaMov Fecha de movimiento.
     * @param fechaLim Fecha límite.
     * @return Fecha límite.
     * @throws ParseException Excepción en caso de que el formato de la fecha no sea válido.
     */

    private Date stringToDate(Date fechaMov, String fechaLim) throws ParseException {
        String[] datePartsLim = fechaLim.split("\\+");
        if (datePartsLim.length != 2) {
            throw new ParseException("Formato de fecha no válido", 0);
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaMov);
        calendar.add(Calendar.DAY_OF_MONTH, Integer.parseInt(datePartsLim[1]));
        System.out.println("calendar.getTime() = " + calendar.getTime());
        return calendar.getTime();
    }

    /**
     * Método que obtiene el valor de una moneda.
     *
     * @param monedaStr Moneda en formato String.
     * @return Valor de la moneda.
     */
    private int obtenerValorMoneda(String monedaStr) {
        switch (monedaStr.toLowerCase()) {
            case "pesos":
                return TipoMoneda.PESOS.getValor();
            case "dolares":
                return TipoMoneda.DOLARES.getValor();
            case "euros":
                return TipoMoneda.EUROS.getValor();
            default:
                throw new IllegalArgumentException("Moneda desconocida: " + monedaStr);
        }
    }
}
