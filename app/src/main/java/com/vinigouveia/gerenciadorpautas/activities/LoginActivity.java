package com.vinigouveia.gerenciadorpautas.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vinigouveia.gerenciadorpautas.R;
import com.vinigouveia.gerenciadorpautas.Room.DBData.MyDatabase;
import com.vinigouveia.gerenciadorpautas.Room.DBEntities.UserEntity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    //Instancia do objeto utilizado para mapear os elementos gráficos da Activity
    private LoginViewHolder mLoginViewHolder = new LoginViewHolder();
    private Intent intentToRecoverPassword;
    private Intent intentToCreateAccount;
    private Intent intentToAgenda;
    private MyDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = MyDatabase.getAppDatabase(getApplicationContext());
        intentToRecoverPassword = new Intent(getApplicationContext(), RecoverPasswordActivity.class);
        intentToCreateAccount = new Intent(getApplicationContext(), CreateAccountActivity.class);
        intentToAgenda = new Intent(getApplicationContext(), AgendaActivity.class);

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
        if (v.getId() == R.id.button_recover_password) {
            startActivity(intentToRecoverPassword);
        }
        if (v.getId() == R.id.button_create_account) {
            startActivity(intentToCreateAccount);
        }
        if (v.getId() == R.id.button_login) {
            String email = mLoginViewHolder.textEmail.getText().toString();
            String password = mLoginViewHolder.textPassword.getText().toString();
            if (email.equals("") || password.equals("")) {
                Toast.makeText(getApplicationContext(), "Preencha o email e a senha", Toast.LENGTH_LONG).show();
            } else {
                UserEntity user = db.userDao().getUser(email);
                verifyUser(user, password);
            }
        }
    }

    public void verifyUser(UserEntity user, String password) {
        if (user == null) {
            Toast.makeText(getApplicationContext(), "Usuário não encontrado", Toast.LENGTH_LONG).show();
        } else if (user.getUserPassword().equals(password)) {
            startActivity(intentToAgenda);
        } else {
            Toast.makeText(getApplicationContext(), "Senha incorreta", Toast.LENGTH_LONG).show();
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
