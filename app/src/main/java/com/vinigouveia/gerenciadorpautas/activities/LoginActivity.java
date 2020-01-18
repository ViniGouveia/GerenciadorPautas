package com.vinigouveia.gerenciadorpautas.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.vinigouveia.gerenciadorpautas.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    //Instancia do objeto utilizado para mapear os elementos gráficos da Activity
    private LoginViewHolder mLoginViewHolder = new LoginViewHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Mapeamento dos elementos gráficos
        this.mLoginViewHolder.textEmail = findViewById(R.id.text_email);
        this.mLoginViewHolder.textPassword = findViewById(R.id.text_password);
        this.mLoginViewHolder.buttonLogin = findViewById(R.id.button_login);
        this.mLoginViewHolder.buttonCreateAccount = findViewById(R.id.button_create_account);
        this.mLoginViewHolder.buttonRecoverPassword = findViewById(R.id.button_recover_password);

        //Mapeamento dos eventos de click dos botões da Activity
        this.mLoginViewHolder.buttonLogin.setOnClickListener(this);
        this.mLoginViewHolder.buttonCreateAccount.setOnClickListener(this);
        this.mLoginViewHolder.buttonRecoverPassword.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intentToRecoverPassword = new Intent(getApplicationContext(), RecoverPasswordActivity.class);
        Intent intentToCreateAccount = new Intent(getApplicationContext(), CreateAccountActivity.class);
        Intent intentToAgenda = new Intent(getApplicationContext(), AgendaActivity.class);
        if (v.getId() == R.id.button_recover_password) {
            startActivity(intentToRecoverPassword);
        }
        if (v.getId() == R.id.button_create_account) {
            startActivity(intentToCreateAccount);
        }
        if (v.getId() == R.id.button_login) {
            //Falta fazer a validação dos dados no banco de dados
            startActivity(intentToAgenda);
        }
    }


    //Classe para mapeamento de elementos gráficos da Activity
    private static class LoginViewHolder {
        EditText textEmail;
        EditText textPassword;
        Button buttonLogin;
        Button buttonRecoverPassword;
        Button buttonCreateAccount;
    }
}