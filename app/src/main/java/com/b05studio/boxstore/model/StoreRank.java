package com.b05studio.boxstore.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by seungwoo on 2017-09-28.
 */

public class StoreRank {

    private List<Subway_Rank> mStore;

    private static StoreRank sStoreRank;

    public static StoreRank get(Context context) {
        if (sStoreRank == null) {
            sStoreRank = new StoreRank(context);
        }
        return sStoreRank;
    }


    private StoreRank(Context context) {
        mStore = new ArrayList<>();
            Subway_Rank store = new Subway_Rank();
            store.setSubway_station("강남역");
            store.setSubway_volume(12000);
            mStore.add(store);

            Subway_Rank store1 = new Subway_Rank();
            store1.setSubway_station("사당역");
            store1.setSubway_volume(11000);
            mStore.add(store1);

            Subway_Rank store2 = new Subway_Rank();
            store2.setSubway_station("신논혁역");
            store2.setSubway_volume(800);
            mStore.add(store2);

            Subway_Rank store3 = new Subway_Rank();
            store3.setSubway_station("홍대입구역");
            store3.setSubway_volume(603);
            mStore.add(store3);


            Subway_Rank store4 = new Subway_Rank();
            store4.setSubway_station("건대입구역");
            store4.setSubway_volume(586);
            mStore.add(store4);

            Subway_Rank store5 = new Subway_Rank();
            store5.setSubway_station("죽전역");
            store5.setSubway_volume(433);
            mStore.add(store5);
    }
    public List<Subway_Rank> getRanks(){
        return mStore;
    }

}
