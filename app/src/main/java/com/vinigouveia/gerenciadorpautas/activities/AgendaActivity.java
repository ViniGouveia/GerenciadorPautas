package com.vinigouveia.gerenciadorpautas.activities;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.vinigouveia.gerenciadorpautas.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class AgendaActivity extends AppCompatActivity implements View.OnClickListener {

    //Instancia do objeto utilizado para mapear os elementos gráficos da activity
    private AgendaViewHolder mAgendaViewHolder = new AgendaViewHolder();
    private Intent intentToProfile;
    private Intent intentToHelp;
    private Intent intentToCreateNewAgenda;
    private Intent intentToOpenedAgendas;
    private Intent intentToClosedAgendas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        intentToProfile = new Intent(getApplicationContext(), ProfileAcessActivity.class);
        intentToHelp = new Intent(getApplicationContext(), HelpUserActivity.class);
        intentToCreateNewAgenda = new Intent(getApplicationContext(), CreateNewAgendaActivity.class);
        intentToOpenedAgendas = new Intent(getApplicationContext(), OpenedAgendasActivity.class);
        intentToClosedAgendas = new Intent(getApplicationContext(), ClosedAgendasActivity.class);


        //Mapeamento dos elementos gráficos
        this.mAgendaViewHolder.createNewAgenda = findViewById(R.id.button_create_new_agenda);
        this.mAgendaViewHolder.openedAgendas = findViewById(R.id.button_opened_agendas);
        this.mAgendaViewHolder.closedAgendas = findViewById(R.id.button_closed_agendas);

        //Mapeamento dos eventos de click
        this.mAgendaViewHolder.createNewAgenda.setOnClickListener(this);
        this.mAgendaViewHolder.openedAgendas.setOnClickListener(this);
        this.mAgendaViewHolder.closedAgendas.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_agenda, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.activity_profile) {
            startActivity(intentToProfile);
            return true;
        }

        if (id == R.id.activity_help) {
            startActivity(intentToHelp);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_create_new_agenda) {
            startActivity(intentToCreateNewAgenda);
        }
        if (v.getId() == R.id.button_opened_agendas) {
            startActivity(intentToOpenedAgendas);
        }
        if (v.getId() == R.id.button_closed_agendas) {
            startActivity(intentToClosedAgendas);
        }
    }

    private static class AgendaViewHolder {
        FloatingActionButton createNewAgenda;
        Button openedAgendas;
        Button closedAgendas;
    }
}
