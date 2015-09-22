package net.boblog.boblogapp.component;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by dave on 15-9-22.
 */
public class TopBarLayout extends RelativeLayout {
    public TopBarLayout(Context context) {
        super(context);
        setStatusBar();
    }

    public TopBarLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setStatusBar();
    }

    public TopBarLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setStatusBar();
    }

    @TargetApi(21)
    public TopBarLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec + getStatusBarHeight());
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    private void setStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            setPadding(getPaddingLeft(), getPaddingTop() + getStatusBarHeight(), getPaddingRight(), getPaddingBottom());
        }
    }

    /**
     * 获取顶部状态栏高度
     * @return
     */
    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}
