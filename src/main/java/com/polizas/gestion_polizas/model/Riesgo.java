package com.polizas.gestion_polizas.model;

public class Riesgo {
    private Long id;
    private Long polizaId;
    private String estado; // ACTIVO, CANCELADO

    public Riesgo(Long id, Long polizaId, String estado) {
        this.id = id;
        this.polizaId = polizaId;
        this.estado = estado;
    }

    public Long getId() { return id; }
    public Long getPolizaId() { return polizaId; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}