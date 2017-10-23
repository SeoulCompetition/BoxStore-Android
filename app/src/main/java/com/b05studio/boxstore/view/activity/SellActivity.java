package com.b05studio.boxstore.view.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.afollestad.materialcamera.MaterialCamera;
import com.b05studio.boxstore.R;
import com.esafirm.imagepicker.features.ImagePicker;
import com.esafirm.imagepicker.features.camera.CameraModule;
import com.esafirm.imagepicker.features.camera.ImmediateCameraModule;
import com.esafirm.imagepicker.features.camera.OnImageReadyListener;
import com.esafirm.imagepicker.model.Image;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SellActivity extends AppCompatActivity {

    Bitmap[] arrayBitmap = new Bitmap[8];

    @BindView(R.id.sellRecyclerview)
    RecyclerView sellImageRecyclerview;

    private CameraModule cameraModule;

    private static final int RC_CODE_PICKER = 2000;
    private static final int RC_CAMERA = 3000;

    private final static int CAMERA_RQ = 6969;


    int selectedPos = 9;

    RecyclerView.Adapter recyclerViewAdapter;
    RecyclerView.LayoutManager recyclerViewLayoutManager;

    private ArrayList<Image> images = new ArrayList<>();
    private ArrayList<String> imagePath = new ArrayList<>();


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
        recyclerViewLayoutManager = new GridLayoutManager(this,4);
        sellImageRecyclerview.setLayoutManager(recyclerViewLayoutManager);

        recyclerViewAdapter = new ImageAdapter(getApplicationContext());
        sellImageRecyclerview.setAdapter(recyclerViewAdapter);
    }

    public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

        private Context context;

        private List<String> imagesPath;

        private ImageAdapter(Context context) {
            this.context = context;
        }

        private ImageAdapter(List<String> imagePathList) {
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
                        Log.d("Permission!!!!!!","PERMIDOJFOSIJFIOSDJOI");
                    } else {
                       new MaterialCamera(SellActivity.this)
                               .stillShot() // launches the Camera in stillshot mode
                               .start(CAMERA_RQ);
                    }
                    selectedPos = holder.getAdapterPosition();
                }
            });
            if(imagePath.size() > 0 && imagePath.size() > position){
                holder.addImageButton.setImageURI(Uri.parse(imagesPath.get(position)));
                ChangeBitmap(imagesPath.get(position),holder.addImageButton);
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
                Toast.makeText(this, "Saved to: " + data.getDataString(), Toast.LENGTH_LONG).show();
                imagePath.add(data.getDataString());

            } else if(data != null) {
                Exception e = (Exception) data.getSerializableExtra(MaterialCamera.ERROR_EXTRA);
                e.printStackTrace();
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
                imagePath.add(data.getDataString());
            }
            recyclerViewAdapter = new ImageAdapter(imagePath);
            sellImageRecyclerview.swapAdapter(recyclerViewAdapter,false);
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
    /*
    @Override
    protected void onActivityResult(int requestCode, final int resultCode, Intent data) {
        if (requestCode == RC_CODE_PICKER && resultCode == RESULT_OK && data != null) {
            images = (ArrayList<Image>) ImagePicker.getImages(data);
            //printImages(images);
            return;
        }
        if (requestCode == RC_CAMERA && resultCode == RESULT_OK) {
            getCameraModule().getImage(this, data, new OnImageReadyListener() {
                @Override
                public void onImageReady(List<Image> resultImages) {
                    images = (ArrayList<Image>) resultImages;
                    imagePath.add(resultImages.get(0).getPath());
                    //printImages(images);
                }
            });
        }
        recyclerViewAdapter = new ImageAdapter(imagePath);
        sellImageRecyclerview.swapAdapter(recyclerViewAdapter,false);
        super.onActivityResult(requestCode, resultCode, data);
    }*/

    private ImmediateCameraModule getCameraModule() {
        if (cameraModule == null) {
            cameraModule = new ImmediateCameraModule();
        }
        return (ImmediateCameraModule) cameraModule;
    }

    public int exifOrientationToDegrees(int exifOrientation) {
        if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_90) {
            return 90;
        } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_180) {
            return 180;
        } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_270) {
            return 270;
        }
        return 0;
    }

    public String getRealPathFromURI(Uri contentUri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(contentUri, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);

    }

    public Bitmap rotate(Bitmap src, float degree) {

        // Matrix 객체 생성
        Matrix matrix = new Matrix();
        // 회전 각도 셋팅
        matrix.postRotate(degree);
        // 이미지와 Matrix 를 셋팅해서 Bitmap 객체 생성
        return Bitmap.createBitmap(src, 0, 0, src.getWidth(),
                src.getHeight(), matrix, true);
    }

    private void ChangeBitmap(String path,ImageButton imageView) {

        //Uri imgUri = data.getData();
       // String imagePath = getRealPathFromURI(imgUri); // path 경로
        path = path.replace("file:/","");
        ExifInterface exif = null;
        try {
            exif = new ExifInterface(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int exifOrientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
        int exifDegree = exifOrientationToDegrees(exifOrientation);

        Bitmap bitmap = BitmapFactory.decodeFile(path);//경로를 통해 비트맵으로 전환
        imageView.setImageBitmap(rotate(bitmap, exifDegree));//이미지 뷰에 비트맵 넣기

    }

}
