package com.firebase.firebasepersoninfoapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class CountryListFragment extends Fragment {

    ListView lv;
    ArrayList<String> al;
    ArrayAdapter<String> aa;



    public CountryListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.country_listfragment,container,false);
        lv = view.findViewById(R.id.country_list);
        al = new ArrayList<String>();
        aa = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,al);
        lv.setAdapter(aa);
        al.add("China");
        al.add("India");
        al.add("Mexico");
        al.add("China");

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = al.get(position);
                CountryStateActivity cs = new CountryStateActivity();
                cs.getCountry(s);
            }
        });


       /* String[] countries= {
                            "China",
                            "India",
                            "Mexico",
                            "USA"
        };
        ListView listView = view.findViewById(R.id.country_list);

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String >(
                              getActivity(),
                              android.R.layout.simple_list_item_1,
                              countries
        );
        listView.setAdapter(listViewAdapter);
        */
        return view;
    }

}
