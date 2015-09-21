package net.boblog.boblogapp.activity;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import net.boblog.boblogapp.R;

/**
 * Created by huang on 2015/9/12.
 */
public class BaseActivity extends Activity {

    /**
     * Activity的返回操作
     * @param view
     */
    public void back(View view) {
        finish();
    }
}
