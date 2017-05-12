package com.bodega.bodega2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bodega.bodega2.model.Cliente;

import java.util.ArrayList;

/**
 * Created by cristhian on 11/05/17.
 */

public class ListViewAdapter extends BaseAdapter {
    private ArrayList<Cliente> arrayList;
    private Context context;
    private LayoutInflater layoutInflater;

    public ListViewAdapter(ArrayList<Cliente> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View vista = layoutInflater.inflate(R.layout.list_item,parent,false);

        TextView text_doc = (TextView) vista.findViewById(R.id.text_doc_list);
        TextView text_nombre = (TextView) vista.findViewById(R.id.text_nombre_list);

        text_doc.setText(arrayList.get(position).getDoc());
        text_nombre.setText(arrayList.get(position).getNombre());


        return vista;
    }
}
