package com.example.androidam.stockcsvlesson;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Stock> list=new StockCSVParser(this,"all_2015-08-28.csv").csvParse();

        StockSQLHelper helper=new StockSQLHelper(this,"stock",null,1,list);
        SQLiteDatabase db=helper.getWritableDatabase();
        helper.dao.setDb(db);
        list=helper.dao.select("end",20,true);


        ListView lv=(ListView)findViewById(R.id.listView);

        ArrayAdapter<String> adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,list);

        lv.setAdapter(adapter);

    }


}
