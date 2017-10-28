package com.b05studio.boxstore.util;

import android.content.Intent;

/**
 * Created by seungwoo on 2017-10-28.
 */

public class ActivityResultEvent {

    private int requestCode;
    private int resultCode;
    private Intent data;

    public static ActivityResultEvent create(int requestCode, int resultCode, Intent intent) {
        return new ActivityResultEvent(requestCode, resultCode, intent);
    }

    private ActivityResultEvent(int requestCode, int resultCode, Intent data) {
        this.requestCode = requestCode;
        this.resultCode = resultCode;
        this.data = data;
    }

    public int getRequestCode() {
        return requestCode;
    }

    public int getResultCode() {
        return resultCode;
    }

    public Intent getData() {
        return data;
    }
}