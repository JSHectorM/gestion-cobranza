package com.lf.gestioncobranza.service.impl;

import com.lf.gestioncobranza.dto.ResponseFianza;
import com.lf.gestioncobranza.service.FianzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FianzaServiceImpl implements FianzaService {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public FianzaServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<ResponseFianza> getAllFianzasOficina(Integer idOficina) {
        String sql = "SELECT\n" +
                "    f.id_fianza,\n" +
                "    f.fianza,\n" +
                "    f.movimiento,\n" +
                "    f.fiado,\n" +
                "    pp.id_pago_pendiente,\n" +
                "    pp.fecha_movimiento,\n" +
                "    pp.fecha_limite,\n" +
                "    pp.importe,\n" +
                "    pp.id_moneda\n" +
                "FROM\n" +
                "    fianzas AS f\n" +
                "INNER JOIN\n" +
                "    pago_pendiente AS pp\n" +
                "ON\n" +
                "    f.id_fianza = pp.id_fianza\n" +
                "Where pp.id_oficina =" + idOficina;
                return jdbcTemplate.query(sql, (rs, rowNum) -> new ResponseFianza(
                rs.getLong("id_fianza"),
                rs.getString("fianza"),
                rs.getString("movimiento"),
                rs.getString("fiado"),
                rs.getLong("id_pago_pendiente"),
                rs.getString("fecha_movimiento"),
                rs.getString("fecha_limite"),
                rs.getString("importe"),
                rs.getInt("id_moneda")
        ));


    }
}
