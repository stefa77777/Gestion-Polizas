package com.polizas.gestion_polizas.service;

import com.polizas.gestion_polizas.model.Poliza;
import com.polizas.gestion_polizas.model.Riesgo;
import com.polizas.gestion_polizas.model.TipoPoliza;
import com.polizas.gestion_polizas.repository.PolizaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PolizaService {

    private final PolizaRepository repository;
    private static final double IPC = 0.05; // Incremento del 5%

    public PolizaService(PolizaRepository repository) {
        this.repository = repository;
    }

    public List<Poliza> listarPolizas(TipoPoliza tipo, String estado) {
        return repository.findByTipoAndEstado(tipo, estado);
    }

    public Poliza renovarPoliza(Long id) {
        Poliza poliza = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Póliza no encontrada"));

        if ("CANCELADA".equalsIgnoreCase(poliza.getEstado())) {
            throw new RuntimeException("No se puede renovar una póliza cancelada.");
        }

        poliza.setCanonMensual(poliza.getCanonMensual() * (1 + IPC));
        poliza.setValorPrima(poliza.getValorPrima() * (1 + IPC));
        poliza.setEstado("RENOVADA");
        repository.save(poliza);
        return poliza;
    }

    public void cancelarPoliza(Long id) {
        Poliza poliza = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Póliza no encontrada"));
        poliza.setEstado("CANCELADA");
        repository.save(poliza);
    }

    public Riesgo agregarRiesgo(Long polizaId, Long riesgoId) {
        Poliza poliza = repository.findById(polizaId)
                .orElseThrow(() -> new RuntimeException("Póliza no encontrada"));

        if (poliza.getTipo() != TipoPoliza.COLECTIVA) {
            throw new RuntimeException("Solo se pueden agregar riesgos a pólizas de tipo COLECTIVA.");
        }

        return new Riesgo(riesgoId, polizaId, "ACTIVO");
    }
}