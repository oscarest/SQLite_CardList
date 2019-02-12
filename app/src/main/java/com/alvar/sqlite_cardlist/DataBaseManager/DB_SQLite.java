package com.alvar.sqlite_cardlist.DataBaseManager;

import com.alvar.sqlite_cardlist.DataBaseManager.Esquema.Producto;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB_SQLite extends SQLiteOpenHelper {

    public static final int OPEN_MODE_READ = 1;
    public static final int OPEN_MODE_WRITE = 2;

    private static final String DATABASE_NAME = "tienda.sqlite";
    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + Producto.TABLE_NAME + " (" +
                    Producto.COLUMN_NAME_ID + " " + Producto.COLUMN_TYPE_ID + " PRIMARY KEY AUTOINCREMENT, " +
                    Producto.COLUMN_NAME_NOMBRE + " " + Producto.COLUMN_TYPE_NOMBRE + "," +
                    Producto.COLUMN_NAME_CANTIDAD + " " + Producto.COLUMN_TYPE_CANTIDAD +  "," +
                    Producto.COLUMN_NAME_SECCION + " " + Producto.COLUMN_TYPE_SECCION + ")";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + Producto.TABLE_NAME;

    public DB_SQLite(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public static SQLiteDatabase conectar(Context context, int open_mode) {
        DB_SQLite db = new DB_SQLite(context);
        SQLiteDatabase conn;
        switch (open_mode) {
            case OPEN_MODE_READ:  conn = db.getReadableDatabase(); break;
            case OPEN_MODE_WRITE: conn = db.getWritableDatabase(); break;
            default:              conn = db.getReadableDatabase(); break;
        }
        return conn;
    }

    public static void desconectar(SQLiteDatabase conn) {
        conn.close();
    }

}