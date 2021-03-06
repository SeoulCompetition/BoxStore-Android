package com.b05studio.boxstore.view.fragment;

/**
 * Created by seungwoo on 2017-09-25.
 */

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.b05studio.boxstore.R;
import com.b05studio.boxstore.application.BoxStoreApplication;
import com.b05studio.boxstore.model.Item;
import com.b05studio.boxstore.model.Station;
import com.b05studio.boxstore.model.Stuff;
import com.b05studio.boxstore.service.network.BoxStoreHttpService;
import com.b05studio.boxstore.service.response.RankStationGetResponse;
import com.b05studio.boxstore.service.response.StuffGetResponse;
import com.b05studio.boxstore.view.adapter.HorizonStationAdapter;
import com.b05studio.boxstore.view.adapter.MainProductPagerAdapter;
import com.b05studio.boxstore.view.adapter.RankAdapter;
import com.rd.PageIndicatorView;
import com.rd.animation.type.AnimationType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class HomeFragment extends Fragment {

    @BindView(R.id.boxStoreMenuStationHorizonScrollReyclerView)
    RecyclerView stationHorizonRecyclerView;

    private RecyclerView.Adapter stationAdapter;
    private RecyclerView.LayoutManager stationLayoutManager;
    private List<String> stations = new ArrayList<>();

    @BindView(R.id.boxStoreMenuStationSubHorizonScrollRecyclerView)
    RecyclerView stationSubHorizonReyclerView;

    @BindView(R.id.mainNewStaionProductViewPager)
    ViewPager boxtoreNewMenuStationViewPager;

    @BindView(R.id.mainStaionProductViewPager)
    ViewPager boxtoreMenuStationViewPager;

    MainProductPagerAdapter mainNewProductStationPagerAdapter;
    MainProductPagerAdapter mainProductStationPagerAdapter;

    private ArrayList<Stuff> stuffNewArrayList = new ArrayList<>();
    private ArrayList<Stuff> stuffArrayListByStaionName = new ArrayList<>();

    @BindView(R.id.mainNewStaionProductViewPagerIndicator)
    PageIndicatorView mainNewStaionProductViewPagerIndicator;

    @BindView(R.id.mainStaionProductViewPagerIndicator)
    PageIndicatorView boxtoreMenuStationViewPagerIndicator;



    private static RecyclerView.Adapter subStationAdapter;
    private static RecyclerView.LayoutManager subStationLayoutManager;
    private static List<String> subStations = new ArrayList<>();

    Retrofit homeFragmentRetrofit;

    private static final String TAG = "FragmentStatPgrAdapFrag";
    private MainProductPagerAdapter mPagerAdapter;
    private ArrayList<Item> mImageItemList;


    ViewPager viewPager;
    PageIndicatorView viewIncicator;
    private RecyclerView mRankRecyclerview;
    private RankAdapter rankAdapter;

    public static int selectedIndex = 0;

    //빈생성자
    public HomeFragment() {

    }

    public static HomeFragment newInstance() {
        HomeFragment homeFragment = new HomeFragment();
        return homeFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeFragmentRetrofit = BoxStoreApplication.getRetrofit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);

        mRankRecyclerview = (RecyclerView) view.findViewById(R.id.mainRankRecyclerView);

        initGetLatelyRegistProduct(view);
        initGetStationRegistProduct();
        initStationRecyclerView();
        initStationSubRecyclerView();
        initStationRankSubReycyclerView();
        return view;
    }

    private void initGetLatelyRegistProduct(final View view) {
//        mImageItemList = new ArrayList<>();
//        mImageItemList.addAll(getThumbImageList());

//        stuffArrayList = new ArrayList<>();
        Retrofit retrofit = BoxStoreApplication.getRetrofit();
        Call<StuffGetResponse> stuffGetResponseCall = retrofit.create(BoxStoreHttpService.class).getLatelyProductList();
        stuffGetResponseCall.enqueue(new Callback<StuffGetResponse>() {
            @Override
            public void onResponse(Call<StuffGetResponse> call, Response<StuffGetResponse> response) {
                if (response.isSuccessful()) {
                    List<Stuff> stuffs = response.body().getStuffs();
                    if(stuffs.size() != 0) {
                        mainNewProductStationPagerAdapter = new MainProductPagerAdapter(getFragmentManager(), stuffs);
                        boxtoreNewMenuStationViewPager.setAdapter(mainNewProductStationPagerAdapter);
                        boxtoreNewMenuStationViewPager.setCurrentItem(0);

                        mainNewStaionProductViewPagerIndicator.setViewPager(boxtoreNewMenuStationViewPager);
                        mainNewStaionProductViewPagerIndicator.setSelectedColor(Color.parseColor("#4B65A7"));
                        mainNewStaionProductViewPagerIndicator.setUnselectedColor(Color.parseColor("#F1F1F1"));
                        mainNewStaionProductViewPagerIndicator.setAnimationType(AnimationType.DROP);
                        mainNewProductStationPagerAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<StuffGetResponse> call, Throwable t) {

            }
        });


}

    public void initGetStationRegistProduct() {
        // TODO: 2017. 10. 29. 역이름 으로 요청하는거 만들기
        getStuffListByStaionName("서울역");
    }

    public void getStuffListByStaionName(String name) {

        Retrofit retrofit = BoxStoreApplication.getRetrofit();
        Call<StuffGetResponse> stuffGetResponseCall = retrofit.create(BoxStoreHttpService.class).getStuffListByStationName(name);
        stuffGetResponseCall.enqueue(new Callback<StuffGetResponse>() {
            @Override
            public void onResponse(Call<StuffGetResponse> call, Response<StuffGetResponse> response) {
                if (response.isSuccessful()) {
                    List<Stuff> stuffs = new ArrayList<>();
                    stuffs = response.body().getStuffs();
                    if(stuffs.size() != 0) {
                        mainProductStationPagerAdapter = new MainProductPagerAdapter(getFragmentManager(), stuffs);
                        boxtoreMenuStationViewPager.setAdapter(mainProductStationPagerAdapter);
                        boxtoreMenuStationViewPager.setCurrentItem(0);
                        boxtoreMenuStationViewPagerIndicator.setViewPager(boxtoreMenuStationViewPager);
                        boxtoreMenuStationViewPagerIndicator.setSelectedColor(Color.parseColor("#4B65A7"));
                        boxtoreMenuStationViewPagerIndicator.setUnselectedColor(Color.parseColor("#F1F1F1"));
                        boxtoreMenuStationViewPagerIndicator.setAnimationType(AnimationType.DROP);

                        mainProductStationPagerAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<StuffGetResponse> call, Throwable t) {
                t.printStackTrace();

            }
        });
    }


    private void initStationRecyclerView() {

        for (int i = 1; i < 10; i++) {
            String stationName = i + "호선";
            stations.add(stationName);
        }
        stationLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        stationAdapter = new HorizonStationAdapter(stations);

        stationHorizonRecyclerView.setAdapter(stationAdapter);
        stationHorizonRecyclerView.setLayoutManager(stationLayoutManager);
    }

    private void initStationSubRecyclerView() {
        subStationLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        subStationAdapter = new HorizonStationSubAdapter();
        getStationInformation(stations.get(0));
        stationSubHorizonReyclerView.setAdapter(subStationAdapter);
        stationSubHorizonReyclerView.setLayoutManager(subStationLayoutManager);
    }

    private void initStationRankSubReycyclerView() {

        mRankRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRankRecyclerview.setHasFixedSize(true);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),
                LinearLayoutManager.VERTICAL);
        dividerItemDecoration.setDrawable(getContext().getResources().getDrawable(R.drawable.line_divider));

        mRankRecyclerview.addItemDecoration(dividerItemDecoration);

        Call<RankStationGetResponse> rankStationGetResponseCall = homeFragmentRetrofit.create(BoxStoreHttpService.class).getStaionRankList();
        rankStationGetResponseCall.enqueue(new Callback<RankStationGetResponse>() {
            @Override
            public void onResponse(Call<RankStationGetResponse> call, Response<RankStationGetResponse> response) {
                RankStationGetResponse rankStationGetResponse = response.body();

                if (response.isSuccessful()) {
                    List<Station> stationRank = rankStationGetResponse.getStations();
                    rankAdapter = new RankAdapter(getContext(), stationRank.subList(0,5), true);
                    mRankRecyclerview.setAdapter(rankAdapter);
                }

            }

            @Override
            public void onFailure(Call<RankStationGetResponse> call, Throwable t) {

            }
        });

    }

    static public void getStationInformation(String name) {
        HomeFragment.selectedIndex = 0;
        switch (name) {
            case "1호선":
                subStations = Arrays.asList("서울역", "종로3가", "시청");
                break;
            case "2호선":
                subStations = Arrays.asList("강남", "홍대입구");
                break;
            case "3호선":
                subStations = Arrays.asList("교대", "양재");
                break;
            case "4호선":
                subStations = Arrays.asList("서울역", "혜화", "사당");
                break;
            case "5호선":
                subStations = Arrays.asList("여의도", "동대문");
                break;
            case "6호선":
                subStations = Arrays.asList("합정역", "연신내");
                break;
            case "7호선":
                subStations = Arrays.asList("건대입구", "노원");
                break;
            case "8호선":
                subStations = Arrays.asList("천호", "잠실");
                break;
            case "9호선":
                subStations = Arrays.asList("노량진", "당산");
                break;
            }


        subStationAdapter.notifyDataSetChanged();
    }

    public class HorizonStationSubAdapter extends RecyclerView.Adapter<HorizonStationSubAdapter.ViewHolder> {

        public HorizonStationSubAdapter() {

        }
        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            @BindView(R.id.cardSubStationImageButton)
            ImageButton cardSubImageButton;

            @BindView(R.id.cardSubStationTextView)
            TextView cardSubTextView;

            public ViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
                itemView.setOnClickListener(this);

                cardSubImageButton.setOnClickListener(this);
                cardSubTextView.setOnClickListener(this);

            }

            @Override
            public void onClick(View view) {
                Log.d("OnClick", String.valueOf(selectedIndex));
                if (getAdapterPosition() == RecyclerView.NO_POSITION) return;
                notifyItemChanged(selectedIndex);
                selectedIndex = getAdapterPosition();
                getStuffListByStaionName(subStations.get(selectedIndex));
                notifyItemChanged(selectedIndex);
//                HomeFragment.getStationInformation(stations.get(selectedIndex));
            }
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_sub_station, parent, false);

            return new HorizonStationSubAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            holder.cardSubTextView.setText(subStations.get(position));

            if (selectedIndex == position) {
                holder.cardSubTextView.setTextColor(Color.parseColor("#ffffff"));
                holder.cardSubImageButton.setImageResource(R.drawable.card_station_sub_click);
                getStuffListByStaionName(subStations.get(selectedIndex));

            } else {
                holder.cardSubTextView.setTextColor(Color.parseColor("#4B65A7"));
                holder.cardSubImageButton.setImageResource(R.drawable.card_station_sub_circular);
            }
        }

        @Override
        public int getItemCount() {
            return subStations.size();
        }

    }


}