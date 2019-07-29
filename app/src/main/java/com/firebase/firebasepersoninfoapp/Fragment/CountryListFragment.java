package com.firebase.firebasepersoninfoapp.Fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.firebase.firebasepersoninfoapp.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class CountryListFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private static final int FINISH_ACTIVITY_REQUEST_CODE = 001;
    private Spinner s1,s2;
    private String stateArray[] =  null;
    private ArrayList<String> al;
    private ArrayAdapter<String> aa;
    private Button bDoneCountryListFragment,bCancelCountryListFragment;
    private FragmentCountryStateListener interfaceListener;

    public interface FragmentCountryStateListener {
        void onInputCountryStateSent(String country, String state);
    }

    public CountryListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        final View view =inflater.inflate(R.layout.country_listfragment,container,false);
        s1 = view.findViewById(R.id.a_countrylistfragment_spinner_country);
        s2 = view.findViewById(R.id.a_countrylistfragment_spinner_state);
        fillListOfCountries();
        bDoneCountryListFragment = view.findViewById(R.id.a_country_fragment_b_Done);
        bCancelCountryListFragment = view.findViewById(R.id.a_country_fragment_b_Cancel);

        bDoneCountryListFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String country = s1.getSelectedItem().toString();
                String state = s2.getSelectedItem().toString();

                interfaceListener.onInputCountryStateSent(country,state);
                Log.i("TAG",country+" "+state);
            }
        });

        bCancelCountryListFragment.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                getActivity().onBackPressed();
            }
        });
        selectCountry();
        return view;
    }


    public void selectCountry() {
        s1.setOnItemSelectedListener(CountryListFragment.this);
    }

    public void fillListOfCountries() {
        al = new ArrayList<String>();
        aa = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_activated_1,al);
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
        /*Intent intent =*/ getActivity().getIntent().addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        Intent intentOfParentActivity = getActivity().getParent().finishActivity(FINISH_ACTIVITY_REQUEST_CODE);
        Log.i("TAG","In onDetach of CountryListFragment");
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

}
