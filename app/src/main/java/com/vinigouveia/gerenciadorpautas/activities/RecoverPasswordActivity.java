package com.vinigouveia.gerenciadorpautas.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.vinigouveia.gerenciadorpautas.R;

public class RecoverPasswordActivity extends AppCompatActivity implements View.OnClickListener {

    //Instancia do objeto utilizado para mapear os elementos gráficos da Activity
    private RecoverPasswordViewHolder mRecoverPasswordViewHolder = new RecoverPasswordViewHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recover_password);

        //Mapeamento dos elementos gráficos
        this.mRecoverPasswordViewHolder.textRecoverPasswordEmail = findViewById(R.id.text_recover_password_email);
        this.mRecoverPasswordViewHolder.buttonBackRecoverPassword = findViewById(R.id.button_back_recover_password);
        this.mRecoverPasswordViewHolder.buttonSendPassword = findViewById(R.id.button_send_password);

        //Mapeamento dos eventos de click
        this.mRecoverPasswordViewHolder.buttonSendPassword.setOnClickListener(this);
        this.mRecoverPasswordViewHolder.buttonBackRecoverPassword.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intentBackRecoverPassword = new Intent(getApplicationContext(), LoginActivity.class);
        if (v.getId() == R.id.button_back_recover_password) {
            startActivity(intentBackRecoverPassword); // Volta à pagina de login
        }
        if (v.getId() == R.id.button_send_password) {
            //Lógica para enviar email contendo a senha do email cadastrado informado e volta à pagina de login
            startActivity(intentBackRecoverPassword);
        }
    }

    private static class RecoverPasswordViewHolder {
        EditText textRecoverPasswordEmail;
        Button buttonBackRecoverPassword;
        Button buttonSendPassword;
    }
}
