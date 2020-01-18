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

public class OpenedAgendasActivity extends AppCompatActivity implements View.OnClickListener {

    private OpenedAgendasViewHolder mOpenedAgendasViewHolder = new OpenedAgendasViewHolder();
    private List<Agenda> listGroup;
    private HashMap<Integer, String> listData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opened_agendas);

        listGroup = new ArrayList<>();
        listData = new HashMap<>();

        Agenda agendaAux1 = new Agenda(1, "Titulo1", "Subtitulo1", "Ta funcionando", "Vinicius", "vini.gouveia11@gmail.com");
        Agenda agendaAux2 = new Agenda(2, "Titulo2", "Subtitulo2", "Ta funcionando tbm", "Vinicius", "vini.gouveia11@gmail.com");

        teste(agendaAux1);
        teste(agendaAux2);


        this.mOpenedAgendasViewHolder.expandableOpenAgendas = findViewById(R.id.expandable_list_view_opened_agendas);
        this.mOpenedAgendasViewHolder.expandableOpenAgendas.setAdapter(new ExpandableAdapter(this, listGroup, listData));

        this.mOpenedAgendasViewHolder.buttonBackOpenedAgendas = findViewById(R.id.button_back_opened_agendas);
        this.mOpenedAgendasViewHolder.buttonBackOpenedAgendas.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_back_opened_agendas) {
            Intent intentBackOpenedAgendas = new Intent(getApplicationContext(), AgendaActivity.class);
            startActivity(intentBackOpenedAgendas);
        }
    }

    public void reopenOrCloseAgenda(View view) {
        Intent intentCloseAgenda = new Intent(getApplicationContext(), ClosedAgendasActivity.class);
        intentCloseAgenda.putExtra("agenda", listGroup.get(0));
        startActivity(intentCloseAgenda);
    }

    private void teste(Agenda agenda) {
        listGroup.add(agenda);
        listData.put(agenda.getAgendaId(), agenda.getAuthorEmail());
    }

    private static class OpenedAgendasViewHolder {
        Button buttonBackOpenedAgendas;
        ExpandableListView expandableOpenAgendas;
    }
}
