package com.b05studio.boxstore.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.b05studio.boxstore.R;
import com.b05studio.boxstore.model.Category;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by seungwoo on 2017-10-01.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private static final String TAG = "CategoryAdapter";
    private List<Category> categories;
    private Context context;

    public CategoryAdapter(Context context, List<Category> categories) {
        this.context = context;
        this.categories = categories;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Category categoryObject = categories.get(position);
        holder.categoryTitle.setText(categoryObject.getName());
        holder.categoryDescription.setText(categoryObject.getDescription());
        Glide.with(holder.categoryImage.getContext()).load(categoryObject.getImageUrl())
                .placeholder(R.drawable.nana_image)
                .into(holder.categoryImage);
        holder.categoryConstraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: 2017-10-19 카테고리 상품 들어가게 구현  
                Intent intent = new Intent();
                intent.putExtra("category",categoryObject.getName());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.category_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.categoryCard)
        public ConstraintLayout categoryConstraintLayout;

        @BindView(R.id.category_image)
        public ImageView categoryImage;

        @BindView(R.id.category_title)
        public TextView categoryTitle;

        @BindView(R.id.category_ex)
        public TextView categoryDescription;


        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    }

}
