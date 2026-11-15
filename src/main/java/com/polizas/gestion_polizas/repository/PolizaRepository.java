package com.polizas.gestion_polizas.repository;

import com.polizas.gestion_polizas.model.Poliza;
import com.polizas.gestion_polizas.model.TipoPoliza;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class PolizaRepository {
    private static final Map<Long, Poliza> polizasDB = new HashMap<>();

    static {
        polizasDB.put(1L, new Poliza(1L, TipoPoliza.INDIVIDUAL, "ACTIVA", 1000000.0, 12000000.0));
        polizasDB.put(2L, new Poliza(2L, TipoPoliza.COLECTIVA, "ACTIVA", 2500000.0, 30000000.0));
    }

    public List<Poliza> findByTipoAndEstado(TipoPoliza tipo, String estado) {
        return polizasDB.values().stream()
                .filter(p -> (tipo == null || p.getTipo() == tipo))
                .filter(p -> (estado == null || p.getEstado().equalsIgnoreCase(estado)))
                .collect(Collectors.toList());
    }

    public Optional<Poliza> findById(Long id) {
        return Optional.ofNullable(polizasDB.get(id));
    }

    public void save(Poliza poliza) {
        polizasDB.put(poliza.getId(), poliza);
    }
}