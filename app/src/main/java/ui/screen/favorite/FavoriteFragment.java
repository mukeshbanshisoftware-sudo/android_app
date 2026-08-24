package ui.screen.favorite;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.bossly.base.BaseFragment;
import com.example.bossly.utils.WindowInsetsManager;
import com.example.food_design.R;

public class FavoriteFragment extends BaseFragment {

    public FavoriteFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        // Apply Safe Area Insets
        WindowInsetsManager.applyTopInset(view.findViewById(R.id.txtFavoriteHeader));
    }
}
