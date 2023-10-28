package com.lf.gestioncobranza.constants;

public enum TipoMoneda {
    PESOS(1, "PESOS"),
    DOLARES(2, "DÓLARES"),
    EUROS(3, "EUROS");

    private final int valor;
    private final String nombre;

    TipoMoneda(int valor, String nombre) {
        this.valor = valor;
        this.nombre = nombre;
    }

    public int getValor() {
        return valor;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
