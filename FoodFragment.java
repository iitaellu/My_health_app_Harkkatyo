//java -> eka file

package com.example.harjoitustyo_ida_viia;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class FoodFragment extends Fragment implements View.OnClickListener {

    TextView textTotal;
    EditText beefLevel, fishLevel, pork_PoultryLevel, dairyLevel, cheeseLevel, riceLevel, eggLevel, saladLevel, restaurantLevel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_food, container, false);

        beefLevel = (EditText) rootView.findViewById(R.id.beefLevel);
        fishLevel = (EditText) rootView.findViewById(R.id.fishLevel);
        pork_PoultryLevel = (EditText) rootView.findViewById(R.id.pork_PoultryLevel);
        dairyLevel = (EditText) rootView.findViewById(R.id.dairyLevel);
        cheeseLevel = (EditText) rootView.findViewById(R.id.cheeseLevel);
        riceLevel = (EditText) rootView.findViewById(R.id.riceLevel);
        eggLevel = (EditText) rootView.findViewById(R.id.eggLevel);
        saladLevel = (EditText) rootView.findViewById(R.id.saladLevel);
        restaurantLevel = (EditText) rootView.findViewById(R.id.restaurantLevel);

        Spinner spinnerPrefer = (Spinner) rootView.findViewById(R.id.spinnerPrefer);
        ArrayAdapter<CharSequence> preferAdapter = ArrayAdapter.createFromResource(getActivity(),R.array.Boolean, android.R.layout.simple_spinner_item);
        preferAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPrefer.setAdapter(preferAdapter);

        textTotal = (TextView) rootView.findViewById(R.id.textTotal);


        Button calculateButton = (Button) rootView.findViewById(R.id.calculateButton);
        calculateButton.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {

        String tempBeef = beefLevel.getText().toString();
        String tempFish = fishLevel.getText().toString();
        String tempPork_Poultry = pork_PoultryLevel.getText().toString();
        String tempDairy = dairyLevel.getText().toString();
        String tempCheese = cheeseLevel.getText().toString();
        String tempRice = riceLevel.getText().toString();
        String tempEgg = eggLevel.getText().toString();
        String tempSalad = saladLevel.getText().toString();
        String tempRestaurant = restaurantLevel.getText().toString();

        int beef = 0;
        int fish = 0;
        int pork_poultry = 0;
        int dairy = 0;
        int cheese = 0;
        int rice = 0;
        int egg = 0;
        int salad = 0;
        int restaurant = 0;
        String total = null;

        if(!"".equals(tempBeef)){
            beef=Integer.parseInt(tempBeef);
        }
        if(!"".equals(tempFish)){
            fish=Integer.parseInt(tempFish);
        }
        if(!"".equals(tempPork_Poultry)){
            pork_poultry=Integer.parseInt(tempPork_Poultry);
        }
        if(!"".equals(tempDairy)){
            dairy=Integer.parseInt(tempDairy);
        }
        if(!"".equals(tempCheese)){
            cheese=Integer.parseInt(tempCheese);
        }
        if(!"".equals(tempRice)){
            rice=Integer.parseInt(tempRice);
        }
        if(!"".equals(tempEgg)){
            egg=Integer.parseInt(tempEgg);
        }
        if(!"".equals(tempSalad)){
            salad=Integer.parseInt(tempSalad);
        }
        if(!"".equals(tempRestaurant)){
            restaurant=Integer.parseInt(tempRestaurant);
        }

        //total = beef;

        textTotal.setText(total);
        Toast.makeText(getActivity(),"Calculated and saved",Toast.LENGTH_LONG).show();

        //TODO kerää annetut tiedot parametreihin. Näiden parametrien avulla rakennetaan linkki,
        // nämä linkin tulokset kirjoitetaan xml tiedostoon. Total riittää!!!!
        // mikäli kirjoitus ei syystä tai toisesta onnistu Toastaa siitä!
        // faktaa https://www.nutrilett.fi/paivittaiset-kalorit-ja-kaloreiden-kulutus/
    }
}


