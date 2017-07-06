package com.bodega.bodega2;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bodega.bodega2.model.Cliente;
import com.bodega.bodega2.service.ClienteService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    TextView txtdetalles;
    Button btnver;
    Button btnsalir;
    ProgressDialog progressDialog;
    private ListView listView;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://laravel-fvn5.frb.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    ClienteService service = retrofit.create(ClienteService.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnver = (Button) findViewById(R.id.btnver);
        btnsalir = (Button) findViewById(R.id.btnsalir);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Cargando ...");
        progressDialog.setCancelable(false);
        listView = (ListView) findViewById(R.id.listclientes);

        getClientesDetalles();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        btnver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getClientesDetalles();
            }
        });

        btnsalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });
    }

    private class AdapterCliente extends ArrayAdapter<Cliente>{
        private List<Cliente> listClientes;

        private MainActivity mainActivity;

        public  AdapterCliente(Context context, List<Cliente> clientes){
            super(context,R.layout.list_item,clientes);

            listClientes = clientes;
        }

        public View getView(final int position, View convertView, ViewGroup parent){
            LayoutInflater inflater = LayoutInflater.from(getContext());

            View item = inflater.inflate(R.layout.list_item,null);

            TextView documento = (TextView) item.findViewById(R.id.text_doc_list);
            TextView nombre = (TextView) item.findViewById(R.id.text_nombre_list);

            documento.setText(listClientes.get(position).getDoc());
            nombre.setText(listClientes.get(position).getNombre()+" "+listClientes.get(position).getApellido());


            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String doc = listClientes.get(position).getDoc();
                    Call<Cliente> call = service.showClientes(doc);

                    call.enqueue(new Callback<Cliente>() {
                        @Override
                        public void onResponse(Call<Cliente> call, Response<Cliente> response) {
                            Cliente cliente = response.body();

                            String nombre = cliente.getNombre();
                            String apellido = cliente.getApellido();
                            String doc = cliente.getDoc();

                            getClienteShow(doc);
                        }

                        @Override
                        public void onFailure(Call<Cliente> call, Throwable t) {

                        }
                    });
                }
            });
            return item;
        }
    }

    private void getClientesDetalles(){
        progressDialog.show();
        Call<List<Cliente>> call = service.getClientes();
        call.enqueue(new Callback<List<Cliente>>() {
            @Override
            public void onResponse(Call<List<Cliente>> call, Response<List<Cliente>> response) {
                List<Cliente> clientes = response.body();
                AdapterCliente adapter = new AdapterCliente(MainActivity.this,clientes);
                listView.setAdapter(adapter);
                progressDialog.hide();
            }

            @Override
            public void onFailure(Call<List<Cliente>> call, Throwable t) {
                Log.e("onFailure: ",t.getMessage());
                progressDialog.hide();
            }
        });
    }
    private void getClienteShow(String doc){
        Call<Cliente> call = service.showClientes(doc);
        call.enqueue(new Callback<Cliente>() {
            @Override
            public void onResponse(Call<Cliente> call, Response<Cliente> response) {
                //Traemos todos los datos a la variable de tipo Cliente
                Cliente cliente = response.body();
                //Declaramos y cargamos las variables a utilizar
                String nombre = cliente.getNombre();
                String apellido = cliente.getApellido();
                String doc = cliente.getDoc();
                //Declaramos en Intent para poder llamar a PedidoActivity
                Intent i = new Intent(MainActivity.this,PedidoActivity.class);
                //Declaramos los parametros a pasar a PedidoActivity
                i.putExtra("nombre",nombre);
                i.putExtra("doc",doc);
                i.putExtra("apellido",apellido);
                startActivity(i);
            }
            @Override
            public void onFailure(Call<Cliente> call, Throwable t) {
                Log.e("onFailure: ", t.getMessage());
            }
        });
    }

}
