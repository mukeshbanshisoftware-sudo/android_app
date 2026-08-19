package ui.screen.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.food_design.R;

import ui.auth.LoginActivity;

public class ProfileFragment extends Fragment {

    private LinearLayout btnLogout;
    private TextView txtUserName, txtUserEmail;
    private ImageView imgUserProfile;

    public ProfileFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        initViews(view);
        displayUserData();
        setupClickListeners();
    }

    private void initViews(View view) {
        txtUserName = view.findViewById(R.id.txtUserName);
        txtUserEmail = view.findViewById(R.id.txtUserEmail);
        imgUserProfile = view.findViewById(R.id.imgUserProfile);
        btnLogout = view.findViewById(R.id.btnLogout);
        
        // Other menu items if needed for feedback
        view.findViewById(R.id.menuEditProfile).setOnClickListener(v -> Toast.makeText(getContext(), "Edit Profile", Toast.LENGTH_SHORT).show());
        view.findViewById(R.id.menuMyOrders).setOnClickListener(v -> Toast.makeText(getContext(), "My Orders", Toast.LENGTH_SHORT).show());
        view.findViewById(R.id.menuPayment).setOnClickListener(v -> Toast.makeText(getContext(), "Payment Method", Toast.LENGTH_SHORT).show());
        view.findViewById(R.id.menuSettings).setOnClickListener(v -> Toast.makeText(getContext(), "Settings", Toast.LENGTH_SHORT).show());
    }

    private void displayUserData() {
        // Mocking user data since we removed models and database
        txtUserName.setText("Test User");
        txtUserEmail.setText("test@gmail.com");
    }

    private void setupClickListeners() {
        btnLogout.setOnClickListener(v -> performLogout());
    }

    private void performLogout() {
        btnLogout.setEnabled(false);
        // Mock logout
        completeLogout();
    }

    private void completeLogout() {
        Toast.makeText(getContext(), "Logged out successfully", Toast.LENGTH_SHORT).show();
        
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        if (getActivity() != null) {
            getActivity().finish();
        }
    }
}
