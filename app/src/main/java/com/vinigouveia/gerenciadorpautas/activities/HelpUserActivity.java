package com.vinigouveia.gerenciadorpautas.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.vinigouveia.gerenciadorpautas.R;


public class HelpUserActivity extends AppCompatActivity implements View.OnClickListener {

    //Instancia do objeto utilizado para mapear os elementos gráficos da Activity
    private HelpViewHolder mHelpUserViewHolder = new HelpViewHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_user);

        //Mapeamento dos elementos gráficos
        this.mHelpUserViewHolder.textHelpUser = findViewById(R.id.text_help_user);
        this.mHelpUserViewHolder.buttonBackHelpUser = findViewById(R.id.button_back_help_user);

        //Mapeamento dos eventos de click
        this.mHelpUserViewHolder.buttonBackHelpUser.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_back_help_user) {
            Intent intentBackUserHelp = new Intent(getApplicationContext(), AgendaActivity.class);
            startActivity(intentBackUserHelp);
        }
    }

    private static class HelpViewHolder {
        TextView textHelpUser;
        Button buttonBackHelpUser;
    }
}
