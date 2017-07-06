package com.bodega.bodega2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.bodega.bodega2.model.Articulo;
import com.bodega.bodega2.service.ProductosService;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.bodega.bodega2.R.id.txtprecio;

public class PedidoActivity extends AppCompatActivity {
    TextView text_nombre,text_doc,text_articulo, text_precio;
    Button btn_salir, btn_agregar,btn_pedido;
    private ListView listView;


    ArrayList<Articulo> articuloArrayList = new ArrayList<>();
    ArrayList<Articulo> auxarray = new ArrayList<>();
    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("https://laravel-fvn5.frb.io/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
    ProductosService service = retrofit.create(ProductosService.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);

        text_nombre = (TextView) findViewById(R.id.text_nombre);
        text_doc = (TextView) findViewById(R.id.text_doc);
        text_articulo = (TextView) findViewById(R.id.txt_articulo);
        text_precio = (TextView) findViewById(txtprecio);
        btn_salir = (Button) findViewById(R.id.btnsalir);
        btn_agregar = (Button) findViewById(R.id.btnagregar);
        btn_pedido = (Button) findViewById(R.id.btn_pedido);


        listView = (ListView) findViewById(R.id.listproductos);

        String nombre = getIntent().getStringExtra("nombre");
        String apellido = getIntent().getStringExtra("apellido");
        String doc = getIntent().getStringExtra("doc");

        text_nombre.setText(nombre+" "+apellido);
        text_doc.setText(doc);

        btn_salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vlprecio = text_precio.getText().toString();
                String vldescripcion = text_articulo.getText().toString();
                articuloArrayList.add(new Articulo(vldescripcion,vlprecio));
            }
        });

        btn_pedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String json = new Gson().toJson(articuloArrayList);
                Log.e("Json Articulos: ", json);
            }
        });



        getArticulos();

    }

    private class AdapterArticulo extends ArrayAdapter<Articulo>{
        private List<Articulo> articuloList;

        public AdapterArticulo(Context context, List<Articulo> articulos){
            super(context, R.layout.list_articulos,articulos);
            articuloList = articulos;
        }

        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            LayoutInflater inflater = LayoutInflater.from(getContext());

            View item = inflater.inflate(R.layout.list_articulos,null);

            TextView descripcion = (TextView) item.findViewById(R.id.txt_descripcion);
            TextView precio = (TextView) item.findViewById(R.id.txt_precio);

            descripcion.setText( articuloList.get(position).getDescripcion());
            precio.setText(articuloList.get(position).getPrecio() + " Gs.");

            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String articulo = articuloList.get(position).getDescripcion();
                    String precio = articuloList.get(position).getPrecio();
                    text_precio.setText(precio);
                    text_articulo.setText(articulo);
                }
            });

            return item;
        }
    }

    private void getArticulos(){
        Call<List<Articulo>> call = service.gerArticulo();

        call.enqueue(new Callback<List<Articulo>>() {
            @Override
            public void onResponse(Call<List<Articulo>> call, Response<List<Articulo>> response) {
                List<Articulo> articulos = response.body();
                AdapterArticulo adapterArticulo = new AdapterArticulo(PedidoActivity.this,articulos);
                listView.setAdapter(adapterArticulo);
            }

            @Override
            public void onFailure(Call<List<Articulo>> call, Throwable t) {

            }
        });
    }
}
