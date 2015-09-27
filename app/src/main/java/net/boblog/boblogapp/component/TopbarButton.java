package net.boblog.boblogapp.component;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.mikepenz.iconics.view.IconicsButton;

/**
 * Created by dave on 15-9-27.
 */
public class TopbarButton extends IconicsButton {

    public TopbarButton(Context context) {
        super(context);
        init();
    }

    public TopbarButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TopbarButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    protected void init() {
        setBackgroundColor(Color.TRANSPARENT);
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        if (focused) {
            setTextColor(Color.parseColor("#55FFFFFF"));
        } else {
            setTextColor(Color.WHITE);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            setTextColor(Color.parseColor("#55FFFFFF"));
            return true;
        } else if (event.getAction() == MotionEvent.ACTION_UP){
            setTextColor(Color.parseColor("#FFFFFF"));
            return callOnClick();
        }
        return false;
    }
}
