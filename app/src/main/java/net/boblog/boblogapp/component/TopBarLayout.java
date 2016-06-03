package net.boblog.boblogapp.component;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import net.boblog.boblogapp.utils.UiTools;

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

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
////            int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
////            int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec) + UiTools.getStatusBarHeight(getContext());
////            heightMeasureSpec = MeasureSpec.makeMeasureSpec(heightSpecSize, heightSpecMode);
//            heightMeasureSpec += UiTools.getStatusBarHeight(getContext());
//        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

//    @Override
//    public void setLayoutParams(ViewGroup.LayoutParams params) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
//            params.height = params.height + UiTools.getStatusBarHeight(getContext());
//        }
//        super.setLayoutParams(params);
//    }

    private void setStatusBar() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
//            setPadding(getPaddingLeft(), getPaddingTop() + UiTools.getStatusBarHeight(getContext()), getPaddingRight(), getPaddingBottom());
//        }
    }
}
