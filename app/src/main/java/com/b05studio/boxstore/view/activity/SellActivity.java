package com.b05studio.boxstore.view.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.b05studio.boxstore.R;

import java.util.ArrayList;
import java.util.List;


import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SellActivity extends AppCompatActivity {

    Bitmap[] arrayBitmap = new Bitmap[8];

    @BindView(R.id.sellRecyclerview)
    RecyclerView recyclerView;

    RecyclerView.Adapter recyclerViewAdapter;
    RecyclerView.LayoutManager recyclerViewLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);
        ButterKnife.bind(this);

        initImageRecyclerView();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    private void initImageRecyclerView() {
        recyclerViewLayoutManager = new GridLayoutManager(this,4);
        recyclerView.setLayoutManager(recyclerViewLayoutManager);

        recyclerViewAdapter = new ImageAdapter(getApplicationContext());
        recyclerView.setAdapter(recyclerViewAdapter);

    }

    public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

        private Context context;

        private ImageAdapter(Context context) {
            this.context = context;
        }

        @Override
        public ImageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_add_image, parent, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(ImageAdapter.ViewHolder holder, int position) {
            final Bitmap bitmap = arrayBitmap[position];
            holder.addImageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // TODO: 2017-10-03 여기에 갤러리 불러와서 다시넣는 작업 해야됨.
                }
            });
        }

        @Override
        public int getItemCount() {
            return arrayBitmap.length;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            @BindView(R.id.addImageItem)
            ImageButton addImageButton;

            public ViewHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);
            }
        }
    }



}
