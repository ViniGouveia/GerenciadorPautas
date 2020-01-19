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
import com.vinigouveia.gerenciadorpautas.SharedPreferences.SecurityPreferences;
import com.vinigouveia.gerenciadorpautas.Constants.Constants;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    //Instancia do objeto utilizado para mapear os elementos gráficos da Activity
    private LoginViewHolder mLoginViewHolder = new LoginViewHolder();

    private Intent intentToRecoverPassword;
    private Intent intentToCreateAccount;
    private Intent intentToAgenda;

    private MyDatabase db;

    private SecurityPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = MyDatabase.getAppDatabase(getApplicationContext());

        mSharedPreferences = new SecurityPreferences(getApplicationContext());

        String savedEmail = mSharedPreferences.getStoredString(Constants.USEREMAIL_KEY);
        String savedPassword = mSharedPreferences.getStoredString(Constants.USERPASSWORD_KEY);

        intentToRecoverPassword = new Intent(getApplicationContext(), RecoverPasswordActivity.class);
        intentToCreateAccount = new Intent(getApplicationContext(), CreateAccountActivity.class);
        intentToAgenda = new Intent(getApplicationContext(), OpenedAgendasActivity.class);

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

        if (!savedEmail.isEmpty() && !savedPassword.isEmpty()) {
            startActivity(intentToAgenda);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_recover_password) {
            intentToRecoverPassword.putExtra(Constants.USEREMAIL_KEY, mLoginViewHolder.textEmail.getText().toString());
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
            mSharedPreferences.storeString(Constants.USEREMAIL_KEY, user.getUserEmail());
            mSharedPreferences.storeString(Constants.USERNAME_KEY, user.getUserName());
            mSharedPreferences.storeString(Constants.USERPASSWORD_KEY, user.getUserPassword());
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
