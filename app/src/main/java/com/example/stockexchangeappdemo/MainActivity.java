package com.example.stockexchangeappdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.stockexchangeappdemo.Models.ModelStockData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements IStockListHandler {

    private Timer mTimer;
    private TimerTask mTimerTask;

    private RecyclerView mRecyclerView;
    private AdapterStockList mAdapter;
    private List<ModelStockData> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.list_view);

        mData = new ArrayList<>();
        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        mAdapter = new AdapterStockList(mData, this, this);

        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mTimer = new Timer();
        mTimerTask = new TimerTask() {
            @Override
            public void run() {
                getAPIData();
            }
        };
        mTimer.schedule(mTimerTask, 0, 10000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mTimer.cancel();
    }

    private void getAPIData(){

        String url = "https://api.worldtradingdata.com/api/v1/stock?symbol=AAPL,MSFT,HSBA.L&api_token=demo";
        //String url = "https://api.worldtradingdata.com/api/v1/stock?symbol=AAPL,MSFT,HSBA.L&api_token=XXn5pEH5C9TVivZ0Vz75U7iJQAyws3WQLTnzAP1jFRaAd7MA0Fh8uwODlc6o";
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                mData = new ArrayList<>();

                Log.e("Response : ", " -=> " + response);
                try {

                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("data");

                    for (int i = 0; i < array.length(); i++) {

                        JSONObject object = array.getJSONObject(i);

                        ModelStockData data = new ModelStockData();
                        data.setSymbol(object.getString("symbol"));
                        data.setName(object.getString("name"));
                        data.setCurrency(object.getString("currency"));
                        data.setPrice(object.getString("price"));
                        data.setPrice_open(object.getString("price_open"));
                        data.setDay_high(object.getString("day_high"));
                        data.setDay_low(object.getString("day_low"));
                        data.setFive_two_week_high(object.getString("52_week_high"));
                        data.setFive_two_week_low(object.getString("52_week_low"));
                        data.setDay_change(object.getString("day_change"));
                        data.setChange_pct(object.getString("change_pct"));
                        data.setClose_yesterday(object.getString("close_yesterday"));
                        data.setMarket_cap(object.getString("market_cap"));
                        data.setVolume(object.getString("volume"));
                        data.setVolume_avg(object.getString("volume_avg"));
                        data.setShares(object.getString("shares"));
                        data.setStock_ex_long(object.getString("stock_exchange_long"));
                        data.setStock_ex_short(object.getString("stock_exchange_short"));
                        data.setTimezone(object.getString("timezone"));
                        data.setTimezone_name(object.getString("timezone_name"));
                        data.setGmt_offset(object.getString("gmt_offset"));
                        data.setLast_trade_time(object.getString("last_trade_time"));

                        mData.add(data);

                    }

                    mAdapter.swapData(mData);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error : ", " -=> " + error.getMessage());
            }
        });
        MySingleton.getInstance(this).addToRequestQueue(request);

    }

    @Override
    public void onStockSelected(ModelStockData data) {
        Intent intent = new Intent(this, StockDetails.class);
        intent.putExtra("DATA", data);
        startActivity(intent);
    }
}
