package com.b05studio.boxstore.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.b05studio.boxstore.R;
import com.b05studio.boxstore.model.Station;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by PK on 9/20/2016.
 */
public class RankAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private boolean isHeader;
    private List<Station> data;
    private Context context;

    public static final int VIEW_ITEM = 1;

    public RankAdapter(Context context, List<Station> data, boolean isHeader){
        this.context=context;
        this.data=data;
        this.isHeader=isHeader;
    }
    public RankAdapter(List<Station> data){
        this.data = data;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new itemView(LayoutInflater.from(context).inflate(R.layout.ranking_item, parent, false));
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof itemView){
            Station subway_rank = data.get(position);
            ((itemView) holder).bindRank(subway_rank);
            Log.d("Item View","Binding Item "+position);
        }
    }

    // to Check the number of item
    @Override
    public int getItemCount() {
        int itemCount=data.size();
        return itemCount;
    }
    public Station getName(int position){
        if(isHeader){
            return data.get(position-1);
        }
        else return data.get(position);
    }
    @Override
    public int getItemViewType(int position) {
       return VIEW_ITEM;
    }

    private boolean isFooter(int position) {
        return position==getItemCount()-1;
    }
    private boolean isHeader(int position) { return position == 0;  }

    public class itemView extends RecyclerView.ViewHolder {

        protected Station subwayRank;

        @BindView(R.id.subway_rank)
        protected TextView subway_rank;
        @BindView(R.id.subway_station)
        protected TextView subway_title;
        @BindView(R.id.subway_volume)
        protected TextView subway_volume;
        @BindView(R.id.btn_Move_to_StationPage)
        protected ImageButton btn_move_to_stationPage;

        public itemView(View v) {
            super(v);
            ButterKnife.bind(this,v);
        }
        public void bindRank(Station msubway_rank) {
            subwayRank = msubway_rank;
            subway_rank.setText(String.valueOf(getAdapterPosition()+1));
            subway_title.setText(subwayRank.getStationName());
            subway_volume.setText(String.valueOf(subwayRank.getStuffCount()));
            btn_move_to_stationPage.setImageResource(R.drawable.ic_recycler_right);
        }
    }
}