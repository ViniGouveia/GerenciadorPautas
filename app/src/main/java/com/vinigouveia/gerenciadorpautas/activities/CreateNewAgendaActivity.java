package com.vinigouveia.gerenciadorpautas.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.vinigouveia.gerenciadorpautas.R;

public class CreateNewAgendaActivity extends AppCompatActivity implements View.OnClickListener {

    //Instancia do objeto utilizado para mapear os elementos gráficos da Activity
    private CreateNewAgendaViewHolder mCreateNewAgendaViewHolder = new CreateNewAgendaViewHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_agenda);

        //Mapeamento dos elementos gráficos
        this.mCreateNewAgendaViewHolder.textNewTitle = findViewById(R.id.text_new_title);
        this.mCreateNewAgendaViewHolder.textNewShortDescription = findViewById(R.id.text_new_short_description);
        this.mCreateNewAgendaViewHolder.textNewDescription = findViewById(R.id.text_new_description);
        this.mCreateNewAgendaViewHolder.textNewAutor = findViewById(R.id.text_new_author);
        this.mCreateNewAgendaViewHolder.buttonBackCreateNewAgenda = findViewById(R.id.button_back_create_new_agenda);
        this.mCreateNewAgendaViewHolder.buttonCreateNewAgenda = findViewById(R.id.button_create_new_agenda);

        //Mapeamento dos eventos de click
        this.mCreateNewAgendaViewHolder.buttonCreateNewAgenda.setOnClickListener(this);
        this.mCreateNewAgendaViewHolder.buttonBackCreateNewAgenda.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intentBackCreateNewAgenda = new Intent(getApplicationContext(), AgendaActivity.class);
        if (v.getId() == R.id.button_back_create_new_agenda) {
            startActivity(intentBackCreateNewAgenda); // Volta para tela de pautas
        }

        if (v.getId() == R.id.button_create_new_agenda) {
            //Lógica que cria uma nova pauta, seta o status como aberta, insere na lista de pautas abertas e retorna a tela de pautas
            startActivity(intentBackCreateNewAgenda);
        }
    }

    private static class CreateNewAgendaViewHolder {
        EditText textNewTitle;
        EditText textNewShortDescription;
        EditText textNewDescription;
        TextView textNewAutor;
        Button buttonBackCreateNewAgenda;
        Button buttonCreateNewAgenda;
    }
}
