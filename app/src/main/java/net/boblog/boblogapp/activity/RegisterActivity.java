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
 * Created by huang on 2015/9/13.
 */
public class RegisterActivity extends BaseActivity {
    private EditText et_nickname;
    private EditText et_email;
    private EditText et_password;
    private EditText et_passwordConfirm;
    private Button btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        et_nickname = (EditText) findViewById(R.id.et_register_nickname);
        et_email = (EditText) findViewById(R.id.et_register_email);
        et_password = (EditText) findViewById(R.id.et_register_password);
        et_passwordConfirm = (EditText) findViewById(R.id.et_register_passwordConfirm);
        btn_register = (Button) findViewById(R.id.btn_register_register);
        TextChangeListener textChangeListener = new TextChangeListener();
        et_nickname.addTextChangedListener(textChangeListener);
        et_email.addTextChangedListener(textChangeListener);
        et_password.addTextChangedListener(textChangeListener);
        et_passwordConfirm.addTextChangedListener(textChangeListener);
    }

    /**
     * 点击注册按钮回调方法
     * @param view
     */
    public void onRegister(View view) {
        Toast.makeText(this, "你已经点击注册按钮", Toast.LENGTH_SHORT).show();
    }

    class TextChangeListener implements TextWatcher {
        @Override
        public void afterTextChanged(Editable editable) {}

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            boolean hasNickname = et_nickname.getText().length() > 0;
            boolean hasEmail = et_email.getText().length() > 0;
            boolean hasPassword = et_password.getText().length() > 0;
            boolean hasPasswordConfirm = et_passwordConfirm.getText().length() > 0;
            if (hasNickname && hasEmail && hasPassword && hasPasswordConfirm) {
                btn_register.setEnabled(true);
            } else {
                btn_register.setEnabled(false);
            }
        }
    }
}
