package net.boblog.boblogapp.component;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.util.Log;
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
        StateListDrawable sld = new StateListDrawable();
        sld.addState(new int[]{android.R.attr.state_pressed}, new ColorDrawable(Color.parseColor("#55000000")));
        sld.addState(new int[]{}, new ColorDrawable(Color.TRANSPARENT));
        setBackground(sld);
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
        Log.d(this.getClass().getCanonicalName(), event.toString());
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            setTextColor(Color.parseColor("#55FFFFFF"));
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            if (event.getX() > 0 && event.getX() < getWidth()
                    && event.getY() > 0 && event.getY() < getHeight()) {
                setTextColor(Color.parseColor("#55FFFFFF"));
            } else {
                setTextColor(Color.parseColor("#FFFFFF"));
            }
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            setTextColor(Color.parseColor("#FFFFFF"));
            if (event.getX() > 0 && event.getX() < getWidth()
                    && event.getY() > 0 && event.getY() < getHeight()) {
                return callOnClick();
            }
        }
        return super.onTouchEvent(event);
    }
}
