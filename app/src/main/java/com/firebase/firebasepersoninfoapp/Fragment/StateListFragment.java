package com.firebase.firebasepersoninfoapp.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.firebasepersoninfoapp.Activity.CountryStateActivity;
import com.firebase.firebasepersoninfoapp.R;

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
        lv = view.findViewById(R.id.country_list);
        al = new ArrayList<String>();
        aa = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,al);
//        lv.setAdapter(aa);
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
                cs.getCountry(s);
                cs.getState(s);
            }
        });
        lv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(position) {
                    case 0 :
                        if(position == 0) {
                            Toast.makeText(getContext(),"Country selected "+position+"at position",Toast.LENGTH_SHORT).show();
                            //Intent intentForChinaCountry = new Intent(StateListFragment.this,CountryStateActivity.class);
                        }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        return view;
    }

}
