package com.firebase.firebasepersoninfoapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class StateListFragment extends Fragment {
    ListView lv;
    ArrayList<String> al;
    ArrayAdapter<String> aa;



    public StateListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_state_list,container,false);
        lv = view.findViewById(R.id.stateList);
        al = new ArrayList<String>();
        aa = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,al);
        lv.setAdapter(aa);
        al.add("Beijing Municipality");
        al.add("Tianjin Municipality");
        al.add("Hebei");
        al.add("Shanxi");
        al.add("Inner Mongolia");
        al.add("Liaoning");
        al.add("Jilin");
        al.add("Heilongjiang");

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = al.get(position);
                CountryStateActivity cs = new CountryStateActivity();
                cs.getState(s);
            }
        });


        return view;
    }

}
