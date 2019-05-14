package com.firebase.firebasepersoninfoapp.Fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import android.widget.Toast;

import com.firebase.firebasepersoninfoapp.Activity.CountryStateActivity;
import com.firebase.firebasepersoninfoapp.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class CountryListFragment extends Fragment {

    ListView lv;
//    List<String> countries;
//    Map<String,List<String>> states;

    ArrayList<String> al;
    ArrayAdapter<String> aa;
    Button bDoneCountryListFragment,bCancelCountryListFragment;

    public CountryListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view =inflater.inflate(R.layout.country_listfragment,container,false);
        lv = view.findViewById(R.id.country_list_fragment);

        fillListOfCountries();
        fillListOfStates();
//        fillData();
        bDoneCountryListFragment = view.findViewById(R.id.a_country_fragment_b_Done);
        bCancelCountryListFragment = view.findViewById(R.id.a_country_fragment_b_Cancel);

        bCancelCountryListFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDetach();
            }
        });

        bDoneCountryListFragment.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               sendDataToActivity();
           }
       });

        return view;

    }

    public void fillListOfStates() {

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = al.get(position);
                CountryStateActivity csObject = (CountryStateActivity) getActivity();
                csObject.functionForStates(s);
            }
        });

        lv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(),"Item selected is "+position,Toast.LENGTH_SHORT).show();
                bDoneCountryListFragment.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                        FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction transaction =fragmentManager.beginTransaction();
                        CountryListFragment countryFragment = new CountryListFragment();
                        transaction.add(R.id.container,countryFragment);
                        transaction.commit();

                            }
//                        Intent intent1 = view.getIntent();
//                        String country = intent1.getStringExtra("country");
//                        intent1.putExtra("country", String.valueOf(country));
//
//                        FragmentManager fragmentManager = getFragmentManager();
//                        FragmentTransaction transaction =fragmentManager.beginTransaction();
//                        CountryListFragment countryFragment = new CountryListFragment();
//                        transaction.replace(R.id.container,countryFragment).commit();
//
//                        Intent intent = getIntent();
//                        country = intent.getStringExtra("country"+country);
//                        intent.putExtra("RESULT",country);
//                        setResult(RESULT_OK,intent);
//                        finish();
//                    }
                        });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
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
                ft1.replace(R.id.country_list_fragment,new StateListFragment());
                ft1.commit();
            }
        });
    }

    public void fillListOfCountries() {
        al = new ArrayList<String>();
        aa = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_activated_1,al);
        lv.setAdapter(aa);
        al.add("China");
        al.add("India");
        al.add("Mexico");
        al.add("USA");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i("TAG","onAttach() called");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("TAG"," in onPause of CountryListFragment");
    }

    //    public void fillData() {
//
//        countries = new ArrayList<>();
//        states = new HashMap<>();
//        countries.add("China");
//        countries.add("India");
//        countries.add("Mexico");
//        countries.add("Wyoming");
//
//        List<String> china =new ArrayList<>();
//        List<String> india = new ArrayList<>();
//        List<String> mexico = new ArrayList<>();
//        List<String> usa = new ArrayList<>();
//
//        china.add("Beijing Municipality");
//        china.add("Tianjin Municipality");
//        china.add("Hebei");
//        china.add("Shanxi");
//        china.add("Inner Mongolia");
//        china.add("Liaoning");
//        china.add("Jilin");
//        china.add("Heilongjiang");
//        china.add("Shanghai Municipality");
//        china.add("Jiangsu");
//        china.add("Zhejiang");
//        china.add("Anhui");
//        china.add("Fujian");
//        china.add("Jiangxi");
//        china.add("Shandong");
//        china.add("Henan");
//        china.add("Hubei");
//        china.add("Hunan");
//        china.add("Guangdong");
//        china.add("Guangxi Zhuang");
//        china.add("Hainan");
//        china.add("Chongqing Municipality");
//        china.add("Sichuan");
//        china.add("Guizhou");
//        china.add("Yunnan");
//        china.add("Tibet Autonomous Region");
//        china.add("Shaanxi");
//        china.add("Gansu");
//        china.add("Qinghai");
//        china.add("Ninghxia Hui");
//        china.add("Xiajiang Uyghur");
//        china.add("Hong Kong");
//        china.add("Macau");
//        china.add("Taiwan");
//
//        india.add("Andhra Pradesh");
//        india.add("Arunachal Pradesh");
//        india.add("Assam");
//        india.add("Bihar");
//        india.add("Chhattisgarh");
//        india.add("Goa");
//        india.add("Gujarat");
//        india.add("Haryana");
//        india.add("Himachal Pradesh");
//        india.add("Jammu & Kashmir ");
//        india.add("Jharkhand ");
//        india.add("Karnataka");
//        india.add("Kerala");
//        india.add("Madhya Pradesh ");
//        india.add("Maharashtra ");
//        india.add("Manipur ");
//        india.add("Meghalaya ");
//        india.add("Mizoram ");
//        india.add("Nagaland ");
//        india.add("Orissa");
//        india.add("Punjab");
//        india.add("Rajasthan");
//        india.add("Sikkim");
//        india.add("Tamil Nadu ");
//        india.add("Telangana");
//        india.add("Tripura");
//        india.add("Uttar Pradesh ");
//        india.add("Uttarakhand");
//        india.add("West Bengal ");
//
//        mexico.add("Aguascalientes");
//        mexico.add("Baja California");
//        mexico.add("Baja California Sur");
//        mexico.add("Chihuahua");
//        mexico.add("Colima");
//        mexico.add("Campeche");
//        mexico.add("Coahuila");
//        mexico.add("Chiapas");
//        mexico.add("Federal District");
//        mexico.add("Durango");
//        mexico.add("Guerrero");
//        mexico.add("Guanajuato");
//        mexico.add("Hidalgo");
//        mexico.add("Jalisco");
//        mexico.add("México State");
//        mexico.add("Michoacán");
//        mexico.add("Morelos");
//        mexico.add("Nayarit");
//        mexico.add("Nuevo León");
//        mexico.add("Oaxaca");
//        mexico.add("Puebla");
//        mexico.add("Querétaro");
//        mexico.add("Quintana Roo");
//        mexico.add("Sinaloa");
//        mexico.add("San Luis Potosí");
//        mexico.add("Sonora");
//        mexico.add("Tabasco");
//        mexico.add("Tlaxcala");
//        mexico.add("Tamaulipas");
//        mexico.add("Veracruz");
//        mexico.add("Yucatán");
//        mexico.add("Zacatecas");
//
//        usa.add("Alabama");
//        usa.add("Alaska");
//        usa.add("Arizona");
//        usa.add("Arkansas");
//        usa.add("California");
//        usa.add("Colorado");
//        usa.add("Connecticut");
//        usa.add("Delaware");
//        usa.add("Florida");
//        usa.add("Georgia");
//        usa.add("Hawaii");
//        usa.add("Idaho");
//        usa.add("Illinois");
//        usa.add("Indiana");
//        usa.add("Iowa");
//        usa.add("Kansas");
//        usa.add("Kentucky");
//        usa.add("Louisiana");
//        usa.add("Maine");
//        usa.add("Maryland");
//        usa.add("Massachusetts");
//        usa.add("Michigan");
//        usa.add("Minnesota");
//        usa.add("Mississippi");
//        usa.add("Missouri");
//        usa.add("Montana");
//        usa.add("Nebraska");
//        usa.add("Nevada");
//        usa.add("New Hampshire");
//        usa.add("New Jersey");
//        usa.add("New Mexico");
//        usa.add("New York");
//        usa.add("North Carolina");
//        usa.add("North Dakota");
//        usa.add("Ohio");
//        usa.add("Oklahoma");
//        usa.add("Oregon");
//        usa.add("Pennsylvania");
//        usa.add("Rhode Island");
//        usa.add("South Carolina");
//        usa.add("South Dakota");
//        usa.add("Tennessee");
//        usa.add("Texas");
//        usa.add("Utah");
//        usa.add("Vermont");
//        usa.add("Virginia");
//        usa.add("Washington");
//        usa.add("West Virginia");
//        usa.add("Wisconsin");
//        usa.add("Wyoming");
//
//        states.put(countries.get(0),china);
//        states.put(countries.get(1),india);
//        states.put(countries.get(2),mexico);
//        states.put(countries.get(3),usa);
//    }

}
