package com.example.stockexchangeappdemo.Models;

public class ModelHistoryChilds {

    private String open;
    private String close;
    private String high;
    private String low;
    private String volume;

    public ModelHistoryChilds(String open, String close, String high, String low, String volume) {
        this.open = open;
        this.close = close;
        this.high = high;
        this.low = low;
        this.volume = volume;
    }

    public String getOpen() {
        return open;
    }

    public String getClose() {
        return close;
    }

    public String getHigh() {
        return high;
    }

    public String getLow() {
        return low;
    }

    public String getVolume() {
        return volume;
    }
}
