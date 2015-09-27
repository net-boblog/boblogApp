package net.boblog.boblogapp.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;

import java.util.HashMap;

/**
 * Created by dave on 15-9-27.
 */
public class AssetTypeFaces {
    private static final HashMap<String, Typeface> cache = new HashMap<>();

    public static Typeface getTypeface(Context context, String assetPath) {
        synchronized (cache) {
            if (!cache.containsKey(assetPath)) {
                try {
                    Typeface typeface = Typeface.createFromAsset(context.getAssets(), assetPath);
                    cache.put(assetPath, typeface);
                } catch (Exception ex) {
                    Log.e(AssetTypeFaces.class.getCanonicalName(), "Typeface not load: " + assetPath);
                }
            }
            return cache.get(assetPath);
        }
    }
}
