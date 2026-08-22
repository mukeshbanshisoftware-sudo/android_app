package ui.screen.profile;

import android.app.AlertDialog;
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

import com.example.bossly.data.local.SessionManager;
import com.example.bossly.data.model.request.LogoutRequest;
import com.example.bossly.data.model.response.LogoutResponse;
import com.example.bossly.data.model.response.UserModel;
import com.example.bossly.network.ApiClient;
import com.example.bossly.network.ApiService;
import com.example.food_design.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ui.auth.LoginActivity;

public class ProfileFragment extends Fragment {

    private LinearLayout btnLogout;
    private TextView txtUserName, txtUserEmail;
    private ImageView imgUserProfile;
    private SessionManager sessionManager;

    public ProfileFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        sessionManager = new SessionManager(requireContext());
        
        initViews(view);
        displayUserData();
        setupClickListeners();
    }

    private void initViews(View view) {
        txtUserName = view.findViewById(R.id.txtUserName);
        txtUserEmail = view.findViewById(R.id.txtUserEmail);
        imgUserProfile = view.findViewById(R.id.imgUserProfile);
        btnLogout = view.findViewById(R.id.btnLogout);
        
        View menuEdit = view.findViewById(R.id.menuEditProfile);
        if (menuEdit != null) menuEdit.setOnClickListener(v -> Toast.makeText(getContext(), "Edit Profile", Toast.LENGTH_SHORT).show());
        
        View menuOrders = view.findViewById(R.id.menuMyOrders);
        if (menuOrders != null) menuOrders.setOnClickListener(v -> Toast.makeText(getContext(), "My Orders", Toast.LENGTH_SHORT).show());
        
        View menuPayment = view.findViewById(R.id.menuPayment);
        if (menuPayment != null) menuPayment.setOnClickListener(v -> Toast.makeText(getContext(), "Payment Method", Toast.LENGTH_SHORT).show());
        
        View menuSettings = view.findViewById(R.id.menuSettings);
        if (menuSettings != null) menuSettings.setOnClickListener(v -> Toast.makeText(getContext(), "Settings", Toast.LENGTH_SHORT).show());
    }

    private void displayUserData() {
        UserModel user = sessionManager.getUser();
        if (user != null) {
            String firstName = user.getFirstName() != null ? user.getFirstName() : "";
            String lastName = user.getLastName() != null ? user.getLastName() : "";
            String fullName = (firstName + " " + lastName).trim();
            txtUserName.setText(fullName.isEmpty() ? user.getUserName() : fullName);
            txtUserEmail.setText(user.getEmail());
        } else {
            txtUserName.setText("Guest User");
            txtUserEmail.setText("");
        }
    }

    private void setupClickListeners() {
        btnLogout.setOnClickListener(v -> showLogoutConfirmationDialog());
    }

    private void showLogoutConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Logout");
        builder.setMessage("Are you sure you want to logout?");
        builder.setPositiveButton("Yes", (dialog, which) -> performLogout());
        builder.setNegativeButton("No", (dialog, which) -> dialog.dismiss());
        builder.show();
    }

    private void performLogout() {
        btnLogout.setEnabled(false);
        
        String refreshToken = sessionManager.getRefreshToken();
        
        // Even if the API call fails or takes time, we should ensure the user is logged out locally
        // to prevent them from staying "logged in" on next restart.
        
        if (refreshToken != null) {
            ApiService apiService = ApiClient.getApiService(requireContext());
            apiService.logout(new LogoutRequest(refreshToken)).enqueue(new Callback<LogoutResponse>() {
                @Override
                public void onResponse(Call<LogoutResponse> call, Response<LogoutResponse> response) {
                    // API call finished (Success or Error), clear local session
                    handleLocalLogout();
                }

                @Override
                public void onFailure(Call<LogoutResponse> call, Throwable t) {
                    // Network error, still logout locally
                    handleLocalLogout();
                }
            });
        } else {
            handleLocalLogout();
        }
    }

    private void handleLocalLogout() {
        // Clear all stored data (Tokens, User Info) from SharedPreferences
        sessionManager.clearSession();
        
        // Navigate to Login screen and clear the task stack
        completeLogout();
    }

    private void completeLogout() {
        if (!isAdded()) return;
        
        Toast.makeText(getContext(), "Logged out successfully", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getActivity(), LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        if (getActivity() != null) {
            getActivity().finish();
        }
    }
}
