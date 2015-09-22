package net.boblog.boblogapp.component;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

import net.boblog.boblogapp.R;

/**
 * Created by dave on 15-9-21.
 */
public class SwitchButton extends View {

    /** 画笔*/
    private Paint paint ;
    /** 开关状态*/
    private boolean toggleOn = false;
    /** 边框大小*/
    private int borderWidth = 2;
    /** 垂直中心*/
    private float centerY;
    /** 按钮的开始和结束位置*/
    private float startX, endX;
    /** 手柄X位置的最小和最大值*/
    private float spotMinX, spotMaxX;
    /**手柄大小 */
    private int spotSize ;
    /** 手柄X位置*/
    private float spotX;
    /** 关闭时内部灰色带高度*/
    private float offLineWidth;
    /** */
    private float radius;
    /** 开启颜色*/
    private int onColor = Color.parseColor("#4ebb7f");
    /** 关闭颜色*/
    private int offBorderColor = Color.parseColor("#dadbda");
    /** 灰色带颜色*/
    private int offColor = Color.parseColor("#ffffff");
    /** 手柄颜色*/
    private int spotColor = Color.parseColor("#ffffff");
    /** 边框颜色*/
    private int borderColor = offBorderColor;
    /** */
    private RectF rect = new RectF();

    public SwitchButton(Context context) {
        this(context, null);
    }

    public SwitchButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public SwitchButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeCap(Paint.Cap.ROUND);

        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.ToggleButton);
        offBorderColor = typedArray.getColor(R.styleable.ToggleButton_offBorderColor, offBorderColor);
        onColor = typedArray.getColor(R.styleable.ToggleButton_onColor, onColor);
        spotColor = typedArray.getColor(R.styleable.ToggleButton_spotColor, spotColor);
        offColor = typedArray.getColor(R.styleable.ToggleButton_offColor, offColor);
        borderWidth = typedArray.getDimensionPixelSize(R.styleable.ToggleButton_borderWidth, borderWidth);
        typedArray.recycle();

        borderColor = offBorderColor;

        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                toggle();
            }
        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        final int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        final int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        Resources r = Resources.getSystem();
        if(widthMode == MeasureSpec.UNSPECIFIED || widthMode == MeasureSpec.AT_MOST){
            widthSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, r.getDisplayMetrics());
            widthMeasureSpec = MeasureSpec.makeMeasureSpec(widthSize, MeasureSpec.EXACTLY);
        }

        if (heightMode == MeasureSpec.UNSPECIFIED || heightSize == MeasureSpec.AT_MOST){
            heightSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, r.getDisplayMetrics());
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(heightSize, MeasureSpec.EXACTLY);
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right,
                            int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        final int width = getWidth();
        final int height = getHeight();

        radius = Math.min(width, height) * 0.5f;
        centerY = radius;
        startX = radius;
        endX = width - radius;
        spotMinX = startX + borderWidth;
        spotMaxX = endX - borderWidth;
        spotSize = height - 4 * borderWidth;
        spotX = toggleOn ? spotMaxX : spotMinX;
        offLineWidth = spotSize;
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable superState =  super.onSaveInstanceState();
        SavedState ss = new SavedState(superState);
        ss.toggleOn = this.toggleOn;
        return ss;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (!(state instanceof SavedState)) {
            super.onRestoreInstanceState(state);
        } else {
            SavedState ss = (SavedState) state;
            super.onRestoreInstanceState(ss.getSuperState());
            this.toggleOn = ss.toggleOn;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        //
        rect.set(0, 0, getWidth(), getHeight());
        paint.setColor(borderColor);
        canvas.drawRoundRect(rect, radius, radius, paint); //画最外圆角矩形

        if(offLineWidth > 0){
            final float cy = offLineWidth * 0.5f;
            rect.set(spotX - cy, centerY - cy, endX + cy, centerY + cy);
            if (toggleOn) {
                paint.setColor(spotColor);
            } else {
                paint.setColor(offColor);
            }
            canvas.drawRoundRect(rect, cy, cy, paint); //画内层圆角矩形
        }

        rect.set(spotX - 1 - radius, centerY - radius, spotX + 1.1f + radius, centerY + radius);
        paint.setColor(borderColor);
        canvas.drawRoundRect(rect, radius, radius, paint); // 画外层滑动柄

        final float spotR = spotSize * 0.5f;
        rect.set(spotX - spotR, centerY - spotR, spotX + spotR, centerY + spotR);
        paint.setColor(spotColor);
        canvas.drawRoundRect(rect, spotR, spotR, paint); // 画内层滑动柄
    }

    private void startAnimation(final float start, final float end) {
        ValueAnimator animator = ValueAnimator.ofFloat(start, end);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                spotX = spotMinX + (spotMaxX - spotMinX) * value;
                offLineWidth = (1 - value) * spotSize;

                final int fr = Color.red(onColor);
                final int fg = Color.green(onColor);
                final int fb = Color.blue(onColor);

                final int tr = Color.red(offBorderColor);
                final int tg = Color.green(offBorderColor);
                final int tb = Color.blue(offBorderColor);

                int sr = (int)(tr + (fr - tr) * value);
                int sg = (int)(tg + (fg - tg) * value);
                int sb = (int)(tb + (fb - tb) * value);

                sr = clamp(sr, 0, 255);
                sg = clamp(sg, 0, 255);
                sb = clamp(sb, 0, 255);

                borderColor = Color.rgb(sr, sg, sb);

                postInvalidate();

            }
        });
        animator.start();
    }

    private int clamp(int value, int low, int high) {
        return Math.min(Math.max(value, low), high);
    }

    public void toggle() {
        toggle(!toggleOn);
    }

    public void toggle(boolean toggle) {
        toggleOn = toggle;
        if (toggleOn) {
            startAnimation(0, 1);
        } else {
            startAnimation(1, 0);
        }
    }

    public void toggleOn() {
        toggle(true);
    }

    public void toggleOff() {
        toggle(false);
    }

    public static class SavedState extends BaseSavedState {
        boolean toggleOn;

        SavedState(Parcelable superState) {
            super(superState);
        }

        private SavedState(Parcel in) {
            super(in);
            toggleOn = (in.readInt() != 0);
        }

        @Override
        public String toString() {
            return "SwitchButton.SavedState{"
                    + Integer.toHexString(System.identityHashCode(this))
                    + " toggleOn=" + toggleOn + "}";
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            dest.writeInt(toggleOn ? 1 : 0);
        }
    }
}
