package com.bodega.bodega2.model;

import java.util.Date;

/**
 * Created by cristhian on 13/06/17.
 */

public class Articulo {
    private int id;
    private int tipo_articulo;
    private String precio;
    private String descripcion;
    private String creado_por;

    public Articulo( String precio, String descripcion) {
        this.precio = precio;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTipo_articulo() {
        return tipo_articulo;
    }

    public void setTipo_articulo(int tipo_articulo) {
        this.tipo_articulo = tipo_articulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCreado_por() {
        return creado_por;
    }

    public void setCreado_por(String creado_por) {
        this.creado_por = creado_por;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }
}
