package com.b05studio.boxstore.view.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.b05studio.boxstore.R;
import com.b05studio.boxstore.application.BoxStoreApplication;
import com.b05studio.boxstore.model.Category;
import com.b05studio.boxstore.service.network.BoxStoreHttpService;
import com.b05studio.boxstore.service.response.CategoryGetResponse;
import com.b05studio.boxstore.view.adapter.CategoryAdapter;
import com.bumptech.glide.Glide;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class CategorySelectActivity extends AppCompatActivity {

    private static final String TAG = "CategorySelectActivity";
    private List<Category> categories = new ArrayList<>();

    @BindView(R.id.categorySelectRecyclerView)
    RecyclerView categorySelectRecyclerView;

    private RecyclerView.LayoutManager categorySelectLayoutManager;
    private RecyclerView.Adapter categorySelectAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_select);
        ButterKnife.bind(this);
        initReyclerView();
        getCategoryList();
    }

    private void initReyclerView() {
        categorySelectLayoutManager = new LinearLayoutManager(getApplicationContext());
        categorySelectRecyclerView.setLayoutManager(categorySelectLayoutManager);


    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    private void setImageDrawableByCategoryName() {
        for(int i = 0 ; i < categories.size() ; i++) {
            switch (categories.get(i).getName()) {
                case "여성의류":
                    categories.get(i).setImageUrl(R.drawable.mini_girl);
                    break;
                case "남성의류":
                    categories.get(i).setImageUrl(R.drawable.mini_man);
                    break;
                case "패션/잡화":
                    categories.get(i).setImageUrl(R.drawable.mini_fashion);
                    break;
                case "뷰티/미용":
                    categories.get(i).setImageUrl(R.drawable.mini_beauty);
                    break;
                case "가전/디지털":
                    categories.get(i).setImageUrl(R.drawable.mini_electronic);
                    break;
                case "레져/스포츠":
                    categories.get(i).setImageUrl(R.drawable.mini_sport);
                    break;
                case "생활/문구":
                    categories.get(i).setImageUrl(R.drawable.mini_life);
                    break;
                case "유아/출산":
                    categories.get(i).setImageUrl(R.drawable.mini_baby);
                    break;
                case "반려동물 용품":
                    categories.get(i).setImageUrl(R.drawable.mini_pet);
                    break;
                case "도서/티켓":
                    categories.get(i).setImageUrl(R.drawable.mini_book);
                    break;
            }
        }

    }

    private void getCategoryList() {

        Retrofit retrofit = BoxStoreApplication.getRetrofit();
        Call<CategoryGetResponse> categoryGetResponseCall = retrofit.create(BoxStoreHttpService.class).getCategoryInfo("카테고리");
        categoryGetResponseCall.enqueue(new Callback<CategoryGetResponse>() {
            @Override
            public void onResponse(Call<CategoryGetResponse> call, Response<CategoryGetResponse> response) {
                if(response.isSuccessful()) {
                    categories = response.body().getData();
                    setImageDrawableByCategoryName();
                    categorySelectAdapter = new CategorySelectAdapter(getApplicationContext(),categories);
                    categorySelectRecyclerView.setAdapter(categorySelectAdapter);
                    categorySelectRecyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getApplicationContext()).build());
                    categorySelectAdapter.notifyDataSetChanged();

                } else {

                }
            }

            @Override
            public void onFailure(Call<CategoryGetResponse> call, Throwable t) {

            }
        });
    }

    public class CategorySelectAdapter extends RecyclerView.Adapter<CategorySelectAdapter.ViewHolder> {

        private static final String TAG = "CategorySelectAdapter";
        private List<Category> categories;
        private Context context;

        public CategorySelectAdapter(Context context, List<Category> categories) {
            this.context = context;
            this.categories = categories;
        }

        @Override
        public void onBindViewHolder(final CategorySelectAdapter.ViewHolder holder, int position) {
            final Category categoryObject = categories.get(position);
            holder.categoryTitle.setText(categoryObject.getName());
            Glide.with(holder.categoryImage.getContext()).load(categoryObject.getImageUrl())
                    .into(holder.categoryImage);
            holder.categoryConstraintLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.putExtra("category",holder.categoryTitle.getText().toString());
                    setResult(3001, intent);
                    finish();

//                    // TODO: 2017-10-19 카테고리 상품 들어가게 구현
//                    Intent intent = new Intent(context, CategoryDetailActivity.class);
//                    intent.putExtra("category",categoryObject.getName());
//                    context.startActivity(intent);
                }
            });
        }

        @Override
        public CategorySelectAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_select_category, viewGroup, false);
            return new CategorySelectAdapter.ViewHolder(view);
        }

        @Override
        public int getItemCount() {
            return categories.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            @BindView(R.id.selectCategoryCardListHolder)
            public ConstraintLayout categoryConstraintLayout;

            @BindView(R.id.selectCategoryCardListImageView)
            public ImageView categoryImage;

            @BindView(R.id.selectCategoryCardListTextView)
            public TextView categoryTitle;

            public ViewHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);
            }

        }

    }


}
