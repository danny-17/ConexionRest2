package com.example.conexionrest2.Interfaz;

import com.example.conexionrest2.Modelo.Auto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AutoApi {
    @GET("marcas")
     Call<List<Auto>> getAuto();
}
