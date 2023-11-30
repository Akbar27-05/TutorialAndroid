package com.akbar27.sqlitedatabase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "dbToko";
    private static final int VERSION = 1;

    SQLiteDatabase db;

    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        db = this.getWritableDatabase();
    }

    boolean runSQL(String sql){
        try {
            db.execSQL(sql);
            return true;

        }
        catch (Exception ex){
            return false;
        }
    }

    Cursor select(String sql){
        try {
            return db.rawQuery(sql, null);
        }catch (Exception e){
            return null;
        }
    }

    public void buatTabel(){
        String tblBarang = "CREATE TABLE \"tblBarang\" (\n" +
                "\t\"idBarang\"\tINTEGER,\n" +
                "\t\"barang\"\tTEXT,\n" +
                "\t\"stock\"\tREAL,\n" +
                "\t\"harga\"\tREAL,\n" +
                "\tPRIMARY KEY(\"idBarang\" AUTOINCREMENT)\n" +
                ");";

        runSQL(tblBarang);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
