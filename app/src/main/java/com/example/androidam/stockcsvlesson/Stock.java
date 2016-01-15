package com.example.androidam.stockcsvlesson;

/**
 * Created by androidAM on 2015/08/31.
 */
public class Stock {
    String code;
    String name;
    float start;
    float end;

    Stock(){

    }
    Stock(String code,String name,float start,float end){
        this.code=code;
        this.name=name;
        this.start=start;
        this.end=end;
    }

    @Override
    public String toString() {
        return String.format("%s %s,始値:%.2f,終値:%.2f",code,name,start,end);
    }
}
