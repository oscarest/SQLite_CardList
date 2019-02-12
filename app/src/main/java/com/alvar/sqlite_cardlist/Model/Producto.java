package com.alvar.sqlite_cardlist.Model;

public class Producto {
    private Long id;
    private String nombre;
    private Integer cantidad;
    private String seccion;

    public Producto() {
        this.id = null;
        this.nombre = "";
        this.cantidad = 0;
        this.seccion = "";
    }

    public Producto(String nombre, Integer cantidad, String seccion) {
        this.id = null;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.seccion = seccion;
    }

    public Producto(Long id, String nombre, Integer cantidad, String seccion) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.seccion = seccion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    @Override
    public String toString() {
        return "Producto {" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", cantidad=" + cantidad +
                ", seccion='" + seccion + '\'' +
                '}';
    }
}