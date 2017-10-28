package com.b05studio.boxstore.view.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialcamera.MaterialCamera;
import com.b05studio.boxstore.R;
import com.b05studio.boxstore.application.BoxStoreApplication;
import com.b05studio.boxstore.model.Product;
import com.b05studio.boxstore.service.network.BoxStoreHttpService;
import com.b05studio.boxstore.service.response.BoxtorePostResponse;
import com.b05studio.boxstore.util.FileUtils;
import com.bumptech.glide.Glide;
import com.esafirm.imagepicker.features.camera.CameraModule;
import com.esafirm.imagepicker.features.camera.ImmediateCameraModule;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.zelory.compressor.Compressor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static android.R.attr.data;

public class SellActivity extends AppCompatActivity {

    private static final int SELECT_CATEGORY_BUTTON = 3001;
    Bitmap[] arrayBitmap = new Bitmap[8];

    @BindView(R.id.sellRecyclerview)
    RecyclerView sellImageRecyclerview;

    @OnClick(R.id.sellBackButton)
    public void onClickBackButton() {
        finish();
    }

    @BindView(R.id.sellProductNameEditText)
    EditText productNameEditText;

    @BindView(R.id.sellProductCategoryTextView)
    TextView productCategoryTextView;

    @OnClick(R.id.sellProductCategoryView)
    public void onClickCategoryButton() {
        Intent intent = new Intent(SellActivity.this, CategorySelectActivity.class);
        startActivityForResult(intent,SELECT_CATEGORY_BUTTON);
    }

    @BindView(R.id.sellProductNoOpenStateButton)
    ImageButton noOpenStateButton;
    @BindView(R.id.sellProductNewStateButton)
    ImageButton newStateButton;
    @BindView(R.id.sellProductNotGoodStateButton)
    ImageButton notgoodStateButton;
    private String productState = "";

    @OnClick({R.id.sellProductNoOpenStateButton, R.id.sellProductNewStateButton, R.id.sellProductNotGoodStateButton})
    public void onSelectProductState(ImageButton view) {
        switch (view.getId()) {
            case R.id.sellProductNoOpenStateButton:
                productState = "미개봉";
                noOpenStateButton.setImageResource(R.drawable.ic_sell_no_open_state_check);
                newStateButton.setImageResource(R.drawable.ic_sell_new_state);
                notgoodStateButton.setImageResource(R.drawable.ic_sell_not_good_state);
                break;
            case R.id.sellProductNewStateButton:
                productState = "중고상품";
                noOpenStateButton.setImageResource(R.drawable.ic_sell_no_open_state);
                newStateButton.setImageResource(R.drawable.ic_sell_new_state_check);
                notgoodStateButton.setImageResource(R.drawable.ic_sell_not_good_state);
                break;
            case R.id.sellProductNotGoodStateButton:
                productState = "하자있음";
                noOpenStateButton.setImageResource(R.drawable.ic_sell_no_open_state);
                newStateButton.setImageResource(R.drawable.ic_sell_new_state);
                notgoodStateButton.setImageResource(R.drawable.ic_sell_not_good_state_check);
                break;
        }
    }

    @BindView(R.id.sellProductTypeChange)
    Button typeSellButton;
    @BindView(R.id.sellProductTypeDevide)
    Button typeDivideButton;
    @BindView(R.id.sellProductTypeSell)
    Button typeChangeButton;
    private String postType = "";

    @OnClick({R.id.sellProductTypeChange, R.id.sellProductTypeDevide, R.id.sellProductTypeSell})
    public void onSelectProductType(Button view) {
        switch (view.getId()) {
            case R.id.sellProductTypeChange:
                typeSellButton.setBackgroundResource(R.drawable.button_unclick);
                typeDivideButton.setBackgroundResource(R.drawable.button_click);
                typeChangeButton.setBackgroundResource(R.drawable.button_click);
                typeSellButton.setTextColor(Color.parseColor("#ffffff"));
                typeDivideButton.setTextColor(Color.parseColor("#4B65A7"));
                typeChangeButton.setTextColor(Color.parseColor("#4B65A7"));
                postType = "판매";
                break;
            case R.id.sellProductTypeDevide:
                typeSellButton.setBackgroundResource(R.drawable.button_click);
                typeDivideButton.setBackgroundResource(R.drawable.button_unclick);
                typeChangeButton.setBackgroundResource(R.drawable.button_click);
                typeSellButton.setTextColor(Color.parseColor("#4B65A7"));
                typeDivideButton.setTextColor(Color.parseColor("#ffffff"));
                typeChangeButton.setTextColor(Color.parseColor("#4B65A7"));
                postType = "나눔";
                break;
            case R.id.sellProductTypeSell:
                typeSellButton.setBackgroundResource(R.drawable.button_click);
                typeDivideButton.setBackgroundResource(R.drawable.button_click);
                typeChangeButton.setBackgroundResource(R.drawable.button_unclick);
                typeSellButton.setTextColor(Color.parseColor("#4B65A7"));
                typeDivideButton.setTextColor(Color.parseColor("#4B65A7"));
                typeChangeButton.setTextColor(Color.parseColor("#ffffff"));
                postType = "교환";
                break;
        }
    }

    @BindView(R.id.sellProductDetailEditText)
    EditText productDetailEditText;

    @BindView(R.id.sellProductPriceEdittext)
    EditText productPriceEditText;

    @BindView(R.id.sellProductStationEditText)
    EditText sellProductStationEditText;

    @OnClick(R.id.sellProductRegistButton)
    public void registProductButton() {
        String productName = productNameEditText.getText().toString();
        if (productName.length() == 0) {
            printErrorMessage("상품이름");
            return;
        }

//        String category = productCategoryTextView.getText().toString();
//        if(category.length() == 0) {
//            printErrorMessage("카테고리");
//            return ;
//        }
        String category = "컴퓨터";

        String state = productState;
        if (state.length() == 0) {
            printErrorMessage("상태");
            return;
        }

        String type = postType;
        if (type.length() == 0) {
            printErrorMessage("거래 유형");
            return;
        }

        String detailText = productDetailEditText.getText().toString();
        if (detailText.length() == 0) {
            printErrorMessage("상세 정보");
            return;
        }

        String price = productPriceEditText.getText().toString();
        if (price.length() == 0) {
            printErrorMessage("가격 설정");
            return;
        }

        String station = sellProductStationEditText.getText().toString();
        if (station.length() == 0) {
            printErrorMessage("선호 거래역");
            return;
        }

        UUID uuid = UUID.randomUUID();
        final String productionId = uuid.toString();

        Product product = new Product(BoxStoreApplication.getCurrentUser().getuId(), productName, category, state, type, detailText, price, station, productionId);
        List<MultipartBody.Part> parts = new ArrayList<>();

        for (int i = 0; i < imagePath.size(); i++) {
            parts.add(prepareFilePart("photo", imagePath.get(i)));
        }

        Retrofit retrofit = BoxStoreApplication.getRetrofit();
        Call<BoxtorePostResponse> responseProductBodyCall = retrofit.create(BoxStoreHttpService.class).uplodeProduct(product);
        responseProductBodyCall.enqueue(new Callback<BoxtorePostResponse>() {
            @Override
            public void onResponse(Call<BoxtorePostResponse> call, Response<BoxtorePostResponse> response) {
                // 상품을 먼저올림.
            }

            @Override
            public void onFailure(Call<BoxtorePostResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });

        Call<BoxtorePostResponse> responseProductImagesBodyCall = retrofit.create(BoxStoreHttpService.class).uplodeProductImages(parts, uuid.toString());
        responseProductImagesBodyCall.enqueue(new Callback<BoxtorePostResponse>() {
            @Override
            public void onResponse(Call<BoxtorePostResponse> call, Response<BoxtorePostResponse> response) {
                // 상품을 먼저올림.
            }

            @Override
            public void onFailure(Call<BoxtorePostResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });

//        ArrayList<MultipartBody.Part> images = new ArrayList<>();
//        for (int i = 0 ; i < imagePath.size() ; i++) {
//            File file = new File(imagePath.get(i));
//            RequestBody surveyBody = RequestBody.create(MediaType.parse("image/*"), file);
//            images.add(MultipartBody.Part.createFormData("productPhotoList", file.getName(), surveyBody));
//        }
    }

    public static String getMimeType(String url) {
        String type = null;
        String extension = MimeTypeMap.getFileExtensionFromUrl(url);
        if (extension != null) {
            type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
        }
        return type;
    }

    @NonNull
    private RequestBody createPartFromString(String descriptionString) {
        return RequestBody.create(
                okhttp3.MultipartBody.FORM, descriptionString);
    }

    @NonNull
    private MultipartBody.Part prepareFilePart(String partName, Uri fileUri) {
        // https://github.com/iPaulPro/aFileChooser/blob/master/aFileChooser/src/com/ipaulpro/afilechooser/utils/FileUtils.java
        // use the FileUtils to get the actual file by uri
        File file = FileUtils.getFile(this, fileUri);

        // create RequestBody instance from file
        // file://
        // content://
        RequestBody requestFile =
                RequestBody.create(
                        MediaType.parse("image/*"),
                        file
                );

        // MultipartBody.Part is used to send also the actual file name
        return MultipartBody.Part.createFormData(partName, file.getName(), requestFile);
    }

//    private void upLoadImageAsync(String productId) {
//        FirebaseStorage storage = FirebaseStorage.getInstance();
//        StorageReference storageReference = storage.getReference("product");
//
//        for (int i = 0 ; i < imagePath.size() ; i++) {
//            try {
//                File compressedFile = new Compressor(this).compressToFile(new File(imagePath.get(i)));
//                storageReference.putFile(Uri.fromFile(compressedFile)).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        e.printStackTrace();
//                    }
//                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                    @Override
//                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                        String downLoadUrlLink = taskSnapshot.getDownloadUrl().toString();
//                        // TODO: 2017-10-27 downloadLink 랑 url 업로드.
//                    }
//                });
//            } catch (Exception e) {
//
//            }
//        }
//    }

    private CameraModule cameraModule;

    private static final int RC_CODE_PICKER = 2000;
    private static final int RC_CAMERA = 3000;

    private final static int CAMERA_RQ = 6969;

    int selectedPos = 9;

    RecyclerView.Adapter recyclerViewAdapter;
    RecyclerView.LayoutManager recyclerViewLayoutManager;

    private ArrayList<Uri> imagePath = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);
        ButterKnife.bind(this);
        initImageRecyclerView();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    private void initImageRecyclerView() {
        recyclerViewLayoutManager = new GridLayoutManager(this, 4);
        sellImageRecyclerview.setLayoutManager(recyclerViewLayoutManager);

        recyclerViewAdapter = new ImageAdapter(getApplicationContext());
        sellImageRecyclerview.setAdapter(recyclerViewAdapter);
    }

    public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

        private Context context;

        private List<Uri> imagesPath;

        private ImageAdapter(Context context) {
            this.context = context;
        }

        private ImageAdapter(List<Uri> imagePathList) {
            imagesPath = imagePathList;
        }

        @Override
        public ImageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_add_image, parent, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(final ImageAdapter.ViewHolder holder, final int position) {
            final Bitmap bitmap = arrayBitmap[position];
            holder.addImageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final Activity activity = SellActivity.this;
                    final String[] permissions = new String[]{Manifest.permission.CAMERA};
                    if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(activity, permissions, RC_CAMERA);
                        Log.d("Permission!!!!!!", "PERMIDOJFOSIJFIOSDJOI");
                    } else {
                        new MaterialCamera(SellActivity.this)
                                .stillShot() // launches the Camera in stillshot mode
                                .start(CAMERA_RQ);
                    }
                    selectedPos = holder.getAdapterPosition();
                }
            });
            if (imagePath.size() > 0 && imagePath.size() > position) {
                Glide.with(getApplicationContext())
                        .load(imagesPath.get(position))
                        .into(holder.addImageButton);


                //holder.addImageButton.setImageURI(Uri.parse(imagesPath.get(position)));
                //ChangeBitmap(imagesPath.get(position),holder.addImageButton);
            }
        }

        private void captureImage() {
            Intent intent = getCameraModule().getCameraIntent(SellActivity.this);
            startActivityForResult(intent, RC_CAMERA);
        }

        @Override
        public int getItemCount() {
            return arrayBitmap.length;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            @BindView(R.id.addImageItem)
            ImageButton addImageButton;

            public ViewHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Received recording or error from MaterialCamera
        if (requestCode == CAMERA_RQ) {
            if (resultCode == RESULT_OK) {
                //Toast.makeText(this, "Saved to: " + data.getDataString(), Toast.LENGTH_LONG).show();
                imagePath.add(data.getData());

            } else if (data != null) {
                Exception e = (Exception) data.getSerializableExtra(MaterialCamera.ERROR_EXTRA);
                e.printStackTrace();
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
                imagePath.add(data.getData());
            }
            recyclerViewAdapter = new ImageAdapter(imagePath);
            sellImageRecyclerview.swapAdapter(recyclerViewAdapter, false);
            super.onActivityResult(requestCode, resultCode, data);
        } else if( requestCode == SELECT_CATEGORY_BUTTON) {
            String category = data.getStringExtra("category");
            productCategoryTextView.setText(category);

        }
    }

    private ImmediateCameraModule getCameraModule() {
        if (cameraModule == null) {
            cameraModule = new ImmediateCameraModule();
        }
        return (ImmediateCameraModule) cameraModule;
    }

    private void printErrorMessage(String name) {
        Toast.makeText(getApplicationContext(), name + "란을 확인해주세요.", Toast.LENGTH_SHORT).show();
    }






}
