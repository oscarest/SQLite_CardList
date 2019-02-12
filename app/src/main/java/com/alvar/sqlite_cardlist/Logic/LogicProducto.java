package com.alvar.sqlite_cardlist.Logic;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.alvar.sqlite_cardlist.DataBaseManager.DB_SQLite;
import com.alvar.sqlite_cardlist.DataBaseManager.Esquema;
import com.alvar.sqlite_cardlist.Model.Producto;

import java.util.ArrayList;
import java.util.List;

public class LogicProducto {

    public static void insertarProducto(Context context, Producto p) {
        ContentValues content = new ContentValues();
        content.put(Esquema.Producto.COLUMN_NAME_NOMBRE, p.getNombre());
        content.put(Esquema.Producto.COLUMN_NAME_CANTIDAD, p.getCantidad());
        content.put(Esquema.Producto.COLUMN_NAME_SECCION, p.getSeccion());
        SQLiteDatabase conn = DB_SQLite.conectar(context, DB_SQLite.OPEN_MODE_WRITE);
        conn.insert(Esquema.Producto.TABLE_NAME, null, content);
        DB_SQLite.desconectar(conn);
    }

    public static void eliminarProducto(Context context, Producto p) {
        String sqlWhere = Esquema.Producto.COLUMN_NAME_ID + " = " + p.getId();
        SQLiteDatabase conn = DB_SQLite.conectar(context, DB_SQLite.OPEN_MODE_WRITE);
        conn.delete(Esquema.Producto.TABLE_NAME, sqlWhere, null);
        DB_SQLite.desconectar(conn);
    }

    public static void editarProducto(Context context, Producto p) {
        ContentValues content = new ContentValues();
        content.put(Esquema.Producto.COLUMN_NAME_NOMBRE, p.getNombre());
        content.put(Esquema.Producto.COLUMN_NAME_CANTIDAD, p.getCantidad());
        content.put(Esquema.Producto.COLUMN_NAME_SECCION, p.getSeccion());
        String sqlWhere = Esquema.Producto.COLUMN_NAME_ID + " = " + p.getId();
        SQLiteDatabase conn = DB_SQLite.conectar(context, DB_SQLite.OPEN_MODE_WRITE);
        conn.update(Esquema.Producto.TABLE_NAME, content, sqlWhere, null);
        DB_SQLite.desconectar(conn);
    }

    public static List listaProductos(Context context) {
        List prod = new ArrayList<>();
        String[] sqlFields = {Esquema.Producto.COLUMN_NAME_ID, Esquema.Producto.COLUMN_NAME_NOMBRE, Esquema.Producto.COLUMN_NAME_CANTIDAD, Esquema.Producto.COLUMN_NAME_SECCION};
        String sqlWhere = "";
        String sqlOrderBy = Esquema.Producto.COLUMN_NAME_NOMBRE + " ASC";

        SQLiteDatabase conn = DB_SQLite.conectar(context, DB_SQLite.OPEN_MODE_READ);
        Cursor cursor = conn.query(Esquema.Producto.TABLE_NAME, sqlFields, sqlWhere, null, null, null, sqlOrderBy);
        if (cursor.getCount() == 0) {
            prod = null;
        } else {
            cursor.moveToFirst();
            do {
                Long dataId = cursor.getLong(cursor.getColumnIndex(Esquema.Producto.COLUMN_NAME_ID));
                String dataNombre = cursor.getString(cursor.getColumnIndex(Esquema.Producto.COLUMN_NAME_NOMBRE));
                Integer dataCantidad = cursor.getInt(cursor.getColumnIndex(Esquema.Producto.COLUMN_NAME_CANTIDAD));
                String dataSeccion = cursor.getString(cursor.getColumnIndex(Esquema.Producto.COLUMN_NAME_SECCION));
                prod.add(new Producto(dataId, dataNombre, dataCantidad, dataSeccion));
            } while (cursor.moveToNext());
        }
        cursor.close();
        DB_SQLite.desconectar(conn);
        return prod;
    }

}