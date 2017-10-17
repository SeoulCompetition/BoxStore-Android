package com.b05studio.boxstore.view.adapter;

import android.content.Context;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.b05studio.boxstore.R;
import com.b05studio.boxstore.model.Item;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by seungwoo on 2017-10-02.
 */

public class StationAdapter extends RecyclerView.Adapter<StationAdapter.ItemViewHolder>{

    private static final String TAG = "StationAdapter";
    private List<Item> item_list;
    private Context context;



    public StationAdapter(Context context, List<Item> item_list) {
        this.item_list = item_list;
        this.context = context;
    }


    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.selling_item,viewGroup,false);
        return new StationAdapter.ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {

        Item ItemObject = item_list.get(position);
        Glide.with(context)
                .load(ItemObject.getUserProfileURL())
                .placeholder(R.drawable.nana_image)
                .into(holder.profile_image);
        Glide.with(context)
                .load(ItemObject.getItemURL())
                .placeholder(R.drawable.nana_image)
                .into(holder.item_image);
        holder.item_title.setText(ItemObject.getItemTitle());
        holder.item_price.setText(ItemObject.getItemPrice());
        holder.profile_name.setText(ItemObject.getUserName());
    }

    @Override
    public int getItemCount() {
        return item_list.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.profile_image)
        ImageView profile_image;
        @BindView(R.id.profile_name)
        TextView profile_name;
        @BindView(R.id.item_image)
        ImageView item_image;
        @BindView(R.id.item_title)
        TextView item_title;
        @BindView(R.id.item_price)
        TextView item_price;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            profile_image.setBackground(new ShapeDrawable(new OvalShape()));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                profile_image.setClipToOutline(true);
            }
        }
    }
}
