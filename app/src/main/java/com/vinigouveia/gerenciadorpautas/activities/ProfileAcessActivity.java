package com.vinigouveia.gerenciadorpautas.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.vinigouveia.gerenciadorpautas.R;


public class ProfileAcessActivity extends AppCompatActivity implements View.OnClickListener {

    //Instancia do objeto utilizado para mapear os elementos gráficos da Activity
    private ProfileAcessViewHolder mProfileAcessViewHolder = new ProfileAcessViewHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_acess);

        //Mapeamento dos elementos gráficos
        this.mProfileAcessViewHolder.textUserName = findViewById(R.id.text_user_name);
        this.mProfileAcessViewHolder.textUserEmail = findViewById(R.id.text_user_email);
        this.mProfileAcessViewHolder.buttonBackProfileAcess = findViewById(R.id.button_back_profile_acess);
        this.mProfileAcessViewHolder.buttonLogout = findViewById(R.id.button_logout);

        //Mapeamento dos eventos de click
        this.mProfileAcessViewHolder.buttonLogout.setOnClickListener(this);
        this.mProfileAcessViewHolder.buttonBackProfileAcess.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_back_profile_acess) {
            Intent intentBackProfileAcess = new Intent(getApplicationContext(), AgendaActivity.class);
            startActivity(intentBackProfileAcess); // Volta à pagina de pautas
        }

        if (v.getId() == R.id.button_logout) {
            Intent intentBackToLogin = new Intent(getApplicationContext(), LoginActivity.class);
            //Lógica para logout
            startActivity(intentBackToLogin);
        }
    }

    private static class ProfileAcessViewHolder {
        TextView textUserName;
        TextView textUserEmail;
        Button buttonBackProfileAcess;
        Button buttonLogout;
    }
}
