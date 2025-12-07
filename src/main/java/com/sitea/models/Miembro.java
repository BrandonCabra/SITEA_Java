package com.sitea.models;

import java.io.Serializable;

public class Miembro implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String nombre;
    private String cargo;
    private String descripcion;
    private String emoji;
    
    public Miembro() {}
    
    public Miembro(String nombre, String cargo, String descripcion, String emoji) {
        this.nombre = nombre;
        this.cargo = cargo;
        this.descripcion = descripcion;
        this.emoji = emoji;
    }
    
    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public String getEmoji() { return emoji; }
    public void setEmoji(String emoji) { this.emoji = emoji; }
}
