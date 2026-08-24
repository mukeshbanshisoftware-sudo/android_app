package utils;



import android.view.View;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class WindowInsetsManager {

    private WindowInsetsManager() {
        // Utility class
    }

    /**
     * Apply status bar + display cutout inset to the top of a view.
     */
    public static void applyTopInset(View view) {

        if (view == null) {
            return;
        }

        final int initialLeft = view.getPaddingLeft();
        final int initialTop = view.getPaddingTop();
        final int initialRight = view.getPaddingRight();
        final int initialBottom = view.getPaddingBottom();

        ViewCompat.setOnApplyWindowInsetsListener(view, (v, windowInsets) -> {

            Insets insets = windowInsets.getInsets(
                    WindowInsetsCompat.Type.statusBars()
                            | WindowInsetsCompat.Type.displayCutout()
            );

            v.setPadding(
                    initialLeft,
                    initialTop + insets.top,
                    initialRight,
                    initialBottom
            );

            return windowInsets;
        });

        ViewCompat.requestApplyInsets(view);
    }

    /**
     * Apply navigation bar inset to the bottom of a view.
     */
    public static void applyBottomInset(View view) {

        if (view == null) {
            return;
        }

        final int initialLeft = view.getPaddingLeft();
        final int initialTop = view.getPaddingTop();
        final int initialRight = view.getPaddingRight();
        final int initialBottom = view.getPaddingBottom();

        ViewCompat.setOnApplyWindowInsetsListener(view, (v, windowInsets) -> {

            Insets insets = windowInsets.getInsets(
                    WindowInsetsCompat.Type.navigationBars()
            );

            v.setPadding(
                    initialLeft,
                    initialTop,
                    initialRight,
                    initialBottom + insets.bottom
            );

            return windowInsets;
        });

        ViewCompat.requestApplyInsets(view);
    }

    /**
     * Apply top + bottom system insets.
     */
    public static void applyTopAndBottomInsets(View view) {

        if (view == null) {
            return;
        }

        final int initialLeft = view.getPaddingLeft();
        final int initialTop = view.getPaddingTop();
        final int initialRight = view.getPaddingRight();
        final int initialBottom = view.getPaddingBottom();

        ViewCompat.setOnApplyWindowInsetsListener(view, (v, windowInsets) -> {

            Insets topInsets = windowInsets.getInsets(
                    WindowInsetsCompat.Type.statusBars()
                            | WindowInsetsCompat.Type.displayCutout()
            );

            Insets bottomInsets = windowInsets.getInsets(
                    WindowInsetsCompat.Type.navigationBars()
            );

            v.setPadding(
                    initialLeft,
                    initialTop + topInsets.top,
                    initialRight,
                    initialBottom + bottomInsets.bottom
            );

            return windowInsets;
        });

        ViewCompat.requestApplyInsets(view);
    }
}
