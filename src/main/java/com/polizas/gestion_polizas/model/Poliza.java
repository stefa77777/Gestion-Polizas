package com.polizas.gestion_polizas.model;

import java.util.ArrayList;
import java.util.List;

public class Poliza {
    private Long id;
    private com.polizas.gestion_polizas.model.TipoPoliza tipo;
    private String estado; // ACTIVA, RENOVADA, CANCELADA
    private double canonMensual;
    private double valorPrima;
    private List<Riesgo> riesgos = new ArrayList<>();

    public Poliza(Long id, com.polizas.gestion_polizas.model.TipoPoliza tipo, String estado, double canonMensual, double valorPrima) {
        this.id = id;
        this.tipo = tipo;
        this.estado = estado;
        this.canonMensual = canonMensual;
        this.valorPrima = valorPrima;
    }

    public Long getId() { return id; }
    public com.polizas.gestion_polizas.model.TipoPoliza getTipo() { return tipo; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public double getCanonMensual() { return canonMensual; }
    public void setCanonMensual(double canonMensual) { this.canonMensual = canonMensual; }
    public double getValorPrima() { return valorPrima; }
    public void setValorPrima(double valorPrima) { this.valorPrima = valorPrima; }
    public List<Riesgo> getRiesgos() { return riesgos; }
}
