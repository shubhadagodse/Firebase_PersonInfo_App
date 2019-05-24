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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.firebasepersoninfoapp.Activity.CountryStateActivity;
import com.firebase.firebasepersoninfoapp.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class CountryListFragment extends Fragment implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    ListView lv;
    Spinner s1,s2;
    String stateArray[] =  null;
    TextView tvCountry, tvState;

    ArrayList<String> al;
    ArrayAdapter<String> aa;
    Button bDoneCountryListFragment,bCancelCountryListFragment,bSendDataToActivity;
    private FragmentCountryStateListener interfaceListener;

    public interface FragmentCountryStateListener {
        void onInputCountryStateSent(String country, String state);
    }
    public CountryListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view =inflater.inflate(R.layout.country_listfragment,container,false);

        s1 = view.findViewById(R.id.a_countrylistfragment_spinner_country);
        s2 = view.findViewById(R.id.a_countrylistfragment_spinner_state);
        tvCountry = view.findViewById(R.id.a_countrylistfragment_tv_country);
        tvState = view.findViewById(R.id.a_countrylistfragment_tv_state);

        fillListOfCountries();
        bSendDataToActivity = view.findViewById(R.id.a_country_fragment_b_SendDataToActivity);
        bDoneCountryListFragment = view.findViewById(R.id.a_country_fragment_b_Done);
        bCancelCountryListFragment = view.findViewById(R.id.a_country_fragment_b_Cancel);

        bDoneCountryListFragment.setOnClickListener(this);

        bSendDataToActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String country = s1.getSelectedItem().toString();
                String state = s2.getSelectedItem().toString();
                tvCountry.setText("Country : "+country);
                tvState.setText("State : "+state);

                Log.i("TAG","Country=="+tvCountry.getText());
                Log.i("TAG","State=="+tvState.getText());

                interfaceListener.onInputCountryStateSent(country,state);
//                CountryStateActivity cs = (CountryStateActivity) getActivity();
//                cs.receiveDataFromCountryStateFragment(country,state);

//                PersonActivity personActivity = (PersonActivity) getActivity();
//                personActivity.receiveDataFromCountryStateFragment(country,state);
                Log.i("TAG",country+" "+state);
            }
        });

        bCancelCountryListFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDetach();
            }
        });
        selectCountry();
        return view;

    }


    public void selectCountry() {

        s1.setOnItemSelectedListener(CountryListFragment.this);

    }

    public void setData(String c,String s) {
        tvCountry.setText(c);
        tvState.setText(s);
    }

    public void sendDataToActivity() {

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String selectedItem = (String) parent.getItemAtPosition(position);
                Log.i("TAG",selectedItem);

                Toast.makeText(getContext(), "selectedItem", Toast.LENGTH_SHORT).show();

                Intent intent =new Intent(getActivity(),CountryStateActivity.class);
                intent.putExtra("country","defaultState");
                startActivityForResult(intent,2);


                Intent resultIntent =new Intent();
                resultIntent.putExtra("RESULT",selectedItem);

                selectedItem = (String) lv.getSelectedItem();
//                Log.i("TAG",selectedItem);
                CountryStateActivity csObject = (CountryStateActivity)getActivity();
//                csObject.receiveCountryFromFragment(selectedItem);

                FragmentTransaction ft1 =getFragmentManager().beginTransaction();
//                ft1.replace(R.id.country_list_fragment,new StateListFragment());
                ft1.commit();
            }
        });
    }

    public void fillListOfCountries() {
        al = new ArrayList<String>();
        aa = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_activated_1,al);
//        lv.setAdapter(aa);
        al.add("China");
        al.add("India");
        al.add("Mexico");
        al.add("USA");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i("TAG","onAttach() called");
        if(context instanceof FragmentCountryStateListener) {
            interfaceListener = (FragmentCountryStateListener) context;
        } else {
            throw new RuntimeException(context.toString()
            +"must implement FragmentCountryListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        interfaceListener = null;
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("TAG"," in onPause of CountryListFragment");
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(position == 0) {
            stateArray = new String[]{"Beijing Municipality","Tianjin Municipality","Hebei","Shanxi","Inner Mongolia","Liaoning","Jilin","Heilongjiang"};
        }
        if (position == 1) {
            stateArray = new String[]{"Andhra Pradesh","Arunachal Pradesh","Assam","Bihar","Chhattisgarh","Goa","Gujarat","Haryana","Himachal Pradesh","Jammu & Kashmir",
                    "Jharkhand","Karnataka","Kerala","Madhya Pradesh","Maharashtra","Manipur","Meghalaya","Mizoram","Nagaland","Orissa","Punjab","Rajasthan","Sikkim",
                    "Tamil Nadu","Telangana","Tripura","Uttar Pradesh","Uttarakhand","West Bengal"};
        }
        if(position == 2) {
            stateArray = new String[]{"Aguascalientes","Baja California","Baja California Sur","Chihuahua","Colima","Campeche","Coahuila","Chiapas","Federal District","Durango"
                    ,"Guerrero","Guanajuato","Hidalgo","Jalisco","México State","Michoacán","Morelos","Nayarit","Nuevo León","Oaxaca","Puebla","Querétaro","Quintana Roo",
                    "Sinaloa","San Luis Potosí","Sonora","Tabasco","Tlaxcala","Tamaulipas","Veracruz","Yucatán","Zacatecas"};
        }
        if(position == 3) {
            stateArray = new String[]{"Alabama","Alaska","Arizona","Arkansas","California","Colorado","Connecticut","Delaware","Florida","Georgia","Hawaii","Idaho","Illinois",
                    "Indiana","Iowa","Kansas","Kentucky","Louisiana","Maine","Maryland","Massachusetts","Michigan","Minnesota","Mississippi","Missouri","Montana","Nebraska",
                    "Nevada","New Hampshire","New Jersey","New Mexico","New York","North Carolina","North Dakota",
                    "Ohio","Oklahoma","Oregon", "Pennsylvania", "Rhode Island", "South Carolina", "South Dakota", "Tennessee", "Texas", "Utah", "Vermont",
                    "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming"
            };
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,stateArray);
        s2.setAdapter(adapter);


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        String country = s1.getSelectedItem().toString();
        String state = s2.getSelectedItem().toString();
        tvCountry.setText("Country : "+country);
        tvState.setText("State : "+state);
    }


}
