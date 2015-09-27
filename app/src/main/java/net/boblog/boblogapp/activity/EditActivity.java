package net.boblog.boblogapp.activity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import net.boblog.boblogapp.R;
import net.boblog.boblogapp.utils.AndroidBug5497Workaround;
import net.boblog.boblogapp.utils.AssetTypeFaces;

/**
 * Created by dave on 15-9-26.
 */
public class EditActivity extends BaseActivity {
    private EditText postBody;
    private View editToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        setContentView(R.layout.activity_edit);
        AndroidBug5497Workaround.assistActivity(this);
        postBody = (EditText) findViewById(R.id.edit_postBody);
        editToolbar = findViewById(R.id.edit_toolbar);
        postBody.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    editToolbar.setVisibility(View.VISIBLE);
                } else {
                    editToolbar.setVisibility(View.GONE);
                }
            }
        });
        postBody.setTypeface(AssetTypeFaces.getTypeface(this, "AppleColorEmoji.ttf"));

    }
}
