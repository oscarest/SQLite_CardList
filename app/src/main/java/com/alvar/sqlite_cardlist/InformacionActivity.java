package com.alvar.sqlite_cardlist;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.alvar.sqlite_cardlist.Logic.LogicProducto;

public class InformacionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion);

        TextView infoNombre = findViewById(R.id.infoNombre);
        TextView infoCantidad = findViewById(R.id.infoCantidad);
        TextView infoSeccion = findViewById(R.id.infoSeccion);

        infoNombre.setText(App.productoActivo.getNombre());
        infoCantidad.setText(App.productoActivo.getCantidad().toString());
        infoSeccion.setText(App.productoActivo.getSeccion());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.opcEditar: clicEditar(); break;
            case R.id.opcBorrar: clicBorrar(); break;
        }
        return true;
    }

    public void clicEditar() {
        App.accion = App.EDITAR;
        startActivity(new Intent(this, EdicionActivity.class));
        finish();
    }

    public void clicBorrar() {
        new AlertDialog.Builder(this)
                .setMessage("¿ Quieres borrar el producto " + App.productoActivo.getNombre() + " ?")
                .setCancelable(false)
                .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        LogicProducto.eliminarProducto(getApplicationContext(), App.productoActivo);
                        finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

}
