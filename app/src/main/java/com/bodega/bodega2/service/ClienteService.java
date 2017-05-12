package com.bodega.bodega2.service;

import com.bodega.bodega2.model.Cliente;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by cristhian on 08/05/17.
 */

public interface ClienteService {

    @GET("clientes?api_token=rZIvmjiRDXwba1Fn44Yp1NxdFgIdfk2136eIkDSzAd9rPofciVwpMAHus0Z7")
    Call<List<Cliente>> getClientes();

    @GET("clientes/{doc}/show?api_token=rZIvmjiRDXwba1Fn44Yp1NxdFgIdfk2136eIkDSzAd9rPofciVwpMAHus0Z7")
    Call<Cliente> showClientes(@Path("doc") String doc);

}
