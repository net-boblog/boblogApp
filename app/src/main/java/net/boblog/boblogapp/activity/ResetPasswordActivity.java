package net.boblog.boblogapp.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import net.boblog.boblogapp.R;

/**
 * Created by huang on 2015/9/14.
 */
public class ResetPasswordActivity extends BaseActivity {
    private EditText et_email;
    private Button btn_resetPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        et_email = (EditText) findViewById(R.id.et_resetPassword_email);
        btn_resetPassword = (Button) findViewById(R.id.btn_resetPassword_resetPassword);
        et_email.addTextChangedListener(new TextChangeListener());
    }

    /**
     * 重置密码按钮点击回调方法
     * @param view
     */
    public void onRestPassword(View view) {
        Toast.makeText(this, "reset password", Toast.LENGTH_SHORT).show();
    }

    class TextChangeListener implements TextWatcher {
        @Override
        public void afterTextChanged(Editable editable) {

        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            boolean hasEmail = et_email.getText().length() > 0;
            if (hasEmail) {
                btn_resetPassword.setEnabled(true);
            } else {
                btn_resetPassword.setEnabled(false);
            }
        }
    }
}
