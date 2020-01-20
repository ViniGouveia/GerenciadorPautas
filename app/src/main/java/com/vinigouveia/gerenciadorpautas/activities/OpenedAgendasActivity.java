package com.vinigouveia.gerenciadorpautas.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;

import com.vinigouveia.gerenciadorpautas.Room.DBData.MyDatabase;
import com.vinigouveia.gerenciadorpautas.Room.DBEntities.AgendaEntity;
import com.vinigouveia.gerenciadorpautas.SharedPreferences.SecurityPreferences;
import com.vinigouveia.gerenciadorpautas.ExpandableListView.ExpandableAdapter;
import com.vinigouveia.gerenciadorpautas.R;
import com.vinigouveia.gerenciadorpautas.Constants.Constants;

import java.util.ArrayList;
import java.util.List;

public class OpenedAgendasActivity extends AppCompatActivity implements View.OnClickListener {

    private OpenedAgendasViewHolder mOpenedAgendasViewHolder = new OpenedAgendasViewHolder();

    private MyDatabase db;

    private List<AgendaEntity> listGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opened_agendas);

        db = MyDatabase.getAppDatabase(getApplicationContext());

        SecurityPreferences SharedPrefereces = new SecurityPreferences(getApplicationContext());
        String authorEmail = SharedPrefereces.getStoredString(Constants.USEREMAIL_KEY);

        listGroup = new ArrayList<>();

        List<AgendaEntity> agendas = db.agendaDao().getAllAgendasByStatus(authorEmail, true);

        this.mOpenedAgendasViewHolder.expandableOpenedAgendas = findViewById(R.id.expandable_list_view_opened_agendas);
        this.mOpenedAgendasViewHolder.expandableOpenedAgendas.setAdapter(new ExpandableAdapter(this, listGroup));

        this.mOpenedAgendasViewHolder.buttonBackOpenedAgendas = findViewById(R.id.button_back_opened_agendas);
        this.mOpenedAgendasViewHolder.buttonBackOpenedAgendas.setOnClickListener(this);

        addAgendas(agendas);

        this.mOpenedAgendasViewHolder.expandableOpenedAgendas.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                for (int i = 0; i < listGroup.size(); i++) {
                    if (mOpenedAgendasViewHolder.expandableOpenedAgendas.isGroupExpanded(i) && i != groupPosition) {
                        mOpenedAgendasViewHolder.expandableOpenedAgendas.collapseGroup(i);
                    }
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_back_opened_agendas) {
            Intent intentBackOpenedAgendas = new Intent(getApplicationContext(), AgendaActivity.class);
            startActivity(intentBackOpenedAgendas);
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

    private void addAgendas(List<AgendaEntity> agendas) {
        for (int i = 0; i < agendas.size(); i++) {
            listGroup.add(agendas.get(i));
        }
    }

    private static class OpenedAgendasViewHolder {
        Button buttonBackOpenedAgendas;
        ExpandableListView expandableOpenedAgendas;
    }
}
