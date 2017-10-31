package com.b05studio.boxstore.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import com.b05studio.boxstore.R;

/**
 * Created by joyeongje on 2017. 10. 31..
 */

public class CheckCriminalDialog extends Dialog {

    private TextView mTitleView;
    private String mTitle;
    ImageButton exitButton;


    // 클릭버튼이 하나일때 생성자 함수로 클릭이벤트를 받는다.
    public CheckCriminalDialog(Context context, String title) {
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
        this.mTitle = title;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 다이얼로그 외부 화면 흐리게 표현
        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = 0.8f;
        getWindow().setAttributes(lpWindow);

        setContentView(R.layout.dialog_alert_crime);

        mTitleView = (TextView) findViewById(R.id.checkPrimeResultTextView);
        exitButton = (ImageButton) findViewById(R.id.alertDialogCheckCrimeExitButton);

        // 제목과 내용을 생성자에서 셋팅한다.
        mTitleView.setText(mTitle);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

    }



}
