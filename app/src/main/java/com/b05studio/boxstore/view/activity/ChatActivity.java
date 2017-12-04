package com.b05studio.boxstore.view.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialcamera.MaterialCamera;
import com.b05studio.boxstore.R;
import com.b05studio.boxstore.application.BoxStoreApplication;
import com.b05studio.boxstore.service.network.BoxStoreHttpService;
import com.b05studio.boxstore.service.response.BoxtorePostResponse;
import com.b05studio.boxstore.view.dialog.CheckCriminalDialog;
import com.b05studio.boxstore.view.fragment.ChatFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ChatActivity extends AppCompatActivity {

    @BindView(R.id.store_stuff)
    ConstraintLayout storestuffLayout;
    @BindView(R.id.chat_layout)
    ConstraintLayout chatLayout;


    private static final int RC_CAMERA = 3000;
    private final static int CAMERA_RQ = 6969;

    private ArrayList<Uri> imagePath = new ArrayList<>();

    @BindView(R.id.sellProductNameEditText)
    EditText sellProductNameEditText;
    @BindView(R.id.locker_number)
    EditText lock_number;
    @BindView(R.id.locker_password)
    EditText lock_password;
    @BindView(R.id.etcEditText)
    EditText etcEditText;


    @OnClick(R.id.seeStoreLocationText)
    public void onClickStore(){

    }

    @OnClick(R.id.bill_photo_btn)
    public void onClickBilBtn(){
        final Activity activity = ChatActivity.this;
        final String[] permissions = new String[]{Manifest.permission.CAMERA};
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, permissions, RC_CAMERA);
            Log.d("Permission!!!!!!", "PERMIDOJFOSIJFIOSDJOI");
        } else {
            new MaterialCamera(ChatActivity.this)
                    .stillShot() // launches the Camera in stillshot mode
                    .start(CAMERA_RQ);
        }
    }
    @OnClick(R.id.completeItemStoreBtn)
    public void onClickCompleteBtn(){

        final String tradeStationName = sellProductNameEditText.getText().toString();
        final String lockerNumber = lock_number.getText().toString();
        final String lockerPassword = lock_password.getText().toString();
        final String etcEditTextString = etcEditText.getText().toString();
        final String BillURL = imagePath.get(0).toString();

        Intent intent = new Intent();
        intent.putExtra("trade_station",tradeStationName);
        intent.putExtra("locker_number",lockerNumber);
        intent.putExtra("locker_password",lockerPassword);
        intent.putExtra("etc",etcEditTextString);
        intent.putExtra("bill_URL",BillURL);
    }

    @BindView(R.id.opponentName)
    TextView chattalkerName;
    @BindView(R.id.itemInfoImage)
    ImageView itemInfoImage;
    @BindView(R.id.itemInfoName)
    TextView itemInfoName;
    @BindView(R.id.itemInfoPrice)
    TextView itemInfoPrice;

    @BindView(R.id.makeDealBtn)
    TextView makeDealBtn;
    @BindView(R.id.searchCheaterBtn)
    TextView searchCheaterBtn;

    @BindView(R.id.itemInfoView)
    View itemInfo;

    private String sellerUId = "";
    private String buyerUId= "";
    private String sellerName = "";
    private String buyerName = "";
    private String stuffId;
    private String itemName;
    private String IntentType;

    private String userState = "";

    private CheckCriminalDialog checkCriminalDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        storestuffLayout.setVisibility(View.GONE);

        ButterKnife.bind(this);
        Intent intent = getIntent();

        IntentType = intent.getStringExtra("Type");
        if(IntentType != null && IntentType.equals("Notification")){
            // 판매자가 알람을 클릭해서 채팅창으로 들어온 경우
            buyerUId = intent.getStringExtra("BuyerUID");
            buyerName = intent.getStringExtra("BuyerName");
            sellerUId = intent.getStringExtra("SellerUID");
            sellerName = intent.getStringExtra("SellerName");
            stuffId = intent.getStringExtra("stuff_id");
        } else if (IntentType != null && IntentType.equals("Bill")){

            // Activity 화면 체인지
            storestuffLayout.setVisibility(View.VISIBLE);
            chatLayout.setVisibility(View.GONE);

            // 구매자가 문의하기를 클릭하여 방문한 경우
            buyerUId = intent.getStringExtra("BuyerUID");
            buyerName = intent.getStringExtra("BuyerName");
            sellerUId = intent.getStringExtra("SellerUID");
            sellerName = intent.getStringExtra("SellerName");
            stuffId = intent.getStringExtra("stuff_id");

        }else {
            //todo : 구매자도 채팅창에서 클릭해서 오는경우 역시 추가해야 한다 그경우 Type를 Chat으로 설정할것
            // 구매자가 문의하기를 클릭하여 방문한 경우
            buyerUId = intent.getStringExtra("BuyerUID");
            buyerName = intent.getStringExtra("BuyerName");
            sellerUId = intent.getStringExtra("SellerUID");
            sellerName = intent.getStringExtra("SellerName");
            stuffId = intent.getStringExtra("stuff_id");
        }
        //구매자 판매자에 따라 name 표시 바뀌게

        sellerUId = intent.getStringExtra("SellerUID");
        sellerName = intent.getStringExtra("SellerName");
        stuffId = intent.getStringExtra("stuff_id");


        if(sellerUId.equals(BoxStoreApplication.getCurrentUser().getuId())){
            //판매자와 지금 유저 UID 가 같을때 그대는 판매자요
            chattalkerName.setText(buyerName);
            userState = "SELLER";
            makeDealBtn.setVisibility(View.VISIBLE);
            searchCheaterBtn.setVisibility(View.GONE);
        }else {
            chattalkerName.setText(sellerName);
            //구매자와 현재 chatAcitivyt에 들어온 사람의 UID가 같을때
            userState = "BUYER";
            searchCheaterBtn.setVisibility(View.VISIBLE);
            makeDealBtn.setVisibility(View.GONE);
        }

        String itemPrice = intent.getStringExtra("stuff_price");
        itemName = intent.getStringExtra("stuff_name");
        String itemImageURL = intent.getStringExtra("stuff_image");

        if(itemPrice == null && itemName == null && itemImageURL == null){
            itemInfo.setVisibility(View.GONE);
        }

        Picasso.with(ChatActivity.this).load(itemImageURL)
                .into(itemInfoImage);
        if(BoxStoreApplication.getCurrentUser().getName().equals(sellerName))
        {
            //지금 접속한 사람이랑 물건을 팔고자하는 사람이랑 같으니까 구매자 아이디가 툴바창이름

            itemInfoName.setText(buyerName);
        }
        else {
            //지금 접속한 사람이 판매자가 아니므로 판매자의 이름을 툴바창에
            itemInfoName.setText(sellerName);

        }
        itemInfoName.setText("'"+itemName+"'");
        itemInfoPrice.setText(itemPrice+"원");



        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.chat_frame_layout, ChatFragment.newInstance(buyerUId,sellerUId,stuffId,IntentType));
        transaction.commit();
    }
    //판매자만 보인다.
    @OnClick(R.id.makeDealBtn)
    public void onClickDeal(){

        Intent intent = new Intent(this,SellerTransactionActivity.class);
        intent.putExtra("BuyerUID",buyerUId);
        intent.putExtra("SellerUID",sellerUId);
        intent.putExtra("stuff_id",stuffId);
        startActivity(intent);
        /*
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //bundlel로 구매자 UID 판매자 UID 전송, 파이어베이스 데이터베이스 생성에 쓰인다.
        transaction.replace(R.id.chat_frame_layout, SellerTransactionFragment.newInstance(buyerUId,sellerUId,stuffId));
        transaction.addToBackStack(null);
        transaction.commit();
        */
    }
    //구매자만 보인다.
    @OnClick(R.id.searchCheaterBtn)
    public void onClickSearch(){
        // 구매자 사기꾼 조회하는 부분..

        Retrofit retrofit = BoxStoreApplication.getRetrofit();
        Call<BoxtorePostResponse> checkCriminalCall = retrofit.create(BoxStoreHttpService.class).checkCriminal(sellerUId);
        checkCriminalCall.enqueue(new Callback<BoxtorePostResponse>() {
            @Override
            public void onResponse(Call<BoxtorePostResponse> call, Response<BoxtorePostResponse> response) {
                BoxtorePostResponse checkCriminalRes = response.body();
                if(response.isSuccessful()) {
                    Log.d("더치트 api","성공인가");
                    // TODO: 2017. 10. 31. 여기에 메시지 띄우기...!
                    checkCriminalDialog = new CheckCriminalDialog(ChatActivity.this,checkCriminalRes.getMessage());
                    checkCriminalDialog.show();
                }
            }

            @Override
            public void onFailure(Call<BoxtorePostResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }
}
