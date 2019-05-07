package com.firebase.firebasepersoninfoapp.Fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
    Button bDoneCountryListFragment,bCancelCountryListFragment;

    public CountryListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.country_listfragment,container,false);

        lv = view.findViewById(R.id.country_list_fragment);
        bDoneCountryListFragment = view.findViewById(R.id.a_country_fragment_b_Done);
        bCancelCountryListFragment = view.findViewById(R.id.a_country_fragment_b_Cancel);

        fillListOfCountries();

        bDoneCountryListFragment.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               sendDataToActivity();
           }
       });
        return view;

    }

    public void sendDataToActivity() {

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String selectedItem = (String) parent.getItemAtPosition(position);
                Log.i("TAG",selectedItem);

                Toast.makeText(getContext(), "selectedItem", Toast.LENGTH_SHORT).show();

                Intent intent =new Intent(getActivity(),CountryStateActivity.class);
                intent.putExtra("state","defaultState");
                startActivityForResult(intent,123);

//                Intent i = new Intent(getActivity().getBaseContext(),CountryStateActivity.class);
//                i.putExtra("country",lv.getSelectedItem().toString());

                String countryName = lv.getSelectedItem().toString();
                Log.i("TAG",countryName);
                CountryStateActivity csObject = (CountryStateActivity)getActivity();
                csObject.receiveCountryFromFragment(countryName);

                FragmentTransaction ft1 =getFragmentManager().beginTransaction();
                ft1.replace(R.id.country_list,new StateListFragment());
                ft1.commit();
            }
        });
    }

    public void fillListOfCountries() {

        al = new ArrayList<String>();
        aa = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,al);
        lv.setAdapter(aa);
        al.add("China");
        al.add("India");
        al.add("Mexico");
        al.add("Wyoming");

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i("TAG","onAttach() called");
    }
}
