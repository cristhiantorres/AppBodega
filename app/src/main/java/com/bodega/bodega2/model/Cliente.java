package com.bodega.bodega2.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by cristhian on 08/05/17.
 */

public class Cliente {

    @SerializedName("clientes")
    private int id;
    private String nombre;
    private String apellido;
    private String doc;
    private String telefono;
    private String correo;
    private String direccion;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDoc() {
        return doc;
    }

    public void setDoc(String doc) {
        this.doc = doc;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }


    public Cliente(String nombre,String apellido, String doc, String telefono, String correo, String direccion){
        this.nombre = nombre;
        this.apellido = apellido;
        this.doc = doc;
        this.telefono = telefono;
        this.correo = correo;
        this.direccion = direccion;
    }
}
