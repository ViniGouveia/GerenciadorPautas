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

public class CreateAccountActivity extends AppCompatActivity implements View.OnClickListener {

    //Instancia do objeto utilizado para mapear os elementos gráficos da Activity
    private CreateAccountViewHolder mCreateAccountViewHolder = new CreateAccountViewHolder();

    private MyDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        db = MyDatabase.getAppDatabase(this);

        //Mapeamento dos elementos gráficos
        this.mCreateAccountViewHolder.textNewName = findViewById(R.id.text_new_user_name);
        this.mCreateAccountViewHolder.textNewEmail = findViewById(R.id.text_new_user_email);
        this.mCreateAccountViewHolder.textNewPassword = findViewById(R.id.text_new_user_password);
        this.mCreateAccountViewHolder.buttonBackCreateAccount = findViewById(R.id.button_back_create_account);
        this.mCreateAccountViewHolder.buttonCreateNewUser = findViewById(R.id.button_create_new_account);

        //Mapeamento dos eventos de click
        this.mCreateAccountViewHolder.buttonBackCreateAccount.setOnClickListener(this);
        this.mCreateAccountViewHolder.buttonCreateNewUser.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        final Intent intentBackCreateAccount = new Intent(getApplicationContext(), LoginActivity.class);
        if (v.getId() == R.id.button_back_create_account) {
            startActivity(intentBackCreateAccount); // Volta à tela de login
        }
        if (v.getId() == R.id.button_create_new_account) {

            String newEmail = mCreateAccountViewHolder.textNewEmail.getText().toString();
            String newName = mCreateAccountViewHolder.textNewName.getText().toString();
            String newPassword = mCreateAccountViewHolder.textNewPassword.getText().toString();
            if (newEmail.equals("") || newName.equals("") || newPassword.equals("")) {
                Toast.makeText(getApplicationContext(), "Preencha todos os campos", Toast.LENGTH_LONG).show();
            } else {
                if (db.userDao().getUser(newEmail) == null) {
                    UserEntity user = new UserEntity(newEmail, newName, newPassword);
                    db.userDao().insertUser(user);
                    startActivity(intentBackCreateAccount);
                    Toast.makeText(getApplicationContext(), "Conta criada com sucesso!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Email existente, tente novamente.", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    private static class CreateAccountViewHolder {
        EditText textNewName;
        EditText textNewEmail;
        EditText textNewPassword;
        Button buttonBackCreateAccount;
        Button buttonCreateNewUser;
    }
}
