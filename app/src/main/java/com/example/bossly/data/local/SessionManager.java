package com.example.bossly.data.local;

import android.content.Context;
import android.content.SharedPreferences;
import com.example.bossly.data.model.response.UserModel;
import com.google.gson.Gson;

public class SessionManager {
    private static final String PREF_NAME = "BosslySession";
    private static final String KEY_ACCESS_TOKEN = "accessToken";
    private static final String KEY_REFRESH_TOKEN = "refreshToken";
    private static final String KEY_USER = "user";
    private static final String KEY_USER_ID = "userId";
    private static final String KEY_TENANT_ID = "tenantId";
    private static final String KEY_TENANT_SLUG = "tenantSlug";

    private final SharedPreferences sharedPreferences;
    private final SharedPreferences.Editor editor;
    private final Gson gson;

    public SessionManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        gson = new Gson();
    }

    public void saveAuthSession(String accessToken, String refreshToken, UserModel user) {
        editor.putString(KEY_ACCESS_TOKEN, accessToken);
        editor.putString(KEY_REFRESH_TOKEN, refreshToken);
        editor.putString(KEY_USER, gson.toJson(user));
        editor.putString(KEY_USER_ID, user.getUserId());
        editor.putString(KEY_TENANT_ID, user.getTenantId());
        editor.putString(KEY_TENANT_SLUG, user.getTenantSlug());
        editor.apply();
    }

    public void saveRegistrationData(String userId, String tenantSlug) {
        editor.putString(KEY_USER_ID, userId);
        editor.putString(KEY_TENANT_SLUG, tenantSlug);
        editor.apply();
    }

    public String getAccessToken() {
        return sharedPreferences.getString(KEY_ACCESS_TOKEN, null);
    }

    public String getRefreshToken() {
        return sharedPreferences.getString(KEY_REFRESH_TOKEN, null);
    }

    public String getUserId() {
        return sharedPreferences.getString(KEY_USER_ID, null);
    }

    public String getTenantId() {
        return sharedPreferences.getString(KEY_TENANT_ID, null);
    }

    public String getTenantSlug() {
        return sharedPreferences.getString(KEY_TENANT_SLUG, null);
    }

    public UserModel getUser() {
        String userJson = sharedPreferences.getString(KEY_USER, null);
        if (userJson == null) return null;
        return gson.fromJson(userJson, UserModel.class);
    }

    public boolean isLoggedIn() {
        return getAccessToken() != null;
    }

    public void clearSession() {
        editor.clear();
        editor.apply();
    }
}
