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

import net.boblog.boblogapp.R;

/**
 * Created by dave on 15-9-21.
 */
public class SwitchButton extends View {

    /** 画笔*/
    private Paint paint ;
    /** 开关状态*/
    private boolean switchOn = false;
    /** 边框大小*/
    private int borderWidth = 1;
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
    /** 手柄滑动时内部圆角矩形radius*/
    private float inRadius;
    /** 手柄半径 */
    private float radius;
    /** 开启颜色*/
    private int onBackgroundColor = Color.parseColor("#45C01A");
    /** 关闭颜色*/
    private int offBackgroundColor = Color.WHITE;
    /** 禁用是背景颜色 */
    private int disabledBackgroundColor = Color.parseColor("#f2f2f2");
    /** 开启时手柄颜色*/
    private int spotOnColor = Color.parseColor("#ffffff");
    /** 开启时手柄颜色*/
    private int spotOffColor = Color.parseColor("#dadbda");
    /** 边框颜色*/
    private int borderColor = spotOffColor;
    /** 是否禁用 */
    private boolean disabled = false;

    private float density;
    /** */
    private RectF rect = new RectF();

    private SwitchChangedListener switchChangedListener;

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
        density = getResources().getDisplayMetrics().density;
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeCap(Paint.Cap.ROUND);

        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.SwitchButton);
        onBackgroundColor = typedArray.getColor(R.styleable.SwitchButton_onBackgroundColor, onBackgroundColor);
        offBackgroundColor = typedArray.getColor(R.styleable.SwitchButton_offBackgroundColor, offBackgroundColor);
        disabledBackgroundColor = typedArray.getColor(R.styleable.SwitchButton_disabledBackgroundColor, disabledBackgroundColor);
        spotOnColor = typedArray.getColor(R.styleable.SwitchButton_spotOnColor, spotOnColor);
        spotOffColor = typedArray.getColor(R.styleable.SwitchButton_spotOffColor, spotOffColor);
        borderColor = typedArray.getColor(R.styleable.SwitchButton_borderColor, borderColor);
        borderWidth = typedArray.getDimensionPixelSize(R.styleable.SwitchButton_borderWidth, borderWidth);
        switchOn = typedArray.getBoolean(R.styleable.SwitchButton_switchOn, switchOn);
        disabled = typedArray.getBoolean(R.styleable.SwitchButton_disabled, disabled);
        typedArray.recycle();

        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if (!disabled) {
                    toggle();
                    if (switchChangedListener != null) {
                        switchChangedListener.onSwitchChanged(SwitchButton.this, SwitchButton.this.switchOn);
                    }
                }
            }
        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        final int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        final int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        Resources r = Resources.getSystem();
        if(widthMode == MeasureSpec.UNSPECIFIED || widthMode == MeasureSpec.AT_MOST){
            int widthSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, r.getDisplayMetrics());
            widthMeasureSpec = MeasureSpec.makeMeasureSpec(widthSize, MeasureSpec.EXACTLY);
        }

        if (heightMode == MeasureSpec.UNSPECIFIED || heightMode == MeasureSpec.AT_MOST){
            int heightSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, r.getDisplayMetrics());
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(heightSize, MeasureSpec.EXACTLY);
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
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
        spotX = switchOn ? spotMaxX : spotMinX;
        inRadius = radius - 2 * borderWidth;
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable superState =  super.onSaveInstanceState();
        SavedState ss = new SavedState(superState);
        ss.toggleOn = this.switchOn;
        return ss;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (!(state instanceof SavedState)) {
            super.onRestoreInstanceState(state);
        } else {
            SavedState ss = (SavedState) state;
            super.onRestoreInstanceState(ss.getSuperState());
            this.switchOn = ss.toggleOn;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        rect.set(0, 0, getWidth(), getHeight());
        if (disabled) {
            paint.setColor(disabledBackgroundColor);
        } else if (switchOn) {
            paint.setColor(onBackgroundColor);
        } else {
            paint.setColor(borderColor);
        }
        canvas.drawRoundRect(rect, radius, radius, paint); //画最外圆角矩形

        if(inRadius > 0 && spotX != spotMaxX){
            rect.set(spotX - inRadius, centerY - inRadius, endX + inRadius, centerY + inRadius);
            if (disabled) {
                paint.setColor(disabledBackgroundColor);
            } else {
                paint.setColor(spotOnColor);
            }
            canvas.drawRoundRect(rect, inRadius, inRadius, paint); //画内层圆角矩形
        }

        final float spotR = spotSize * 0.5f - density * 2;//手柄与外框隔2dp
        rect.set(spotX - spotR, centerY - spotR, spotX + spotR, centerY + spotR);
        if (switchOn) {
            paint.setColor(spotOnColor);
        } else {
            paint.setColor(spotOffColor);
        }
        canvas.drawRoundRect(rect, spotR, spotR, paint); // 画内层滑动柄
    }

    private void startAnimation(final float start, final float end) {
        ValueAnimator animator = ValueAnimator.ofFloat(start, end);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                spotX = spotMinX + (spotMaxX - spotMinX) * value;
                inRadius = (1 - value) * spotSize * 0.5f;
                postInvalidate();

            }
        });
        animator.start();
    }

    private float clamp(float value, float low, float high) {
        return Math.min(Math.max(value, low), high);
    }

    public void toggle() {
        toggle(!switchOn);
    }

    public void toggle(boolean toggle) {
        switchOn = toggle;
        if (switchOn) {
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

    public void disable() {
        if (!disabled) {
            disabled = true;
            postInvalidate();
        }
    }

    public void enable() {
        if (disabled) {
            disabled = false;
            postInvalidate();
        }
    }

    public void setSwitchChangedListener(SwitchChangedListener listener) {
        switchChangedListener = listener;
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
                    + " switchOn=" + toggleOn + "}";
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            dest.writeInt(toggleOn ? 1 : 0);
        }
    }

    public interface SwitchChangedListener {
        public void onSwitchChanged(SwitchButton switchButton, boolean isChecked);
    }
}
