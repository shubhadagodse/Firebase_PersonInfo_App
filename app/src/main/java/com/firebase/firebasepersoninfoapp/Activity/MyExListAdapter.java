package com.firebase.firebasepersoninfoapp.Activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.firebase.firebasepersoninfoapp.R;

import java.util.List;
import java.util.Map;

public class MyExListAdapter extends BaseExpandableListAdapter {

    Context context;
    List<String> countries;
    Map<String,List<String>> states;

    public MyExListAdapter() {
        this.context = context;
        this.countries = countries;
        this.states = states;
    }

    @Override
    public int getGroupCount() {
        return countries.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return states.get(countries.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return countries.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return states.get(countries.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String country = (String) getGroup(groupPosition);
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView =inflater.inflate(R.layout.country_listfragment,null);
        }

        TextView txtCountry = convertView.findViewById(R.id.a_main_tv_country);
        txtCountry.setText(country);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String state = (String) getChild(groupPosition,childPosition);
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView =inflater.inflate(R.layout.fragment_state_list,null);
        }

        TextView txtState = convertView.findViewById(R.id.a_main_tv_state);
        txtState.setText(state);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
