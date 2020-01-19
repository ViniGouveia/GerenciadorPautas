package com.vinigouveia.gerenciadorpautas.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;

import com.vinigouveia.gerenciadorpautas.Room.DBData.MyDatabase;
import com.vinigouveia.gerenciadorpautas.Room.DBEntities.AgendaEntity;
import com.vinigouveia.gerenciadorpautas.ExpandableListView.ExpandableAdapter;
import com.vinigouveia.gerenciadorpautas.R;
import com.vinigouveia.gerenciadorpautas.SharedPreferences.SecurityPreferences;
import com.vinigouveia.gerenciadorpautas.Constants.Constants;

import java.util.ArrayList;
import java.util.List;

public class ClosedAgendasActivity extends AppCompatActivity implements View.OnClickListener {

    private ClosedAgendasViewHolder mClosedAgendasViewHolder = new ClosedAgendasViewHolder();

    private MyDatabase db;

    private List<AgendaEntity> listGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_closed_agendas);

        db = MyDatabase.getAppDatabase(getApplicationContext());

        SecurityPreferences SharedPrefereces = new SecurityPreferences(getApplicationContext());
        String authorEmail = SharedPrefereces.getStoredString(Constants.USEREMAIL_KEY);

        listGroup = new ArrayList<>();

        List<AgendaEntity> agendas = db.agendaDao().getAllAgendasByStatus(authorEmail, false);

        this.mClosedAgendasViewHolder.expandableClosedAgendas = findViewById(R.id.expandable_list_view_closed_agendas);
        this.mClosedAgendasViewHolder.expandableClosedAgendas.setAdapter(new ExpandableAdapter(this, listGroup));

        this.mClosedAgendasViewHolder.buttonBackClosedAgendas = findViewById(R.id.button_back_closed_agendas);
        this.mClosedAgendasViewHolder.buttonBackClosedAgendas.setOnClickListener(this);

        addAgendas(agendas);

        this.mClosedAgendasViewHolder.expandableClosedAgendas.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                for (int i = 0; i < listGroup.size(); i++) {
                    if (mClosedAgendasViewHolder.expandableClosedAgendas.isGroupExpanded(i) && i != groupPosition) {
                        mClosedAgendasViewHolder.expandableClosedAgendas.collapseGroup(i);
                    }
                }
            }
        });
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_back_closed_agendas) {
            Intent intentBackClosedAgendas = new Intent(getApplicationContext(), AgendaActivity.class);
            startActivity(intentBackClosedAgendas);
        }
    }

    private void addAgendas(List<AgendaEntity> agendas) {
        for (int i = 0; i < agendas.size(); i++) {
            listGroup.add(agendas.get(i));
        }
    }

    public void reopenOrCloseAgenda(View view) {
        int group = (int) view.getTag();
        if (listGroup.get(group).getStatus()) {
            listGroup.get(group).setStatus(false);
            db.agendaDao().updateStatus(listGroup.get(group));
            finish();
            startActivity(getIntent());
        } else {
            listGroup.get(group).setStatus(true);
            db.agendaDao().updateStatus(listGroup.get(group));
            finish();
            startActivity(getIntent());
        }
    }

    private static class ClosedAgendasViewHolder {
        Button buttonBackClosedAgendas;
        ExpandableListView expandableClosedAgendas;
    }
}
