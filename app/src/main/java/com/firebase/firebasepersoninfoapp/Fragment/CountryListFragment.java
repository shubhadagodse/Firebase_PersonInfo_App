package com.firebase.firebasepersoninfoapp.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.firebase.firebasepersoninfoapp.Activity.CountryStateActivity;
import com.firebase.firebasepersoninfoapp.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class CountryListFragment extends Fragment {

    ListView lv;
    ArrayList<String> al;
    ArrayAdapter<String> aa;
    Button bDone,bCancel;

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
        al.add("Wyoming");

       /* bDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft1 =getFragmentManager().beginTransaction();
                ft1.replace(R.id.A_CountryState_frame1,new StateListFragment());
                ft1.commit();

            }
        });
    */
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FragmentTransaction ft1 =getFragmentManager().beginTransaction();
                ft1.replace(R.id.A_CountryState_frame1,new StateListFragment());
                ft1.commit();

                String s = al.get(position);
                CountryStateActivity cs = (CountryStateActivity) getActivity();
                cs.getCountry(s);
                cs.getState(s);

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
