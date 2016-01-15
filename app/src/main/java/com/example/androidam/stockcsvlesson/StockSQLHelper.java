package com.example.androidam.stockcsvlesson;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by mjpurin on 2015/08/31.
 */
public class StockSQLHelper extends SQLiteOpenHelper {
    StockDAO dao;
    ArrayList<Stock> list;
    public StockSQLHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version,ArrayList<Stock> list) {
        super(context, name, factory, version);
        this.list=list;
        dao=new StockDAO();
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        dao.setDb(db);
        dao.create();
        dao.insert(list);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        dao.setDb(db);
        dao.drop();
        this.onCreate(db);

    }

}
