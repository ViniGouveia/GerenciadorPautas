package com.vinigouveia.gerenciadorpautas.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.vinigouveia.gerenciadorpautas.R;
import com.vinigouveia.gerenciadorpautas.SharedPreferences.SecurityPreferences;
import com.vinigouveia.gerenciadorpautas.Constants.Constants;


public class ProfileAcessActivity extends AppCompatActivity implements View.OnClickListener {

    //Instancia do objeto utilizado para mapear os elementos gráficos da Activity
    private ProfileAcessViewHolder mProfileAcessViewHolder = new ProfileAcessViewHolder();
    private Intent intentBackProfileAcess;
    private Intent intentBackToLogin;

    private String userEmail;
    private String userName;

    private SecurityPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_acess);

        mSharedPreferences = new SecurityPreferences(getApplicationContext());

        intentBackProfileAcess = new Intent(getApplicationContext(), AgendaActivity.class);
        intentBackToLogin = new Intent(getApplicationContext(), LoginActivity.class);
        intentBackToLogin.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        loadDataFromLoggedUser();

        //Mapeamento dos elementos gráficos
        this.mProfileAcessViewHolder.textUserName = findViewById(R.id.text_user_name);
        this.mProfileAcessViewHolder.textUserEmail = findViewById(R.id.text_user_email);
        this.mProfileAcessViewHolder.buttonBackProfileAcess = findViewById(R.id.button_back_profile_acess);
        this.mProfileAcessViewHolder.buttonLogout = findViewById(R.id.button_logout);

        this.mProfileAcessViewHolder.textUserEmail.setText(userEmail);
        this.mProfileAcessViewHolder.textUserName.setText(userName);

        //Mapeamento dos eventos de click
        this.mProfileAcessViewHolder.buttonLogout.setOnClickListener(this);
        this.mProfileAcessViewHolder.buttonBackProfileAcess.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_back_profile_acess) {
            startActivity(intentBackProfileAcess); // Volta à pagina de pautas
        }

        if (v.getId() == R.id.button_logout) {
            finish();
            mSharedPreferences.clearSharedPreferences(); //Limpa as credenciais salvas para um novo login ser efetuado
            startActivity(intentBackToLogin);
        }
    }

    //Procedimento que recupera os dados do usuário logado para ser exibido no perfil
    private void loadDataFromLoggedUser() {
        userEmail = mSharedPreferences.getStoredString(Constants.USEREMAIL_KEY);
        userName = mSharedPreferences.getStoredString(Constants.USERNAME_KEY);
    }

    private static class ProfileAcessViewHolder {
        TextView textUserName;
        TextView textUserEmail;
        Button buttonBackProfileAcess;
        Button buttonLogout;
    }
}
