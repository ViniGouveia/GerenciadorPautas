package com.vinigouveia.gerenciadorpautas.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.vinigouveia.gerenciadorpautas.R;
import com.vinigouveia.gerenciadorpautas.Room.DBData.MyDatabase;
import com.vinigouveia.gerenciadorpautas.Room.DBEntities.AgendaEntity;
import com.vinigouveia.gerenciadorpautas.SharedPreferences.SecurityPreferences;
import com.vinigouveia.gerenciadorpautas.Constants.Constants;

public class CreateNewAgendaActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher {

    //Instancia do objeto utilizado para mapear os elementos gráficos da Activity
    private CreateNewAgendaViewHolder mCreateNewAgendaViewHolder = new CreateNewAgendaViewHolder();

    private Intent mIntentBackCreateNewAgenda;

    private MyDatabase db;

    private AgendaEntity newAgenda;

    private String authorEmail;
    private String authorName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_agenda);

        db = MyDatabase.getAppDatabase(getApplicationContext());

        SecurityPreferences mSharedPreferences = new SecurityPreferences(getApplicationContext());
        authorEmail = mSharedPreferences.getStoredString(Constants.USEREMAIL_KEY);
        authorName = mSharedPreferences.getStoredString(Constants.USERNAME_KEY);

        mIntentBackCreateNewAgenda = new Intent(getApplicationContext(), AgendaActivity.class);

        //Mapeamento dos elementos gráficos
        this.mCreateNewAgendaViewHolder.textNewTitle = findViewById(R.id.text_new_title);
        this.mCreateNewAgendaViewHolder.textNewShortDescription = findViewById(R.id.text_new_short_description);
        this.mCreateNewAgendaViewHolder.textNewDescription = findViewById(R.id.text_new_description);
        this.mCreateNewAgendaViewHolder.textNewAuthor = findViewById(R.id.text_new_author);
        this.mCreateNewAgendaViewHolder.buttonBackCreateNewAgenda = findViewById(R.id.button_back_create_new_agenda);
        this.mCreateNewAgendaViewHolder.buttonCreateNewAgenda = findViewById(R.id.button_create_new_agenda);

        //Mapeamento dos eventos de click
        this.mCreateNewAgendaViewHolder.buttonCreateNewAgenda.setOnClickListener(this);
        this.mCreateNewAgendaViewHolder.buttonBackCreateNewAgenda.setOnClickListener(this);

        this.mCreateNewAgendaViewHolder.textNewAuthor.setText(authorName);

        this.mCreateNewAgendaViewHolder.textNewTitle.addTextChangedListener(this);
        this.mCreateNewAgendaViewHolder.textNewShortDescription.addTextChangedListener(this);
        this.mCreateNewAgendaViewHolder.textNewDescription.addTextChangedListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_back_create_new_agenda) {
            startActivity(mIntentBackCreateNewAgenda); // Volta para tela de pautas
        }

        if (v.getId() == R.id.button_create_new_agenda) {
            newAgenda = getNewAgendaData();
            db.agendaDao().insertAgenda(newAgenda);
            startActivity(mIntentBackCreateNewAgenda);
            Toast.makeText(getApplicationContext(), "Pauta adicionada com sucesso!", Toast.LENGTH_LONG).show();
        }
    }

    public AgendaEntity getNewAgendaData() {
        String title, shortDescription, description;
        title = mCreateNewAgendaViewHolder.textNewTitle.getText().toString();
        shortDescription = mCreateNewAgendaViewHolder.textNewShortDescription.getText().toString();
        description = mCreateNewAgendaViewHolder.textNewDescription.getText().toString();
        return new AgendaEntity(db.agendaDao().getAllAgendas(authorEmail).size(), title, shortDescription, description, authorName, authorEmail);
    }

    public Boolean verifyEmptyFields() {
        return this.mCreateNewAgendaViewHolder.textNewTitle.getText().toString().equals("") || this.mCreateNewAgendaViewHolder.textNewShortDescription.getText().toString().equals("") || this.mCreateNewAgendaViewHolder.textNewDescription.getText().toString().equals("");
    }

    public void unlockButton() {
        this.mCreateNewAgendaViewHolder.buttonCreateNewAgenda.setBackground(getResources().getDrawable(R.drawable.button_green_background));
        this.mCreateNewAgendaViewHolder.buttonCreateNewAgenda.setEnabled(true);
    }

    public void lockButton() {
        this.mCreateNewAgendaViewHolder.buttonCreateNewAgenda.setBackground(getResources().getDrawable(R.drawable.button_grey_background));
        this.mCreateNewAgendaViewHolder.buttonCreateNewAgenda.setEnabled(false);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (!verifyEmptyFields()) {
            unlockButton();
        } else {
            lockButton();
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
        if (!verifyEmptyFields()) {
            unlockButton();
        } else {
            lockButton();
        }
    }

    private static class CreateNewAgendaViewHolder {
        EditText textNewTitle;
        EditText textNewShortDescription;
        EditText textNewDescription;
        TextView textNewAuthor;
        Button buttonBackCreateNewAgenda;
        Button buttonCreateNewAgenda;
    }
}
