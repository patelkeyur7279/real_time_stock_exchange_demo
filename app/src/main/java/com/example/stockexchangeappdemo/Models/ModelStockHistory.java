package com.example.stockexchangeappdemo.Models;

import java.util.List;

public class ModelStockHistory {

    private String date;
    private ModelHistoryChilds mChild;

    public ModelStockHistory(String date, ModelHistoryChilds mChild) {
        this.date = date;
        this.mChild = mChild;
    }

    public String getDate() {
        return date;
    }

    public ModelHistoryChilds getChild() {
        return mChild;
    }
}