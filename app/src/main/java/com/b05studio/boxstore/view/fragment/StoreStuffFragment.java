package com.b05studio.boxstore.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.b05studio.boxstore.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by seungwoo on 2017-10-30.
 */

public class StoreStuffFragment extends Fragment {

    @BindView(R.id.bill_photo_btn)
    ImageButton billImageBtn;
    @BindView(R.id.sellProductNameEditText)
    EditText productNameEdit;
    @BindView(R.id.locker_number)
    EditText lockerNumberText;
    @BindView(R.id.locker_password)
    EditText lockerPassWordText;
    @BindView(R.id.etcMessageText)
    EditText etcText;

    //빈생성자
    public StoreStuffFragment() {
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_store_stuff, container, false);
        ButterKnife.bind(view);
        return view;
    }
    @OnClick(R.id.bill_photo_btn)
    public void onClickbillPhoto(){

    }
    @OnClick(R.id.keepNoticeText)
    public void onClickNotice(){

    }
    @OnClick(R.id.seeStoreLocationText)
    public void onClickLoacation(){
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        // 백스택 꼭
        //transaction.add(R.layout.activity_chat,)
    }
    @OnClick(R.id.completeItemStoreBtn)
    public void onClickCompleteStore(){

    }

}
