package com.bodega.bodega2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bodega.bodega2.model.Cliente;
import com.bodega.bodega2.service.ClienteService;

import java.util.List;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {

    Button btn_cliente, btn_salir,btn_add;
    MainActivity mainActivity;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://laravel-fvn5.frb.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    ClienteService service = retrofit.create(ClienteService.class);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText ed_nombre = (EditText) findViewById(R.id.edit_nombre);
        final EditText ed_apellido = (EditText) findViewById(R.id.edit_apellido);
        final EditText ed_correo = (EditText) findViewById(R.id.edit_correo);
        final EditText ed_telefono = (EditText) findViewById(R.id.edit_telefono);
        final EditText ed_doc = (EditText) findViewById(R.id.edit_doc);
        final EditText ed_direccion = (EditText) findViewById(R.id.edit_direccion);

        btn_cliente = (Button) findViewById(R.id.btn_cliente);
        btn_salir = (Button) findViewById(R.id.btn_salir);
        btn_add = (Button) findViewById(R.id.btn_add);

        btn_cliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(i);
            }
        });

        btn_salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vlnombre = ed_nombre.getText().toString().trim();
                String vlapellido= ed_apellido.getText().toString().trim();
                String vlcorreo = ed_correo.getText().toString().trim();
                String vltelefono = ed_telefono.getText().toString().trim();
                String vldoc = ed_doc.getText().toString().trim();
                String vldireccion = ed_direccion.getText().toString().trim();

                if (!TextUtils.isEmpty(vlnombre) && !TextUtils.isEmpty(vlapellido) && !TextUtils.isEmpty(vldoc) && !TextUtils.isEmpty(vlcorreo) && !TextUtils.isEmpty(vltelefono)){
                    saveCliente(vlnombre,vlapellido,vldoc,vlcorreo,vltelefono,vldireccion);
                }else{
                    Toast.makeText(getApplicationContext(),"Error: Vacio",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void saveCliente(final String vnombre, String vapellido, final String vdoc, String vtelefono, String vcorreo, String vdireccion){
        Cliente cliente = new Cliente(vnombre,vapellido,vdoc, vtelefono,vcorreo,vdireccion);
//        Call<Cliente> call =  service.store(cliente);
        Call<List<Cliente>> call = service.store(cliente);
        call.enqueue(new Callback<List<Cliente>>() {
            @Override
            public void onResponse(Call<List<Cliente>> call, Response<List<Cliente>> response) {
                String code = String.valueOf(response.code());
                String message = String.valueOf(response.message());
                if (response.isSuccessful()){
                    getClienteShow(vdoc);
                    Toast.makeText(getApplicationContext(),"Te has registrado correctamente",Toast.LENGTH_SHORT).show();
                }else{
                    Log.e("Error: ",code +": "+message);
                }
            }

            @Override
            public void onFailure(Call<List<Cliente>> call, Throwable t) {
                Log.e("onFailure: ",t.getMessage());
            }
        });

    }

    private void getClienteShow(String doc){
        Call<Cliente> call = service.showClientes(doc);
        call.enqueue(new Callback<Cliente>() {
            @Override
            public void onResponse(Call<Cliente> call, Response<Cliente> response) {
                EditText ed_nombre = (EditText) findViewById(R.id.edit_nombre);
                EditText ed_apellido = (EditText) findViewById(R.id.edit_apellido);
                EditText ed_correo = (EditText) findViewById(R.id.edit_correo);
                EditText ed_telefono = (EditText) findViewById(R.id.edit_telefono);
                EditText ed_doc = (EditText) findViewById(R.id.edit_doc);
                EditText ed_direccion = (EditText) findViewById(R.id.edit_direccion);

                //Traemos todos los datos a la variable de tipo Cliente
                Cliente cliente = response.body();
                //Declaramos y cargamos las variables a utilizar
                String nombre = cliente.getNombre();
                String apellido = cliente.getApellido();
                String doc = cliente.getDoc();
                //Declaramos en Intent para poder llamar a PedidoActivity
                Intent i = new Intent(RegisterActivity.this,PedidoActivity.class);
                //Declaramos los parametros a pasar a PedidoActivity
                i.putExtra("nombre",nombre);
                i.putExtra("doc",doc);
                i.putExtra("apellido",apellido);
                startActivity(i);
                ed_nombre.setText("");
                ed_apellido.setText("");
                ed_correo.setText("");
                ed_telefono.setText("");
                ed_doc.setText("");
                ed_direccion.setText("");
            }
            @Override
            public void onFailure(Call<Cliente> call, Throwable t) {
                Log.e("onFailure: ", t.getMessage());
            }
        });
    }
}
