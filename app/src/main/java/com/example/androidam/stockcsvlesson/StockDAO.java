package com.example.androidam.stockcsvlesson;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by androidAM on 2015/08/31.
 */
public class StockDAO {
    SQLiteDatabase db;
    public void setDb(SQLiteDatabase db){
        this.db=db;
    }
    public void create() {

        String sql = "CREATE TABLE stock( " +
                "code TEXT, " +
                "name TEXT," +
                "start REAL," +
                "end REAL );";
        //SQLを実行する
        db.execSQL(sql);
    }

    public void drop() {

        String sql = "DROP TABLE IF EXISTS stock;";
        //SQLを実行する
        db.execSQL(sql);
    }

    public void insert(ArrayList<Stock> list) {
        db.beginTransaction();//トランザクション開始
        try {
            SQLiteStatement stt = db.compileStatement("INSERT INTO stock(code,name,start,end) VALUES(?,?,?,?)");
            for (Stock st : list) {
                stt.bindString(1, st.code);
                stt.bindString(2, st.name);
                stt.bindDouble(3, st.start);
                stt.bindDouble(4, st.end);

                stt.executeInsert();

            }

            db.setTransactionSuccessful();//成功を通知するフラグ
        } finally {
            db.endTransaction();//成功フラグが立っていたらコミット
        }
    }

    public ArrayList<Stock> select(String column,int limit,boolean isDesc) {
        ArrayList<Stock> list=new ArrayList<Stock>();
        StringBuilder sql=new StringBuilder();
        sql.append("SELECT * FROM stock ");
        sql.append("WHERE start>?");
        sql.append(" ORDER BY ");
        sql.append(column);
        sql.append(isDesc?" DESC":" ASC");
        sql.append(" limit ?");

        String[] args={"0",String.valueOf(limit)};

        Cursor c=db.rawQuery(sql.toString(), args);

        boolean eol=c.moveToFirst();

        while(eol){
            list.add(new Stock(
                    c.getString(c.getColumnIndex("code")),
                    c.getString(c.getColumnIndex("name")),
                    c.getFloat(c.getColumnIndex("start")),
                    c.getFloat(c.getColumnIndex("end"))
            ));
           eol= c.moveToNext();
        }

        return list;
    }

}
