package net.boblog.boblogapp.component;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;

/**
 * Created by dave on 15-10-15.
 */
public class LinedEditText extends EditText {
    private Paint mPaint;
    private Path mPath;

    public LinedEditText(Context context) {
        super(context);
        init();
    }

    public LinedEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LinedEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPath = new Path();
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(1);
        mPaint.setPathEffect(new DashPathEffect(new float[]{20, 10}, 0));
        mPaint.setColor(0xFFAAAAAA);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int lineCount = getLineCount();
        Path path = mPath;
        Paint paint = mPaint;

        int left = getPaddingLeft();
        int right = getWidth() - getPaddingRight();
        Log.d("right: ", String.valueOf(right));
        int lineHeight = getLineHeight();
        int showLine = Math.max(getHeight()/ getLineHeight(), lineCount);

        for (int i = 1; i <= showLine; i++) {
            path.reset();
            path.moveTo(left, lineHeight * i - 10);
            path.lineTo(right, lineHeight * i - 10);
            canvas.drawPath(path, paint);
        }

        super.onDraw(canvas);

    }
}
