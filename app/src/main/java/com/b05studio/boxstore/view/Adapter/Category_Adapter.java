package com.b05studio.boxstore.view.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.b05studio.boxstore.R;
import com.b05studio.boxstore.model.Category;
import com.b05studio.boxstore.view.activity.StationActivity;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by seungwoo on 2017-10-01.
 */

public class Category_Adapter extends RecyclerView.Adapter<Category_Adapter.CategoryViewHolder>{


    private static final String TAG = "Category_Adapter";
    private List<Category> category_list;
    private Context context;

    public Category_Adapter(Context context,List<Category> category_list) {
        this.category_list = category_list;
        this.context = context;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.category_item,viewGroup,false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
        final Category categoryObject = category_list.get(position);
        holder.category_title.setText(categoryObject.getTitle());
        holder.category_ex.setText(categoryObject.getDescription());
        Glide.with(context).load(categoryObject.getImageURL())
                .placeholder(R.drawable.nana_image)
                .into(holder.category_image);
        holder.setOnClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if(isLongClick) {

                }
                else {
                    Log.d(TAG,"onClick");
                    Toast.makeText(context,categoryObject.getTitle(),Toast.LENGTH_SHORT)
                    .show();
                    Intent intent = new Intent(context, StationActivity.class);
                    context.startActivity(intent);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return category_list.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener{

        @BindView(R.id.category_image)
        public ImageView category_image;
        @BindView(R.id.category_title)
        public TextView category_title;
        @BindView(R.id.category_ex)
        public TextView category_ex;

        private ItemClickListener clickListener;

        public CategoryViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            ButterKnife.bind(this, view);
        }

        public void setOnClickListener(ItemClickListener itemClickListener) {
            this.clickListener = itemClickListener;
        }

        @Override
        public void onClick(View view) {
            clickListener.onClick(view, getPosition(), false);
        }
    }
    public interface ItemClickListener {
        void onClick(View view, int position, boolean isLongClick);
    }

}
