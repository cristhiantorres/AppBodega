package com.bodega.bodega2.service;

import com.bodega.bodega2.model.Articulo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by cristhian on 10/06/17.
 */

public interface ProductosService {

    @GET("pedidos/articulos")
    Call<List<Articulo>> gerArticulo();

}
