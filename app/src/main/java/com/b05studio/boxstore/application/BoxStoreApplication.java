package com.b05studio.boxstore.application;

import android.app.Application;

import com.b05studio.boxstore.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by young on 2017-09-25.
 */

public class BoxStoreApplication extends Application {

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
}
