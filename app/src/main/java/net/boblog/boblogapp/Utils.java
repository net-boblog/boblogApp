package net.boblog.boblogapp;

import android.content.Context;

/**
 * Created by dave on 15-9-26.
 */
public class Utils {
    /**
     * 将指定颜色灰度化
     * @param color
     * @return
     */
    public static int grayOutColor(int color) {
        int alpha = 0xFF << 24;
        int red = (color & 0x00FF0000) >> 16;
        int green = (color & 0x0000FF00) >> 8;
        int blue = color & 0x000000FF;
        color = (red * 19595 + green * 38469 + blue * 7472) >> 16;
        return (0x00FFFFFF & color) | alpha;
    }

    /**
     * 将颜色的R、G、B各分量乘以rate值，进行暗化或量化
     * @param color
     * @param rate
     * @return
     */
    public static int brightenColorBy(int color, float rate) {
        int alpha = 0xFF << 24;
        int red = (color & 0x00FF0000) >> 16;
        int green = (color & 0x0000FF00) >> 8;
        int blue = color & 0x000000FF;
        color = Math.min((int)(red * rate), 255) +  Math.min((int)(green * rate), 255) +  Math.min((int)(blue * rate), 255);
        return (0x00FFFFFF & color) | alpha;
    }

    public static int px2dp(Context context, int px) {
        return (int) (px / context.getResources().getDisplayMetrics().density + 0.5f);
    }

    public static int dp2px(Context context, int dp) {
        return (int) (dp * context.getResources().getDisplayMetrics().density + 0.5f);
    }

    public static int sp2px(Context context, int sp) {
        return (int) (sp * context.getResources().getDisplayMetrics().scaledDensity + 0.5f);
    }

    public static int px2sp(Context context, int px) {
        return (int) (px / context.getResources().getDisplayMetrics().scaledDensity + 0.5f);
    }
}
