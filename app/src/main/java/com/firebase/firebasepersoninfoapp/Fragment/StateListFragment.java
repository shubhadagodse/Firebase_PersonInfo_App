package com.firebase.firebasepersoninfoapp.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
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
    ListView lv, listOfChina,listOfIndia,listOfMexico,listOfWyoming;
    ArrayList<String> al;
    ArrayAdapter<String> aa;



    public StateListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_state_list,container,false);
        lv = view.findViewById(R.id.country_list_fragment);
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
        lv.setAdapter(aa);

        lv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String s = al.get(position);
                CountryStateActivity cs = new CountryStateActivity();
               // cs.getCountry(s);
                cs.getState(s);

                switch(position) {
                    case 0 :
                        if(position == 0) {
                            Toast.makeText(getContext(),"Country selected "+position+"at position",Toast.LENGTH_SHORT).show();
                            Intent intentForChinaCountry = new Intent(getContext(),CountryStateActivity.class);
                            listOfChina = lv.findViewById(R.id.stateList);
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
                            lv.setAdapter(aa);
                        }

                    case 1 :
                        if(position == 1) {
                            Toast.makeText(getContext(),"country selected "+position+"at position",Toast.LENGTH_SHORT).show();
                            Intent intentForIndia = new Intent(getContext(),CountryStateActivity.class);
                            listOfIndia = lv.findViewById(R.id.stateList);
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
                            lv.setAdapter(aa);
                        }

                    case 2 :
                        if(position == 2) {
                            Toast.makeText(getContext(),"country selected "+position+"at position",Toast.LENGTH_SHORT).show();
                            Intent intentForMexico = new Intent(getContext(),CountryStateActivity.class);
                            listOfMexico = lv.findViewById(R.id.stateList);
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
                            lv.setAdapter(aa);
                        }

                    case 3 :
                        if(position == 3) {
                            Toast.makeText(getContext(),"country selected "+position+"at position",Toast.LENGTH_SHORT).show();
                            Intent intentForWyoming = new Intent(getContext(),CountryStateActivity.class);
                            listOfWyoming = lv.findViewById(R.id.stateList);
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
                            lv.setAdapter(aa);
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
