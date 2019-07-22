package com.example.stockexchangeappdemo.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class ModelStockData implements Parcelable {

    private String symbol;
    private String name;
    private String currency;
    private String price;
    private String price_open;
    private String day_high;
    private String day_low;
    private String five_two_week_high;
    private String five_two_week_low;
    private String day_change;
    private String change_pct;
    private String close_yesterday;
    private String market_cap;
    private String volume;
    private String volume_avg;
    private String shares;
    private String stock_ex_long;
    private String stock_ex_short;
    private String timezone;
    private String timezone_name;
    private String gmt_offset;
    private String last_trade_time;

    public ModelStockData() {
    }

    protected ModelStockData(Parcel in) {
        symbol = in.readString();
        name = in.readString();
        currency = in.readString();
        price = in.readString();
        price_open = in.readString();
        day_high = in.readString();
        day_low = in.readString();
        five_two_week_high = in.readString();
        five_two_week_low = in.readString();
        day_change = in.readString();
        change_pct = in.readString();
        close_yesterday = in.readString();
        market_cap = in.readString();
        volume = in.readString();
        volume_avg = in.readString();
        shares = in.readString();
        stock_ex_long = in.readString();
        stock_ex_short = in.readString();
        timezone = in.readString();
        timezone_name = in.readString();
        gmt_offset = in.readString();
        last_trade_time = in.readString();
    }

    public static final Creator<ModelStockData> CREATOR = new Creator<ModelStockData>() {
        @Override
        public ModelStockData createFromParcel(Parcel in) {
            return new ModelStockData(in);
        }

        @Override
        public ModelStockData[] newArray(int size) {
            return new ModelStockData[size];
        }
    };

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setPrice_open(String price_open) {
        this.price_open = price_open;
    }

    public void setDay_high(String day_high) {
        this.day_high = day_high;
    }

    public void setDay_low(String day_low) {
        this.day_low = day_low;
    }

    public void setFive_two_week_high(String five_two_week_high) {
        this.five_two_week_high = five_two_week_high;
    }

    public void setFive_two_week_low(String five_two_week_low) {
        this.five_two_week_low = five_two_week_low;
    }

    public void setDay_change(String day_change) {
        this.day_change = day_change;
    }

    public void setChange_pct(String change_pct) {
        this.change_pct = change_pct;
    }

    public void setClose_yesterday(String close_yesterday) {
        this.close_yesterday = close_yesterday;
    }

    public void setMarket_cap(String market_cap) {
        this.market_cap = market_cap;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public void setVolume_avg(String volume_avg) {
        this.volume_avg = volume_avg;
    }

    public void setShares(String shares) {
        this.shares = shares;
    }

    public void setStock_ex_long(String stock_ex_long) {
        this.stock_ex_long = stock_ex_long;
    }

    public void setStock_ex_short(String stock_ex_short) {
        this.stock_ex_short = stock_ex_short;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public void setTimezone_name(String timezone_name) {
        this.timezone_name = timezone_name;
    }

    public void setGmt_offset(String gmt_offset) {
        this.gmt_offset = gmt_offset;
    }

    public void setLast_trade_time(String last_trade_time) {
        this.last_trade_time = last_trade_time;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public String getCurrency() {
        return currency;
    }

    public String getPrice() {
        return price;
    }

    public String getPrice_open() {
        return price_open;
    }

    public String getDay_high() {
        return day_high;
    }

    public String getDay_low() {
        return day_low;
    }

    public String getFive_two_week_high() {
        return five_two_week_high;
    }

    public String getFive_two_week_low() {
        return five_two_week_low;
    }

    public String getDay_change() {
        return day_change;
    }

    public String getChange_pct() {
        return change_pct;
    }

    public String getClose_yesterday() {
        return close_yesterday;
    }

    public String getMarket_cap() {
        return market_cap;
    }

    public String getVolume() {
        return volume;
    }

    public String getVolume_avg() {
        return volume_avg;
    }

    public String getShares() {
        return shares;
    }

    public String getStock_ex_long() {
        return stock_ex_long;
    }

    public String getStock_ex_short() {
        return stock_ex_short;
    }

    public String getTimezone() {
        return timezone;
    }

    public String getTimezone_name() {
        return timezone_name;
    }

    public String getGmt_offset() {
        return gmt_offset;
    }

    public String getLast_trade_time() {
        return last_trade_time;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(symbol);
        parcel.writeString(name);
        parcel.writeString(currency);
        parcel.writeString(price);
        parcel.writeString(price_open);
        parcel.writeString(day_high);
        parcel.writeString(day_low);
        parcel.writeString(five_two_week_high);
        parcel.writeString(five_two_week_low);
        parcel.writeString(day_change);
        parcel.writeString(change_pct);
        parcel.writeString(close_yesterday);
        parcel.writeString(market_cap);
        parcel.writeString(volume);
        parcel.writeString(volume_avg);
        parcel.writeString(shares);
        parcel.writeString(stock_ex_long);
        parcel.writeString(stock_ex_short);
        parcel.writeString(timezone);
        parcel.writeString(timezone_name);
        parcel.writeString(gmt_offset);
        parcel.writeString(last_trade_time);
    }
}
