package com.b05studio.boxstore.application;

import android.app.Application;
import android.support.multidex.MultiDexApplication;

import com.b05studio.boxstore.R;
import com.b05studio.boxstore.model.BoxstoreUser;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by young on 2017-09-25.
 */

public class BoxStoreApplication  extends MultiDexApplication {

    private static Retrofit retrofit = null;
    private static final String BOXSTORE_BASIC_URL = "http://52.78.22.122:3000";
    private static  BoxstoreUser currentUser = new BoxstoreUser();

    // 향후 retrofit 객체도 여기

    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/NOTOSANSKR-REGULAR.OTF")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }

    public static Retrofit getRetrofit(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .client(new OkHttpClient().newBuilder()
                            .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                            .addNetworkInterceptor(new StethoInterceptor()).build())
                    .baseUrl(BOXSTORE_BASIC_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }

    public static BoxstoreUser getCurrentUser() {
        return currentUser;
    }
    public static  void setCurrentUser(BoxstoreUser boxstoreUser) {
        currentUser = boxstoreUser;
    }
}
