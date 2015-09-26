package net.boblog.boblogapp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import net.boblog.boblogapp.R;

/**
 * Created by dave on 15-9-26.
 */
public class EditActivity extends BaseActivity {
    private EditText postBody;
    private View editToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
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
    }
}
