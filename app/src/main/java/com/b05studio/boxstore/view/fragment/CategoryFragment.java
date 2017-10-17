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
import com.b05studio.boxstore.model.Category;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryFragment extends Fragment {

    private static final String TAG = "FragmentCategory";
    private ArrayList<Category> categories;

    @BindView(R.id.category_list)
    RecyclerView recyclerView;

    public CategoryFragment() {

    }

    public static CategoryFragment newInstance() {
        CategoryFragment fragment_category = new CategoryFragment();
        return fragment_category;
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
       // CategoryAdapter mAdapter = new CategoryAdapter(getContext(), getTestData());
        //recyclerView.setAdapter(mAdapter);
    }

    private void getCategoryList() {
        categories = new ArrayList<>();

        // TODO: 2017. 10. 12. 이미지 적용

    }


//    public List<Category> getTestData() {
////        List<Category> category = new ArrayList<Category>();
////        for (int i = 0; i < android_version_names.length; i++) {
////            category.add(new Category(android_image_urls[i], android_version_names[i], android_version_names_description[i]));
////        }
////        return category;
//    }
}