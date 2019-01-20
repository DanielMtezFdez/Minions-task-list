package com.khezu.listatareas.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ControladorDB extends SQLiteOpenHelper {


    public ControladorDB(Context context) {
        super(context, "com.khezu.listatareas.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE TAREAS (ID INTEGER PRIMARY KEY AUTOINCREMENT, TAREA TEXT NOT NULL);");
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

    public String[] obtenerTareas(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT NOMBRE FROM TAREAS", null);
        int numRegistros = cursor.getCount();
        if(numRegistros==0){
            db.close();
            return null;
        } else {
            cursor.moveToFirst();
            String[] tareas =  new String[numRegistros];
            for (int i = 0; i < numRegistros; i++) {
                tareas[i] = cursor.getString(i);
                cursor.moveToNext();
            }
            db.close();
            return tareas;
        }
    }

    public int contabilizarRegistros(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT NOMBRE FROM TAREAS", null);
        db.close();
        return cursor.getCount();
    }

    public void borrarTarea(String tarea){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("TAREAS", "NOMBRE = ?", new String[]{tarea});
        db.close();
    }




    //Métodos para los desafíos
    public void obtenerNumeroTareasCompletas(){

    }

    public void actualizarNumeroTareasCompletas(){

    }
}
