package com.vinigouveia.gerenciadorpautas.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;

import com.vinigouveia.gerenciadorpautas.data.Agenda;
import com.vinigouveia.gerenciadorpautas.ExpandableListView.ExpandableAdapter;
import com.vinigouveia.gerenciadorpautas.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ClosedAgendasActivity extends AppCompatActivity implements View.OnClickListener {

    private ClosedAgendasViewHolder mClosedAgendasViewHolder = new ClosedAgendasViewHolder();
    private List<Agenda> listGroup;
    private HashMap<Integer, String> listData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_closed_agendas);

        listGroup = new ArrayList<>();
        listData = new HashMap<>();

        teste();

        this.mClosedAgendasViewHolder.expandableClosedAgendas = findViewById(R.id.expandable_list_view_closed_agendas);
        this.mClosedAgendasViewHolder.expandableClosedAgendas.setAdapter(new ExpandableAdapter(this, listGroup, listData));

        this.mClosedAgendasViewHolder.buttonBackClosedAgendas = findViewById(R.id.button_back_closed_agendas);
        this.mClosedAgendasViewHolder.buttonBackClosedAgendas.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_back_closed_agendas) {
            Intent intentBackClosedAgendas = new Intent(getApplicationContext(), AgendaActivity.class);
            startActivity(intentBackClosedAgendas);
        }
    }

    private void teste() {
        Agenda agendaAux = (Agenda) getIntent().getSerializableExtra("agenda");
        if (agendaAux != null) {
            listGroup.add(agendaAux);
            listData.put(agendaAux.getAgendaId(), agendaAux.getAuthorEmail());
        }
    }

    public void reopenOrCloseAgenda(View view) {
        Intent intentCloseAgenda = new Intent(getApplicationContext(), AgendaActivity.class);
        startActivity(intentCloseAgenda);
    }

    private static class ClosedAgendasViewHolder {
        Button buttonBackClosedAgendas;
        ExpandableListView expandableClosedAgendas;
    }
}
