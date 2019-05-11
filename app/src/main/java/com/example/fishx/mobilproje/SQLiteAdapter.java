package com.example.fishx.mobilproje;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class SQLiteAdapter extends SQLiteOpenHelper {

    private static final String database_NAME = "toDoDB";
    private static final int database_VERSION = 1;
    private static final String table_TODO = "toDoContentDB";
    private static final String hatirlanacak_TODO = "content";
    private static final String hatirlanacak_ID = "id";
    private static final String hatirlanacak_YIL = "yil";
    private static final String hatirlanacak_AY = "ay";
    private static final String hatirlanacak_GUN = "gun";
    private static final String hatirlanacak_SAAT = "saat";
    private static final String hatirlanacak_DK = "dk";
    private static final String hatirlanacak_NOTIF = "notif";
    private static final String COLUMNS[] = { hatirlanacak_ID ,hatirlanacak_TODO  , hatirlanacak_YIL , hatirlanacak_AY ,
            hatirlanacak_GUN , hatirlanacak_SAAT , hatirlanacak_DK , hatirlanacak_NOTIF};
    private static final String CREATE_TODOTABLE = "CREATE TABLE " + table_TODO +" (" +
            hatirlanacak_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, " +
            hatirlanacak_TODO +" TEXT, " +
            hatirlanacak_AY +" TEXT, " +
            hatirlanacak_GUN +" TEXT, " +
            hatirlanacak_SAAT +" TEXT, " +
            hatirlanacak_DK +" TEXT, " +
            hatirlanacak_NOTIF +" TEXT, " +
            hatirlanacak_YIL +" TEXT " +
            ");";
    public SQLiteAdapter(Context context) {
        super(context, database_NAME, null, database_VERSION);
       // super(context,new File(Environment.getExternalStorageDirectory(),database_NAME).toString(),null,database_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TODOTABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void contentAdd(listAdapter contentValue){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(hatirlanacak_TODO,contentValue.getToDo());
        values.put(hatirlanacak_YIL,contentValue.getYil());
        values.put(hatirlanacak_AY,contentValue.getAy());
        values.put(hatirlanacak_GUN,contentValue.getGun());
        values.put(hatirlanacak_SAAT,contentValue.getSaat());
        values.put(hatirlanacak_DK,contentValue.getDk());
        values.put(hatirlanacak_NOTIF,contentValue.getNotif());
        db.insert(table_TODO,null,values);
        db.close();
    }
    public List<listAdapter> getContents(){
        List<listAdapter> listeler = new ArrayList<>();
        String query = " SELECT * FROM "+table_TODO;
        SQLiteDatabase db = this.getReadableDatabase();
        listAdapter liste = null;
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do {
                liste = new listAdapter();
                liste.setId(Integer.parseInt(cursor.getString(0)));
                liste.setToDo(cursor.getString(1));
                liste.setYil(cursor.getString(2));
                liste.setAy(cursor.getString(3));
                liste.setGun(cursor.getString(4));
                liste.setSaat(cursor.getString(5));
                liste.setDk(cursor.getString(6));
                listeler.add(liste);
            }while (cursor.moveToNext());
        }
        return listeler;
    }

    public listAdapter contentRead(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(table_TODO,COLUMNS," id = ?",new String[]{String.valueOf(id)},null,null,null);
            if(cursor !=null)
                 cursor.moveToFirst();

            listAdapter liste = new listAdapter();
            liste.setId(cursor.getInt(0));
            liste.setToDo(cursor.getString(1));
            liste.setYil(cursor.getString(2));
            liste.setAy(cursor.getString(3));
            liste.setGun(cursor.getString(4));
            liste.setSaat(cursor.getString(5));
            liste.setDk(cursor.getString(6));
            return liste;
    }
    public void deletetoDo(listAdapter list){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(table_TODO,hatirlanacak_ID + " = ?",new String[]{String.valueOf(list.getId())});
        db.close();

    }
    //geri dönüş değeri etkilenen kayıt sayısı
    public int updateContent(listAdapter list){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("content",list.getToDo());
        values.put("yil",list.getYil());
        values.put("ay",list.getAy());
        values.put("gun",list.getGun());
        values.put("saat",list.getSaat());
        values.put("dk",list.getDk());
        int i = db.update(table_TODO,values,hatirlanacak_ID + " = ?",new String[]{String.valueOf(list.getId())});
        db.close();
        return i;


    }


}
