package com.vinigouveia.gerenciadorpautas.ExpandableListView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.vinigouveia.gerenciadorpautas.Room.DBEntities.AgendaEntity;
import com.vinigouveia.gerenciadorpautas.R;

import java.util.List;

public class ExpandableAdapter extends BaseExpandableListAdapter {
    private List<AgendaEntity> listGroup;
    private LayoutInflater inflater;

    public ExpandableAdapter(Context context, List<AgendaEntity> listGroup) {
        this.listGroup = listGroup;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getGroupCount() {
        return listGroup.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listGroup.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return listGroup.get(groupPosition); //listData.get(listGroup.get(groupPosition).getId());
    }

    @Override
    public long getGroupId(int groupPosition) {
        return listGroup.get(groupPosition).getId();
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return listGroup.get(groupPosition).getId();
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ViewHolderGroup viewHolderGroup;

        convertView = inflater.inflate(R.layout.header_expandable_list_view, null);
        viewHolderGroup = new ViewHolderGroup();
        convertView.setTag(viewHolderGroup);

        viewHolderGroup.textViewTitle = convertView.findViewById(R.id.text_view_title);
        viewHolderGroup.textViewSDescription = convertView.findViewById(R.id.text_view_sdescription);

        viewHolderGroup.textViewTitle.setText(listGroup.get(groupPosition).getTitle());
        viewHolderGroup.textViewSDescription.setText(listGroup.get(groupPosition).getShortDescription());

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolderItem viewHolderItem;
        AgendaEntity agendaAux = (AgendaEntity) getGroup(groupPosition);

        convertView = inflater.inflate(R.layout.item_expandable_list_view, null);
        viewHolderItem = new ViewHolderItem();
        convertView.setTag(viewHolderItem);

        viewHolderItem.textViewDescription = convertView.findViewById(R.id.text_view_description);
        viewHolderItem.textViewAuthor = convertView.findViewById(R.id.text_view_author);
        viewHolderItem.buttonOpenCloseAgenda = convertView.findViewById(R.id.button_open_close_agenda);

        viewHolderItem.textViewAuthor.setText(agendaAux.getAuthorName());
        viewHolderItem.textViewDescription.setText(agendaAux.getDescription());
        viewHolderItem.buttonOpenCloseAgenda.setTag(groupPosition);

        if (agendaAux.getStatus()) {
            viewHolderItem.buttonOpenCloseAgenda.setBackground(convertView.getResources().getDrawable(R.drawable.button_red_background));
            viewHolderItem.buttonOpenCloseAgenda.setText(String.format("%s", "Finalizar"));
        } else {
            viewHolderItem.buttonOpenCloseAgenda.setBackground(convertView.getResources().getDrawable(R.drawable.button_green_background));
            viewHolderItem.buttonOpenCloseAgenda.setText(String.format("%s", "Reabrir"));
        }

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    class ViewHolderGroup {
        TextView textViewTitle;
        TextView textViewSDescription;
    }

    class ViewHolderItem {
        TextView textViewDescription;
        TextView textViewAuthor;
        Button buttonOpenCloseAgenda;
    }

}
