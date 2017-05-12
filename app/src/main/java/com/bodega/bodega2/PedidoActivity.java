package com.bodega.bodega2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PedidoActivity extends AppCompatActivity {
    TextView text_nombre,text_doc;
    Button btn_salir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);

        text_nombre = (TextView) findViewById(R.id.text_nombre);
        text_doc = (TextView) findViewById(R.id.text_doc);
        btn_salir = (Button) findViewById(R.id.btnsalir);

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

    }
}
