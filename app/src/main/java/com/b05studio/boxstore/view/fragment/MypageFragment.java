package com.b05studio.boxstore.view.fragment;

/**
 * Created by seungwoo on 2017-09-25.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.b05studio.boxstore.R;
import com.b05studio.boxstore.model.ChatMessage;
import com.b05studio.boxstore.view.adapter.MessageChatAdapter;
import com.b05studio.boxstore.view.adapter.UserRecordAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MypageFragment extends Fragment {

    List<String> mRecordList= new ArrayList<>();

    @BindView(R.id.userRecordRecyclerview)
    RecyclerView userRecordRecyclerView;
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
        ButterKnife.bind(this,view);
        initRecyclerView();
        return view;
    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        userRecordRecyclerView.setLayoutManager(linearLayoutManager);
        userRecordRecyclerView.setHasFixedSize(true);
        //getContext 잘작동하나?
        UserRecordAdapter  userRecordAdapter = new UserRecordAdapter(getContext(),getRecordList());
        userRecordRecyclerView.setAdapter(userRecordAdapter);
    }

    public List<String> getRecordList() {
        mRecordList.add("문의 내역");
        mRecordList.add("구매 내역");
        mRecordList.add("판매 내역");
        mRecordList.add("등록된 상품");
        mRecordList.add("담은 상품");
        mRecordList.add("최근 본 상품");

        return mRecordList;
    }
}