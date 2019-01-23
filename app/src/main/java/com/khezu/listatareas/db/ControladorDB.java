package com.khezu.listatareas.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class ControladorDB extends SQLiteOpenHelper {


    public ControladorDB(Context context) {
        super(context, "com.khezu.listatareas.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE TAREAS (ID INTEGER PRIMARY KEY AUTOINCREMENT, NOMBRE TEXT NOT NULL);");
        db.execSQL("CREATE TABLE TAREAS_COMPLETAS (ID INTEGER PRIMARY KEY AUTOINCREMENT, TAREA_COMPLETA TEXT NOT NULL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertarTarea(String tarea){
        ContentValues registro = new ContentValues();
        registro.put("NOMBRE", tarea);
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert("TAREAS", null, registro);
        db.close();
    }

    public ArrayList<String> obtenerTareas(){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM TAREAS", null);
        int regs = cursor.getCount();
        if(regs==0){
            db.close();
            return null;
        } else {
            ArrayList<String> tareas = new ArrayList<>();
            cursor.moveToFirst();
            for (int i = 0; i < regs; i++) {
                tareas.add(cursor.getString(1));
                cursor.moveToNext();
            }
            db.close();
            return tareas;
        }
    }

    public int contabilizarRegistros(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM TAREAS", null);
        return cursor.getCount();
    }

    public void borrarTarea(String tarea){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("TAREAS", "NOMBRE = ?", new String[]{tarea});
        insertarTareaCompleta(tarea);
        db.close();

    }

    //Desafíos
    public void insertarTareaCompleta(String tarea){
        ContentValues registro = new ContentValues();
        registro.put("TAREA_COMPLETA", tarea);
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert("TAREAS_COMPLETAS", null, registro);
        db.close();
    }

    public int comprobarTareasCompletas(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM TAREAS_COMPLETAS", null);
        int registrosTotales = cursor.getCount();
        db.close();
        return registrosTotales;
    }

}
