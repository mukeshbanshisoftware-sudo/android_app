package ui.screen.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.bossly.base.BaseFragment;
import com.example.bossly.data.local.SessionManager;
import com.example.bossly.data.model.response.UserModel;
import com.example.bossly.utils.WindowInsetsManager;
import com.example.food_design.R;

public class HomeFragment extends BaseFragment {

    private EditText etSearch;
    private TextView userName;
    private SessionManager sessionManager;

    public HomeFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_screen_activity, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        displayUserData();
        
        // Apply Safe Area Insets
        // Apply top inset to the header section
        WindowInsetsManager.applyTopInset(view.findViewById(R.id.etTopHading));
    }

    private void displayUserData() {
        UserModel user = sessionManager.getUser();
        if (user != null) {
            String firstName = user.getFirstName() != null ? user.getFirstName() : "";
            String lastName = user.getLastName() != null ? user.getLastName() : "";
            String fullName = (firstName + " " + lastName).trim();
            userName.setText(fullName.isEmpty() ? user.getUserName() : fullName);
        } else {
            userName.setText("Guest User");
        }
    }

    private void initViews(View view) {
        userName = view.findViewById(R.id.txtUserName);
        etSearch = view.findViewById(R.id.etSearchNs);
        sessionManager = new SessionManager(requireContext());
    }
}
