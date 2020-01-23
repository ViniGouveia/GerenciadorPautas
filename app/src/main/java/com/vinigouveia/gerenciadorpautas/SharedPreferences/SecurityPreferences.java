package com.vinigouveia.gerenciadorpautas.SharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;

//Shared preferences utilizado para salvar os dados do usuário ao fazer login
public class SecurityPreferences {

    private SharedPreferences mSharedPreferences;

    public SecurityPreferences(Context context) {
        this.mSharedPreferences = context.getSharedPreferences("user_logged_data", Context.MODE_PRIVATE);
    }

    public void storeString(String key, String value) {
        this.mSharedPreferences.edit().putString(key, value).apply();
    }

    public String getStoredString(String key) {
        return this.mSharedPreferences.getString(key, "");
    }

    public void clearSharedPreferences() {
        this.mSharedPreferences.edit().clear().apply();
    }
}
