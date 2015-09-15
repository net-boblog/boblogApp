package net.boblog.boblogapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import net.boblog.boblogapp.R;

/**
 * Created by huang on 2015/9/12.
 */
public class LoginActivity extends BaseActivity {
    private EditText et_username;
    private EditText et_password;
    private Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        et_username = (EditText) findViewById(R.id.et_login_username);
        et_password = (EditText) findViewById(R.id.et_login_password);
        btn_login = (Button) findViewById(R.id.btn_login_login);
        TextChangeListener textChangeListener = new TextChangeListener();
        et_username.addTextChangedListener(textChangeListener);
        et_password.addTextChangedListener(textChangeListener);
    }

    public void onLogin(View view) {
        Toast.makeText(this, "你已经点击登陆按钮", Toast.LENGTH_SHORT).show();
    }

    /**
     * 点击“注册账户”回调方法
     * @param view
     */
    public void onRegister(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    /**
     * 点击“忘记密码”回调方法
     * @param view
     */
    public void onResetPassword(View view) {
        startActivity(new Intent(this, ResetPasswordActivity.class));
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
            boolean hasUsername = et_username.getText().length() > 0;
            boolean hasPassword= et_password.getText().length() > 0;
            if (hasUsername && hasPassword) {
                btn_login.setEnabled(true);
            } else {
                btn_login.setEnabled(false);
            }
        }
    }
}
