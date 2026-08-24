package com.example.bossly.base;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * Base class for all fragments to support global safe-area / edge-to-edge system.
 */
public abstract class BaseFragment extends Fragment {

    public BaseFragment() {
        super();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Base implementation for onViewCreated
    }
}
