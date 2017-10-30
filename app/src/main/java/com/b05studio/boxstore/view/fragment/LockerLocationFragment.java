package com.b05studio.boxstore.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.b05studio.boxstore.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by seungwoo on 2017-10-30.
 */

public class LockerLocationFragment extends Fragment {


    @BindView(R.id.stationMapText)
    TextView stationNameText;
    @BindView(R.id.stationMapDesText)
    TextView stationDesText;
    @OnClick(R.id.popupMap)
    public void onClickPopUp(){
        /*

        View popupView = getLayoutInflater().inflate(R.layout.dialog_activity, null);
        mPopupWindow = new PopupWindow(popupView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        //popupView 에서 (LinearLayout 을 사용) 레이아웃이 둘러싸고 있는 컨텐츠의 크기 만큼 팝업 크기를 지정

        mPopupWindow.setFocusable(true);
        // 외부 영역 선택히 PopUp 종료

        mPopupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);


        Button cancel = (Button) popupView.findViewById(R.id.Cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
            }
        });


*/
    }
    public LockerLocationFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_locker_location, container, false);
        ButterKnife.bind(view);
        return view;
    }
}
