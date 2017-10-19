package com.b05studio.boxstore.view.adapter;

import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.b05studio.boxstore.R;
import com.b05studio.boxstore.view.fragment.HomeFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by young on 2017-10-20.
 */

public class HorizonStationAdapter extends RecyclerView.Adapter<HorizonStationAdapter.StationViewHolder> {

    private int selectedIndex = 0;
    private List<String> stations = new ArrayList<>();

    public HorizonStationAdapter(List<String> stations) {
        this.stations = stations;
    }

    public class StationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.stationCardContainer)
        ConstraintLayout stationContainer;

        @BindView(R.id.stationName)
        TextView stationName;

        @BindView(R.id.stationBottomView)
        View stationBottomView;

        public StationViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (getAdapterPosition() == RecyclerView.NO_POSITION) return;
            notifyItemChanged(selectedIndex);
            selectedIndex = getAdapterPosition();
            notifyItemChanged(selectedIndex);
            HomeFragment.getStationInformation(stations.get(selectedIndex));
        }
    }

    @Override
    public StationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_station,parent, false);
        return new StationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StationViewHolder holder, final int position) {

        holder.stationName.setText(stations.get(position));

        if(selectedIndex == position) {
            holder.stationName.setTextColor(Color.parseColor("#4B65A7"));
            holder.stationBottomView.setBackgroundColor(Color.parseColor("#4B65A7"));
        } else {
            holder.stationName.setTextColor(Color.parseColor("#B2B2B2"));
            holder.stationBottomView.setBackgroundColor(Color.parseColor("#B2B2B2"));
        }
    }

    @Override
    public int getItemCount() {
        return stations.size();
    }
}