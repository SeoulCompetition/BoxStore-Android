package com.b05studio.boxstore.view.fragment;

/**
 * Created by seungwoo on 2017-09-25.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.b05studio.boxstore.R;
import com.b05studio.boxstore.application.BoxStoreApplication;
import com.b05studio.boxstore.model.Category;
import com.b05studio.boxstore.service.network.BoxStoreHttpService;
import com.b05studio.boxstore.service.response.CategoryGetResponse;
import com.b05studio.boxstore.view.adapter.CategoryAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CategoryFragment extends Fragment {

    private static final String TAG = "FragmentCategory";
    private List<Category> categories;

    CategoryAdapter mAdapter;

    @BindView(R.id.category_list)
    RecyclerView recyclerView;

    public CategoryFragment() {

    }

    public static CategoryFragment newInstance() {
        return new CategoryFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.category_toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ButterKnife.bind(this, view);

        initReyclerView();
        getCategoryList();

        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

    }

    private void initReyclerView() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
    }

    private void setAdapterToRecyclerView() {
        mAdapter = new CategoryAdapter(getContext(), categories);
        recyclerView.setAdapter(mAdapter);
    }

    private void setImageDrawableByCategoryName() {
        for(int i = 0 ; i < categories.size() ; i++) {
            switch (categories.get(i).getName()) {
                // TODO: 2017. 10. 18. 마저처리
                case "여성 의류":
                    categories.get(i).setImageUrl(R.drawable.ic_category_girl);
                    break;
                case "남성 의류":
                    categories.get(i).setImageUrl(R.drawable.ic_category_man);
                    break;
                case "패션잡화":
                    categories.get(i).setImageUrl(R.drawable.ic_category_fashion_things);
                    break;
                case "뷰티/미용":
                    categories.get(i).setImageUrl(R.drawable.ic_category_beautify);
                    break;
                case "가전/디지털":
                    categories.get(i).setImageUrl(R.drawable.ic_category_electronic);
                    break;
                case "레져/스포츠":
                    categories.get(i).setImageUrl(R.drawable.ic_category_sport);
                    break;
                case "생활/문구":
                    categories.get(i).setImageUrl(R.drawable.ic_category_life);
                    break;
                case "유아/출산":
                    categories.get(i).setImageUrl(R.drawable.ic_category_baby);
                    break;
                case "반려동물 용품":
                    categories.get(i).setImageUrl(R.drawable.ic_category_pet);
                    break;
                case "도서/티켓/음반":
                    categories.get(i).setImageUrl(R.drawable.ic_category_book);
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
                    setAdapterToRecyclerView();
                } else {

                }
            }

            @Override
            public void onFailure(Call<CategoryGetResponse> call, Throwable t) {

            }
        });
    }




}