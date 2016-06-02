package net.boblog.boblogapp.activity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.EditText;

import net.boblog.boblogapp.R;
import net.boblog.boblogapp.utils.AssetTypeFaces;

/**
 * Created by dave on 15-9-26.
 */
public class EditActivity extends BaseActivity {
    private EditText postBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_edit);
        postBody = (EditText) findViewById(R.id.edit_postBody);
        postBody.setTypeface(AssetTypeFaces.getTypeface(this, "AppleColorEmoji.ttf"));
    }
}
