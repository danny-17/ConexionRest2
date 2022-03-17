package com.example.conexionrest2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.conexionrest2.Interfaz.AutoApi;
import com.example.conexionrest2.Modelo.Auto;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView auto_item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auto_item=findViewById(R.id.auto_item);
        getAuto();
    }

    private void getAuto(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://parallelum.com.br/fipe/api/v1/carros/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AutoApi autoapi=retrofit.create(AutoApi.class);
        Call<List<Auto>> call = autoapi.getAuto();

        call.enqueue(new Callback<List<Auto>>() {
            @Override
            public void onResponse(Call<List<Auto>> call, Response<List<Auto>> response) {
                if(!response.isSuccessful()){
                    auto_item.setText(response.code());
                }
                List<Auto> lista_auto = response.body();
                for(Auto auto: lista_auto){
                    String contenido = "";
                    contenido+="codigo"+auto.getCodigo()+"\n";
                    contenido+="marca"+auto.getNome()+"\n\n";
                    auto_item.append(contenido);
                }
            }

            @Override
            public void onFailure(Call<List<Auto>> call, Throwable t) {
                auto_item.setText(t.getMessage());
            }
        });
    }
}