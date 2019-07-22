package com.example.stockexchangeappdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stockexchangeappdemo.Models.ModelStockData;

import java.util.List;

interface IStockListHandler {
    void onStockSelected(ModelStockData data);
}

public class AdapterStockList extends RecyclerView.Adapter<AdapterStockList.ViewHolderStockList>{

    private List<ModelStockData> mData;
    private Context mContext;
    private IStockListHandler mInterface;

    public AdapterStockList(List<ModelStockData> mData, Context mContext, IStockListHandler mInterface) {
        this.mData = mData;
        this.mContext = mContext;
        this.mInterface = mInterface;
    }

    @NonNull
    @Override
    public ViewHolderStockList onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_container_stock_list, parent, false);
        return new ViewHolderStockList(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderStockList holder, int position) {

        final ModelStockData data = mData.get(position);

        holder.mStockName.setText(data.getName());
        holder.mPrice.setText("$ "+data.getPrice());
        holder.mDayHigh.setText("Day High : "+data.getDay_high());
        holder.mDayLow.setText("Day Low : "+data.getDay_low());
        holder.mPriceOpen.setText("Price Open : "+data.getPrice_open());
        holder.mLastUpdated.setText("Last Updated : "+data.getLast_trade_time());

        holder.mMainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mInterface != null){
                    mInterface.onStockSelected(data);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        if (mData.isEmpty()){
            return 0;
        }
        return mData.size();
    }

    public void swapData(List<ModelStockData> data){
        mData = data;
        notifyDataSetChanged();
    }

    class ViewHolderStockList extends RecyclerView.ViewHolder{

        TextView mStockName, mPrice, mDayHigh, mDayLow, mPriceOpen, mLastUpdated;
        ConstraintLayout mMainLayout;

        public ViewHolderStockList(@NonNull View itemView) {
            super(itemView);

            mMainLayout = itemView.findViewById(R.id.stock_list_container_view);

            mStockName = itemView.findViewById(R.id.stock_list_title);
            mPrice = itemView.findViewById(R.id.stock_list_price);
            mDayHigh = itemView.findViewById(R.id.stock_list_day_high);
            mDayLow = itemView.findViewById(R.id.stock_list_day_low);
            mPriceOpen = itemView.findViewById(R.id.stock_list_price_open);
            mLastUpdated = itemView.findViewById(R.id.stock_list_last_updated);

        }
    }

}
