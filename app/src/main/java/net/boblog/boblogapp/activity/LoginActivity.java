package net.boblog.boblogapp.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import net.boblog.boblogapp.R;

/**
 * Created by huang on 2015/9/12.
 */
public class LoginActivity extends BaseActivity {
    private ImageView iv_logo;
    private EditText et_username;
    private EditText et_password;
    private Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_login);
        iv_logo = (ImageView) findViewById(R.id.iv_login_logo);
        et_username = (EditText) findViewById(R.id.et_login_username);
        et_password = (EditText) findViewById(R.id.et_login_password);
        btn_login = (Button) findViewById(R.id.btn_login_login);
    }

    @Override
    protected void onResume() {
        super.onResume();
        iv_logo.post(new Runnable() {
            @Override
            public void run() {
                DisplayMetrics dm = new DisplayMetrics();
                LoginActivity.this.getWindowManager().getDefaultDisplay().getMetrics(dm);
                int[] originalPos = new int[2];
                iv_logo.getLocationOnScreen(originalPos);
                TranslateAnimation translateAnimation = new TranslateAnimation(0, 0, -(originalPos[1] * 0.5f), 0);
                ScaleAnimation scaleAnimation = new ScaleAnimation(0.5f, 1f, 0.5f, 1f, Animation.RELATIVE_TO_SELF,
                        0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                AnimationSet animationSet = new AnimationSet(true);
                animationSet.setDuration(500);
                animationSet.setInterpolator(new DecelerateInterpolator());
                animationSet.setFillAfter(true);
                animationSet.addAnimation(translateAnimation);
                animationSet.addAnimation(scaleAnimation);
                iv_logo.startAnimation(animationSet);
//                iv_logo.animate().translationYBy(-(originalPos[1] * 0.5f)).scaleX(1.5f).scaleY(1.5f).setDuration(400)
//                        .setInterpolator(new DecelerateInterpolator()).start();
            }
        });
    }

    public void onLogin(View view) {
        boolean hasError = false;

        if (et_username.getText().length() == 0) {
            et_username.setError("请输入用户名");
            hasError = true;
        }

        if (et_password.getText().length() == 0) {
            et_password.setError("请输入密码");
            hasError = true;
        }

        if (hasError) {
            return;
        } else {
            startActivity(new Intent(this, MainActivity.class));
        }
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
}
