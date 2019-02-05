package com.alexlopezramos.mvc_vehiculos.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class VehiculosSQLiteHelper extends SQLiteOpenHelper {

    //Creacion de Tabla
    String sqlCreate = "CREATE TABLE vehiculos (" +
            "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
            "matricula VARCHAR(7) UNIQUE NOT NULL," +
            "marca VARCHAR(20)," +
            "modelo VARCHAR(20))";

    public VehiculosSQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS vehiculos");
        sqLiteDatabase.execSQL(sqlCreate);
    }
}
