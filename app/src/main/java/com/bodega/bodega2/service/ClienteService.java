package com.bodega.bodega2.service;

import com.bodega.bodega2.model.Cliente;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by cristhian on 08/05/17.
 */

public interface ClienteService {

    @GET("pedidos/clientes")
    Call<List<Cliente>> getClientes();

    @GET("api/clientes/{doc}/show?api_token=TYtm7VTWLLKTsDbnRBoIExU4dH9UX4r0HN2y7KvyCGC9HR4QweL2nRyDsPjN")
    Call<Cliente> showClientes(@Path("doc") String doc);

    @POST("api/5370clientes?api_token=TYtm7VTWLLKTsDbnRBoIExU4dH9UX4r0HN2y7KvyCGC9HR4QweL2nRyDsPjN")
    Call<List<Cliente>> store(@Body Cliente cliente);

}