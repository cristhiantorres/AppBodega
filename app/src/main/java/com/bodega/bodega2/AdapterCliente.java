package com.bodega.bodega2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.bodega.bodega2.model.Cliente;
import com.bodega.bodega2.service.ClienteService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by cristhian on 12/05/17.
 */

public class AdapterCliente extends ArrayAdapter<Cliente> {
    private List<Cliente> listClientes;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://d3.pybox.com/bodegaapi/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    ClienteService service = retrofit.create(ClienteService.class);

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


//        item.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String doc = listClientes.get(position).getDoc();
//                Call<Cliente> call = service.showClientes(doc);
//
//                call.enqueue(new Callback<Cliente>() {
//                    @Override
//                    public void onResponse(Call<Cliente> call, Response<Cliente> response) {
//                        Cliente cliente = response.body();
//
//                        String nombre = cliente.getNombre();
//                        String apellido = cliente.getApellido();
//                        String doc = cliente.getDoc();
//
//                    }
//
//                    @Override
//                    public void onFailure(Call<Cliente> call, Throwable t) {
//
//                    }
//                });
//            }
//        });
        return item;
    }
}
