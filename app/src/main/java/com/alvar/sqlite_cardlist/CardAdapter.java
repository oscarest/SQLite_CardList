package com.alvar.sqlite_cardlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.alvar.sqlite_cardlist.Model.Producto;

import java.util.ArrayList;
import java.util.List;

public class CardAdapter extends ArrayAdapter {
    private List <Producto> listado = new ArrayList<>();

    static class CardViewHolder {
        TextView line1;
        TextView line2;
        TextView line3;
    }

    public CardAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }


    public void add(Producto object) {
        listado.add(object);
        super.add(object);
    }

    @Override
    public int getCount() {
        return this.listado.size();
    }

    @Override
    public Producto getItem(int index) {
        return (Producto) this.listado.get(index);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.list_item_card, parent, false);

            CardViewHolder viewHolder = new CardViewHolder();
            viewHolder.line1 = row.findViewById(R.id.line1);
            viewHolder.line2 = row.findViewById(R.id.line2);
            viewHolder.line3 = row.findViewById(R.id.line3);

            Producto p = getItem(position);
            viewHolder.line1.setText(p.getNombre());
            viewHolder.line2.setText(p.getCantidad().toString());
            viewHolder.line3.setText(p.getSeccion());

            row.setTag(viewHolder);
        }
        return row;
    }

}