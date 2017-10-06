package com.b05studio.boxstore.view.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.b05studio.boxstore.R;
import com.b05studio.boxstore.model.Subway_Rank;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by PK on 9/20/2016.
 */
public class Rank_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private boolean isHeader;
    private List<Subway_Rank> data;
    private Context context;

    public static final int VIEW_HEADER = 0;
    public static final int VIEW_ITEM = 1;

    public Rank_Adapter(Context context, List<Subway_Rank> data, boolean isHeader){
        this.context=context;
        this.data=data;
        this.isHeader=isHeader;
    }
    public Rank_Adapter(List<Subway_Rank> data){
        this.data = data;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==VIEW_HEADER) {
            return new headView(LayoutInflater.from(context).inflate(R.layout.ranking_item, parent, false));
        }
        else {
            return new itemView(LayoutInflater.from(context).inflate(R.layout.ranking_item, parent, false));
        }
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof headView){
        // ((headView) holder).header.setText("This is Header");
            Log.d("Item View","Binding Item header at "+position);
        }
        else if(holder instanceof itemView){
            Subway_Rank subway_rank = data.get(position-1);
            ((itemView) holder).bindRank(subway_rank);
            Log.d("Item View","Binding Item "+position);
        }
    }



    // to Check the number of item
    @Override
    public int getItemCount() {
        int itemCount=data.size();
        //if header is required then increase the number of count by one
        if(isHeader){
            itemCount=itemCount+1;
        }
        return itemCount;
    }
    public Subway_Rank getName(int position){
        if(isHeader){
            return data.get(position-1);
        }
        else return data.get(position);
    }
    @Override
    public int getItemViewType(int position) {
        if (isHeader && isHeader(position))
            return VIEW_HEADER;

        else return VIEW_ITEM;
    }
    private boolean isFooter(int position) {
        return position==getItemCount()-1;
    }

    private boolean isHeader(int position) { return position == 0;  }

    public class headView extends RecyclerView.ViewHolder {
        protected TextView header;
        public headView(View v) {
            super(v);
            header = (TextView) v.findViewById(R.id.headr);
        }
    }
    public class itemView extends RecyclerView.ViewHolder {


        protected Subway_Rank mSubway_rank;

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
        public void bindRank(Subway_Rank msubway_rank) {
            mSubway_rank = msubway_rank;
            subway_rank.setText(String.valueOf(getAdapterPosition()));
            subway_title.setText(mSubway_rank.getSubway_station());
            subway_volume.setText(String.valueOf(mSubway_rank.getSubway_volume()));
            btn_move_to_stationPage.setImageResource(R.drawable.ic_recycler_right);

        }
    }
}