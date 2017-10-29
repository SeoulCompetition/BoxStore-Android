package com.b05studio.boxstore.view.fragment;

/**
 * Created by seungwoo on 2017-09-25.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.b05studio.boxstore.R;

public class MypageFragment extends Fragment {


    //빈 생성자
    public MypageFragment(){

    }
    public static MypageFragment newInstance(){
        MypageFragment mypageFragment = new MypageFragment();
        return mypageFragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_mypage, container, false);
        return view;
    }
}