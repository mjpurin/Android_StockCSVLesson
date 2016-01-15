package com.example.androidam.stockcsvlesson;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;


public class StockCSVParser {
	private final int DATA_START_ROW=2;
	public ArrayList<String> csvList;
	public ArrayList<Stock> list;
	public CSVReader reader;

	public StockCSVParser(Context context, String fileName){
		list=new ArrayList<Stock>();
		reader=new CSVReader(context);
		csvList=reader.readFile(fileName);
	}

	public ArrayList<Stock> csvParse(){
		for(int i = DATA_START_ROW;i < csvList.size();++i){
			Stock st=new Stock();
			// i行目のデータを「,」で分割した配列として取得

			String[] data = csvList.get(i).split(",");
			st.code=data[0];
			st.name=data[1];
			try{
				st.start=Float.parseFloat(data[4]);
			}catch(NumberFormatException e){
				st.start=0;
			}
			try{
				st.end=Float.parseFloat(data[7]);
			}catch(NumberFormatException e){
				st.end=0;
			}
			list.add(st);
		}
		return list;
	}
}
