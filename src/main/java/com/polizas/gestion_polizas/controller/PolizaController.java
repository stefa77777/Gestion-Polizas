package com.polizas.gestion_polizas.controller;

import com.polizas.gestion_polizas.model.Poliza;
import com.polizas.gestion_polizas.model.Riesgo;
import com.polizas.gestion_polizas.model.TipoPoliza;
import com.polizas.gestion_polizas.service.PolizaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/polizas")
public class PolizaController {

    private final PolizaService service;
    private static final String API_KEY_VALIDA = "123456";

    public PolizaController(PolizaService service) {
        this.service = service;
    }

    private boolean validarApiKey(String key) {
        return API_KEY_VALIDA.equals(key);
    }

    @GetMapping
    public ResponseEntity<?> listarPolizas(
            @RequestHeader("x-api-key") String apiKey,
            @RequestParam(required = false) TipoPoliza tipo,
            @RequestParam(required = false) String estado) {

        if (!validarApiKey(apiKey)) {
            return ResponseEntity.status(401).body("API Key inválida");
        }
        List<Poliza> polizas = service.listarPolizas(tipo, estado);
        return ResponseEntity.ok(polizas);
    }

    @PostMapping("/{id}/renovar")
    public ResponseEntity<?> renovarPoliza(
            @RequestHeader("x-api-key") String apiKey,
            @PathVariable Long id) {

        if (!validarApiKey(apiKey)) {
            return ResponseEntity.status(401).body("API Key inválida");
        }
        try {
            Poliza poliza = service.renovarPoliza(id);
            return ResponseEntity.ok(poliza);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/{id}/cancelar")
    public ResponseEntity<?> cancelar(
            @RequestHeader("x-api-key") String apiKey,
            @PathVariable Long id) {

        if (!validarApiKey(apiKey)) {
            return ResponseEntity.status(401).body("API Key inválida");
        }
        try {
            service.cancelarPoliza(id);
            return ResponseEntity.ok().body("Póliza y sus riesgos cancelados exitosamente");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/{id}/riesgos")
    public ResponseEntity<?> agregarRiesgo(
            @RequestHeader("x-api-key") String apiKey,
            @PathVariable Long id,
            @RequestBody Map<String, Long> payload) {

        if (!validarApiKey(apiKey)) {
            return ResponseEntity.status(401).body("API Key inválida");
        }
        try {
            Long riesgoId = payload.get("riesgoId");
            Riesgo riesgo = service.agregarRiesgo(id, riesgoId);
            return ResponseEntity.ok(riesgo);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}