package com.example.stockexchangeappdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.stockexchangeappdemo.Models.ModelHistoryChilds;
import com.example.stockexchangeappdemo.Models.ModelStockData;
import com.example.stockexchangeappdemo.Models.ModelStockHistory;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

public class StockDetails extends AppCompatActivity {

    private Timer mTimer;
    private TimerTask mTimerTask;

    private ModelStockData mData;
    private List<ModelStockHistory> mHistory;

    private GraphView mGraphView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_details);

        mData = getIntent().getParcelableExtra("DATA");
        setTitle(mData.getName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mGraphView = findViewById(R.id.graph_view);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mTimer = new Timer();
        mTimerTask = new TimerTask() {
            @Override
            public void run() {
                getStockHistory();
            }
        };
        mTimer.schedule(mTimerTask, 0, 10000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mTimer.cancel();
    }

    private void getStockHistory(){

        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        int weeks = (calendar.get(Calendar.WEEK_OF_YEAR)-52);
        calendar.add(Calendar.WEEK_OF_YEAR, weeks);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String fromDate = format.format(calendar.getTime());
        String toDate = format.format(Calendar.getInstance(TimeZone.getDefault()).getTime());

        String historyUrl = "https://api.worldtradingdata.com/api/v1/history?symbol=" + mData.getSymbol()
                + "&api_token=XXn5pEH5C9TVivZ0Vz75U7iJQAyws3WQLTnzAP1jFRaAd7MA0Fh8uwODlc6o&sort=asc";//&date_from=" + fromDate + "&date_to=" + toDate;

        StringRequest request = new StringRequest(historyUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.e("Response : ", " -=> " + response);

                mHistory = new ArrayList<>();

                try {

                    JSONObject jsonObject = new JSONObject(response);

                    JSONObject historyObj = jsonObject.getJSONObject("history");

                    Iterator d = historyObj.keys();

                    JSONArray array = new JSONArray();

                    while (d.hasNext()){
                        array.put((String) d.next());
                    }

                    LineGraphSeries<DataPoint> series = new LineGraphSeries<>();
                    for (int i = 0; i < array.length(); i++) {

                        JSONObject object = historyObj.getJSONObject(array.getString(i));

                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        Date date = new Date();
                        try {
                            date = format.parse(array.getString(i));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        String open = object.getString("open");
                        String close = object.getString("close");
                        String high = object.getString("high");
                        String low = object.getString("low");
                        String volume = object.getString("volume");

                        series.appendData(new DataPoint(date, Float.parseFloat(close)), true, 52);
                        mHistory.add(new ModelStockHistory(array.getString(i),
                                new ModelHistoryChilds(open, close, high, low, volume)));

                    }

                    series.setAnimated(true);

                    mGraphView.scrollTo(mGraphView.getScrollX(), mGraphView.getScrollY());
                    mGraphView.getViewport().setScalableY(false);
                    //mGraphView.getViewport().setScalable(true);
                    mGraphView.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(StockDetails.this));
                    mGraphView.addSeries(series);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        MySingleton.getInstance(this).addToRequestQueue(request);

    }

}
