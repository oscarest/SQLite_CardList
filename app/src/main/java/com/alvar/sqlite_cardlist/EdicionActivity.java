package com.alvar.sqlite_cardlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.alvar.sqlite_cardlist.Logic.LogicProducto;

import java.util.Arrays;
import java.util.List;

public class EdicionActivity extends AppCompatActivity {

    private EditText txtNombre;
    private EditText txtCantidad;
    private Spinner lstSeccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edicion);

        txtNombre = findViewById(R.id.txtNombre);
        txtCantidad = findViewById(R.id.txtCantidad);
        lstSeccion = findViewById(R.id.lstSeccion);

        List secciones = Arrays.asList("Monitor", "DiscoDuro", "Memoria", "Teclado", "Ratón", "Impresora");
        lstSeccion.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, secciones));

        txtNombre.setText(App.productoActivo.getNombre());
        txtCantidad.setText(App.productoActivo.getCantidad().toString());
        lstSeccion.setSelection(secciones.indexOf(App.productoActivo.getSeccion()));
    }

    public void clicGuardar(View view) {
        String nombre = txtNombre.getText().toString();
        String cantidad = txtCantidad.getText().toString();
        String seccion = lstSeccion.getSelectedItem().toString();

        if (nombre.equals("") || cantidad.equals("")) {
            mostrarMensaje("Faltan datos obligatorios.");
        } else {
            App.productoActivo.setNombre(nombre);
            App.productoActivo.setCantidad(Integer.parseInt(cantidad));
            App.productoActivo.setSeccion(seccion);
            switch (App.accion) {
                case App.INSERTAR:
                    LogicProducto.insertarProducto(this, App.productoActivo);
                    break;
                case App.EDITAR:
                    LogicProducto.editarProducto(this, App.productoActivo);
                    break;
            }
            mostrarMensaje("Producto " + nombre + " ha sido almacenado.");
            finish();
        }
    }

    private void mostrarMensaje(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
}
