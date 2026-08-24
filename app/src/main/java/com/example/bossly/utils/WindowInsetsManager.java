package com.example.bossly.utils;

import android.view.View;
import android.view.ViewGroup;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class WindowInsetsManager {

    public static void applyTopInset(View view) {
        if (view == null) return;
        final int initialPaddingTop = view.getPaddingTop();
        ViewCompat.setOnApplyWindowInsetsListener(view, (v, windowInsets) -> {
            Insets insets = windowInsets.getInsets(WindowInsetsCompat.Type.statusBars() | WindowInsetsCompat.Type.displayCutout());
            v.setPadding(
                v.getPaddingLeft(),
                initialPaddingTop + insets.top,
                v.getPaddingRight(),
                v.getPaddingBottom()
            );
            return windowInsets;
        });
    }

    public static void applyBottomInset(View view) {
        if (view == null) return;
        final int initialPaddingBottom = view.getPaddingBottom();
        ViewCompat.setOnApplyWindowInsetsListener(view, (v, windowInsets) -> {
            Insets insets = windowInsets.getInsets(WindowInsetsCompat.Type.navigationBars());
            v.setPadding(
                v.getPaddingLeft(),
                v.getPaddingTop(),
                v.getPaddingRight(),
                initialPaddingBottom + insets.bottom
            );
            return windowInsets;
        });
    }

    public static void applyTopAndBottomInsets(View view) {
        if (view == null) return;
        final int initialPaddingTop = view.getPaddingTop();
        final int initialPaddingBottom = view.getPaddingBottom();
        ViewCompat.setOnApplyWindowInsetsListener(view, (v, windowInsets) -> {
            Insets topInsets = windowInsets.getInsets(WindowInsetsCompat.Type.statusBars() | WindowInsetsCompat.Type.displayCutout());
            Insets bottomInsets = windowInsets.getInsets(WindowInsetsCompat.Type.navigationBars());
            v.setPadding(
                v.getPaddingLeft(),
                initialPaddingTop + topInsets.top,
                v.getPaddingRight(),
                initialPaddingBottom + bottomInsets.bottom
            );
            return windowInsets;
        });
    }
    
    public static void applyTopMargin(View view) {
        if (view == null) return;
        ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        final int initialMarginTop = lp.topMargin;
        ViewCompat.setOnApplyWindowInsetsListener(view, (v, windowInsets) -> {
            Insets insets = windowInsets.getInsets(WindowInsetsCompat.Type.statusBars() | WindowInsetsCompat.Type.displayCutout());
            lp.topMargin = initialMarginTop + insets.top;
            v.setLayoutParams(lp);
            return windowInsets;
        });
    }

    public static void applyBottomMargin(View view) {
        if (view == null) return;
        ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        final int initialMarginBottom = lp.bottomMargin;
        ViewCompat.setOnApplyWindowInsetsListener(view, (v, windowInsets) -> {
            Insets insets = windowInsets.getInsets(WindowInsetsCompat.Type.navigationBars());
            lp.bottomMargin = initialMarginBottom + insets.bottom;
            v.setLayoutParams(lp);
            return windowInsets;
        });
    }
}
