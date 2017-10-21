package com.b05studio.boxstore.view.fragment;

/**
 * Created by seungwoo on 2017-09-25.
 */

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.util.ArraySet;
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
import com.b05studio.boxstore.model.Item;
import com.b05studio.boxstore.model.Station;
import com.b05studio.boxstore.model.StoreRank;
import com.b05studio.boxstore.model.Subway_Rank;
import com.b05studio.boxstore.util.DotIndicator;
import com.b05studio.boxstore.view.adapter.HorizonStationAdapter;
import com.b05studio.boxstore.view.adapter.RankAdapter;
import com.b05studio.boxstore.view.adapter.homeveiw1Adapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends Fragment {

    @BindView(R.id.boxStoreMenuStationHorizonScrollReyclerView)
    RecyclerView stationHorizonRecyclerView;

    private RecyclerView.Adapter stationAdapter;
    private RecyclerView.LayoutManager stationLayoutManager;
    private List<String> stations = new ArrayList<>();

    @BindView(R.id.boxStoreMenuStationSubHorizonScrollRecyclerView)
    RecyclerView stationSubHorizonReyclerView;

    private static RecyclerView.Adapter subStationAdapter;
    private static RecyclerView.LayoutManager subStationLayoutManager;
    private static List<String> subStations = new ArrayList<>();

    private static final String TAG = "FragmentStatPgrAdapFrag";
    private homeveiw1Adapter mPagerAdapter;
    private ArrayList<Item> mImageItemList;
    ViewPager viewPager;
    DotIndicator viewIncicator;
    private RecyclerView mRankRecyclerview;
    private RankAdapter rank_adapter;

    public final static String[] imageThumbUrls = new String[] {
            "https://lh6.googleusercontent.com/-55osAWw3x0Q/URquUtcFr5I/AAAAAAAAAbs/rWlj1RUKrYI/s240-c/A%252520Photographer.jpg",
            "https://lh4.googleusercontent.com/--dq8niRp7W4/URquVgmXvgI/AAAAAAAAAbs/-gnuLQfNnBA/s240-c/A%252520Song%252520of%252520Ice%252520and%252520Fire.jpg",
            "https://lh5.googleusercontent.com/-7qZeDtRKFKc/URquWZT1gOI/AAAAAAAAAbs/hqWgteyNXsg/s240-c/Another%252520Rockaway%252520Sunset.jpg",
            "https://lh3.googleusercontent.com/--L0Km39l5J8/URquXHGcdNI/AAAAAAAAAbs/3ZrSJNrSomQ/s240-c/Antelope%252520Butte.jpg",
            "https://lh6.googleusercontent.com/-8HO-4vIFnlw/URquZnsFgtI/AAAAAAAAAbs/WT8jViTF7vw/s240-c/Antelope%252520Hallway.jpg",
            "https://lh4.googleusercontent.com/-WIuWgVcU3Qw/URqubRVcj4I/AAAAAAAAAbs/YvbwgGjwdIQ/s240-c/Antelope%252520Walls.jpg",
            "https://lh6.googleusercontent.com/-UBmLbPELvoQ/URqucCdv0kI/AAAAAAAAAbs/IdNhr2VQoQs/s240-c/Apre%2525CC%252580s%252520la%252520Pluie.jpg",
            "https://lh3.googleusercontent.com/-s-AFpvgSeew/URquc6dF-JI/AAAAAAAAAbs/Mt3xNGRUd68/s240-c/Backlit%252520Cloud.jpg",
            "https://lh5.googleusercontent.com/-bvmif9a9YOQ/URquea3heHI/AAAAAAAAAbs/rcr6wyeQtAo/s240-c/Bee%252520and%252520Flower.jpg",
            "https://lh5.googleusercontent.com/-n7mdm7I7FGs/URqueT_BT-I/AAAAAAAAAbs/9MYmXlmpSAo/s240-c/Bonzai%252520Rock%252520Sunset.jpg",
    };

    public final static String[] imageUrls = new String[] {
            "https://lh6.googleusercontent.com/-h-ALJt7kSus/URqvIThqYfI/AAAAAAAAAbs/ejiv35olWS8/s1024/Tokyo%252520Heights.jpg",
            "https://lh5.googleusercontent.com/-Hy9k-TbS7xg/URqvIjQMOxI/AAAAAAAAAbs/RSpmmOATSkg/s1024/Tokyo%252520Highway.jpg",
            "https://lh6.googleusercontent.com/-83oOvMb4OZs/URqvJL0T7lI/AAAAAAAAAbs/c5TECZ6RONM/s1024/Tokyo%252520Smog.jpg",
            "https://lh3.googleusercontent.com/-FB-jfgREEfI/URqvJI3EXAI/AAAAAAAAAbs/XfyweiRF4v8/s1024/Tufa%252520at%252520Night.jpg",
            "https://lh4.googleusercontent.com/-vngKD5Z1U8w/URqvJUCEgPI/AAAAAAAAAbs/ulxCMVcU6EU/s1024/Valley%252520Sunset.jpg",
//            "https://lh6.googleusercontent.com/-DOz5I2E2oMQ/URqvKMND1kI/AAAAAAAAAbs/Iqf0IsInleo/s1024/Windmill%252520Sunrise.jpg",
//            "https://lh5.googleusercontent.com/-biyiyWcJ9MU/URqvKculiAI/AAAAAAAAAbs/jyPsCplJOpE/s1024/Windmill.jpg",
//            "https://lh4.googleusercontent.com/-PDT167_xRdA/URqvK36mLcI/AAAAAAAAAbs/oi2ik9QseMI/s1024/Windmills.jpg",
//            "https://lh5.googleusercontent.com/-kI_QdYx7VlU/URqvLXCB6gI/AAAAAAAAAbs/N31vlZ6u89o/s1024/Yet%252520Another%252520Rockaway%252520Sunset.jpg",
//            "https://lh4.googleusercontent.com/-e9NHZ5k5MSs/URqvMIBZjtI/AAAAAAAAAbs/1fV810rDNfQ/s1024/Yosemite%252520Tree.jpg",
    };



    public static ArrayList<Item> getThumbImageList(){
        ArrayList<Item> imageThumbsList = new ArrayList<>();

        for (int i = 0; i < imageThumbUrls.length; i++) {
            imageThumbsList.add(new Item(imageThumbUrls[i],"GTX 1060","200,000"));
        }

        return imageThumbsList;
    }

    //빈생성자
    public HomeFragment(){

    }

    public static HomeFragment newInstance(){
        HomeFragment homeFragment = new HomeFragment();
        return homeFragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);

        mRankRecyclerview = (RecyclerView) view.findViewById(R.id.home_view_2);
        mRankRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRankRecyclerview.setHasFixedSize(true);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),
                LinearLayoutManager.VERTICAL);
        dividerItemDecoration.setDrawable(getContext().getResources().getDrawable(R.drawable.line_divider));

        mRankRecyclerview.addItemDecoration(dividerItemDecoration);

        mImageItemList = new ArrayList<>();
        mImageItemList.addAll(getThumbImageList());

        mPagerAdapter = new homeveiw1Adapter(getChildFragmentManager(), mImageItemList);

        viewPager = view.findViewById(R.id.home_vp_1);
        viewPager.setAdapter(mPagerAdapter);
        viewPager.setCurrentItem(0);

        // viewIncicator = view.findViewById(R.id.viewIncicator);
        //viewIncicator.setViewPager(viewPager);

        updateUI();
        initStationRecyclerView();
        initStationSubRecyclerView();
        return view;
    }

    private void updateUI() {
        StoreRank storeRank = StoreRank.get(getActivity());
        List<Subway_Rank> stores = storeRank.getRanks();

        rank_adapter = new RankAdapter(getActivity(),stores,true);
        mRankRecyclerview.setAdapter(rank_adapter);
    }

    private void initStationRecyclerView() {

        for(int i = 1 ; i < 10 ; i++) {
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

    static public void getStationInformation(String name) {
        switch (name) {
            case "1호선":
                subStations = Arrays.asList("서울역","종로3가","시청");
                break;
            case "2호선":
                subStations = Arrays.asList("강남","홍대입구");
                break;
            case "3호선":
                subStations = Arrays.asList("교대","양재");
                break;
            case "4호선":
                subStations = Arrays.asList("서울역","혜화","사당");
                break;
            case "5호선":
                subStations = Arrays.asList("여의도","동대문");
                break;
            case "6호선":
                subStations = Arrays.asList("합정역","연신내");
                break;
            case "7호선":
                subStations = Arrays.asList("건대입구","노원");
                break;
            case "8호선":
                subStations = Arrays.asList("천호","잠실");
                break;
            case "9호선":
                subStations = Arrays.asList("노량진","당산");
                break;
        }
        subStationAdapter.notifyDataSetChanged();
    }

    public class HorizonStationSubAdapter extends RecyclerView.Adapter<HorizonStationSubAdapter.SubStationViewHolder> {

        private int selectedIndex = 0;

        public class SubStationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            @BindView(R.id.cardSubStationImageButton)
            ImageButton cardSubImageButton;

            @BindView(R.id.cardSubStationTextView)
            TextView cardSubTextView;

            public SubStationViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View view) {
                if (getAdapterPosition() == RecyclerView.NO_POSITION) return;
                notifyItemChanged(selectedIndex);
                selectedIndex = getAdapterPosition();
                notifyItemChanged(selectedIndex);
                HomeFragment.getStationInformation(stations.get(selectedIndex));
                // TODO: 2017-10-20 상점 정보 나오게하기
            }
                                                                                                                                    }
        @Override
        public SubStationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_sub_station,parent, false);
            return new HorizonStationSubAdapter.SubStationViewHolder(view);
        }

        @Override
        public void onBindViewHolder(SubStationViewHolder holder, int position) {
            holder.cardSubTextView.setText(subStations.get(position));

            if(selectedIndex == position) {
                holder.cardSubTextView.setTextColor(Color.parseColor("#ffffff"));
                holder.cardSubImageButton.setImageResource(R.drawable.card_station_sub_click);
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