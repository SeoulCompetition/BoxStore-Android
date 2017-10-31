package com.b05studio.boxstore.view.adapter;

import android.content.Context;
import android.content.Intent;
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
import com.b05studio.boxstore.model.Stuff;
import com.b05studio.boxstore.view.activity.DetailProductActivity;
import com.b05studio.boxstore.view.fragment.MainStuffFragment;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by seungwoo on 2017-10-02.
 */

public class StationAdapter extends RecyclerView.Adapter<StationAdapter.ItemViewHolder>{

    private static final String TAG = "StationAdapter";
    private List<Stuff> stuffList;
    private Context context;


    public StationAdapter(Context context, List<Stuff> stuffList) {
        this.stuffList = stuffList;
        this.context = context;
    }


    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.selling_item,viewGroup,false);
        return new StationAdapter.ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {

        final Stuff stuff = stuffList.get(position);
        String url = stuff.getSellerId().getPhotoURL();
        if(url != null) {
            Glide.with(context)
                    .load(url)
                    .into(holder.profile_image);
        } else {
            Glide.with(context)
                    .load(R.drawable.ic_empty_profile)
                    .into(holder.profile_image);
        }
        Glide.with(context)
                .load(stuff.getImageUrl().get(0))
                .into(holder.item_image);

        holder.item_title.setText(stuff.getStuffName());
        holder.item_price.setText(String.valueOf(stuff.getPrice()) + " 원");
        holder.profile_name.setText(stuff.getSellerId().getName());
        holder.item_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailProductActivity.class);
                MainStuffFragment.selectedStuff = stuff;
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return stuffList.size();
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
