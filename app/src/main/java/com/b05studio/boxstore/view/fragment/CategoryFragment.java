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
import com.b05studio.boxstore.view.Adapter.CategoryAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryFragment extends Fragment {


    private final String android_version_names[] = {
            "Donut",
            "Eclair",
            "Froyo",
            "Gingerbread",
            "Honeycomb",
            "Ice Cream Sandwich",
            "Jelly Bean",
            "KitKat",
            "Lollipop",
            "Marshmallow"
    };

    private final String android_image_urls[] = {
            "http://api.learn2crack.com/android/images/donut.png",
            "http://api.learn2crack.com/android/images/eclair.png",
            "http://api.learn2crack.com/android/images/froyo.png",
            "http://api.learn2crack.com/android/images/ginger.png",
            "http://api.learn2crack.com/android/images/honey.png",
            "http://api.learn2crack.com/android/images/icecream.png",
            "http://api.learn2crack.com/android/images/jellybean.png",
            "http://api.learn2crack.com/android/images/kitkat.png",
            "http://api.learn2crack.com/android/images/lollipop.png",
            "http://api.learn2crack.com/android/images/marshmallow.png"
    };
    private final String android_version_names_description[] = {
            "Donut ...",
            "Eclair ...",
            "Froyo ...",
            "Gingerbread ...",
            "Honeycomb ...",
            "Ice Cream Sandwich ...",
            "Jelly Bean ...",
            "KitKat ...",
            "Lollipop ...",
            "Marshmallow ..."
    };

    private static final String TAG = "FragmentCategory";
    @BindView(R.id.category_list)
    RecyclerView recyclerView;

    //빈생성자
    public CategoryFragment() {

    }
    public static CategoryFragment newInstance(){
        CategoryFragment fragment_category = new CategoryFragment();
        return fragment_category;
    }
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.category_toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

//
        ButterKnife.bind(this,view);
        GridLayoutManager mGrid = new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(mGrid);
        recyclerView.setHasFixedSize(true);
        CategoryAdapter mAdapter = new CategoryAdapter(getContext(),getTestData());
        recyclerView.setAdapter(mAdapter);

        return view;
    }

    @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

    }
    public List<Category> getTestData() {
        List<Category> category = new ArrayList<Category>();
        for(int i=0;i<android_version_names.length;i++){
            category.add(new Category(android_image_urls[i],android_version_names[i],android_version_names_description[i]));
        }
        return category;
    }
}