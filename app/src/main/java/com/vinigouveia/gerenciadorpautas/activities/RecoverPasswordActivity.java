package com.vinigouveia.gerenciadorpautas.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vinigouveia.gerenciadorpautas.Constants.Constants;
import com.vinigouveia.gerenciadorpautas.Mail.Mail;
import com.vinigouveia.gerenciadorpautas.R;
import com.vinigouveia.gerenciadorpautas.Room.DBData.MyDatabase;
import com.vinigouveia.gerenciadorpautas.Room.DBEntities.UserEntity;

public class RecoverPasswordActivity extends AppCompatActivity implements View.OnClickListener {

    //Instancia do objeto utilizado para mapear os elementos gráficos da Activity
    private RecoverPasswordViewHolder mRecoverPasswordViewHolder = new RecoverPasswordViewHolder();

    private MyDatabase db;

    private Intent intentBackRecoverPassword;

    private String email;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recover_password);

        db = MyDatabase.getAppDatabase(getApplicationContext());
        intentBackRecoverPassword = new Intent(getApplicationContext(), LoginActivity.class);
        intentBackRecoverPassword.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        email = getIntent().getStringExtra(Constants.USEREMAIL_KEY);

        //Mapeamento dos elementos gráficos
        this.mRecoverPasswordViewHolder.textRecoverPasswordEmail = findViewById(R.id.text_recover_password_email);
        this.mRecoverPasswordViewHolder.buttonBackRecoverPassword = findViewById(R.id.button_back_recover_password);
        this.mRecoverPasswordViewHolder.buttonSendPassword = findViewById(R.id.button_send_password);

        this.mRecoverPasswordViewHolder.textRecoverPasswordEmail.setText(email);

        //Mapeamento dos eventos de click
        this.mRecoverPasswordViewHolder.buttonSendPassword.setOnClickListener(this);
        this.mRecoverPasswordViewHolder.buttonBackRecoverPassword.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_back_recover_password) {
            startActivity(intentBackRecoverPassword); // Volta à pagina de login
        }
        if (v.getId() == R.id.button_send_password) {
            if (recoverPassword()) {
                sendEmail();
                startActivity(intentBackRecoverPassword);
                Toast.makeText(getApplicationContext(), "Email enviado!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void sendEmail() {
        if (!isOnline()) {
            Toast.makeText(getApplicationContext(), "Verifique sua conexão com a internet!", Toast.LENGTH_SHORT).show();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                Mail m = new Mail();

                String[] toArr = {email};
                m.setTo(toArr);

                m.setFrom("mPautas@recoverpassword.com.br");
                m.setSubject(email);
                m.setBody("Sua senha é: " + db.userDao().getUserPassword(email));

                try {
                    m.sendE();
                } catch (Exception e) {
                    e.printStackTrace();
                    System.exit(0);
                }
            }
        }).start();
    }

    private Boolean recoverPassword() {
        UserEntity user;
        if (email.equals("")) {
            email = mRecoverPasswordViewHolder.textRecoverPasswordEmail.getText().toString();
            if (email.equals("")) {
                Toast.makeText(getApplicationContext(), "Digite um email válido.", Toast.LENGTH_SHORT).show();
                return false;
            } else {
                user = db.userDao().getUser(email);
                if (user == null) {
                    Toast.makeText(getApplicationContext(), "Usuário não encontrado no sistema", Toast.LENGTH_SHORT).show();
                    return false;
                } else {
                    password = user.getUserPassword();
                    email = user.getUserEmail();
                    return true;
                }
            }
        } else {
            user = db.userDao().getUser(email);
            if (user == null) {
                Toast.makeText(getApplicationContext(), "Usuário não encontrado no sistema", Toast.LENGTH_SHORT).show();
                return false;
            } else {
                password = user.getUserPassword();
                email = user.getUserEmail();
                return true;
            }
        }
    }

    public boolean isOnline() {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
            return netInfo != null && netInfo.isConnectedOrConnecting();
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), "Erro ao verificar se estava online! (" + ex.getMessage() + ")", Toast.LENGTH_SHORT).show();
            return false;
        }
    }


    private static class RecoverPasswordViewHolder {
        EditText textRecoverPasswordEmail;
        Button buttonBackRecoverPassword;
        Button buttonSendPassword;
    }
}
