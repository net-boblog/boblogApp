package net.boblog.boblogapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import net.boblog.boblogapp.R;

/**
 * Created by dave on 15-9-21.
 */
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickAddTab(View view) {
        startActivity(new Intent(this, EditActivity.class));
    }

    public void onClickSettingTab(View view) {
        startActivity(new Intent(this, SettingActivity.class));
    }
}
