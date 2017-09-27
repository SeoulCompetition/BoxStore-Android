package com.b05studio.boxstore.util;

import android.content.Context;
import android.content.Intent;

/**
 * Created by young on 2017-09-25.
 */

public class BaseUtil {

    public static void moveActivity(Context context, Class targetClassName) {
        Intent intent = new Intent(context, targetClassName);
        context.startActivity(intent);
    }


}
