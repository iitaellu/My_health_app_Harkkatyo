//java -> eka file

package com.example.harjoitustyo_ida_viia;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FoodFragment extends Fragment implements View.OnClickListener {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_food, container, false);

        EditText beefLevel = (EditText) rootView.findViewById(R.id.beefLevel);
        EditText fishLevel = (EditText) rootView.findViewById(R.id.fishLevel);
        EditText pork_PoultryLevel = (EditText) rootView.findViewById(R.id.pork_PoultryLevel);
        EditText dairyLevel = (EditText) rootView.findViewById(R.id.dairyLevel);
        EditText cheeseLevel = (EditText) rootView.findViewById(R.id.cheeseLevel);
        EditText riceLevel = (EditText) rootView.findViewById(R.id.riceLevel);
        EditText eggLevel = (EditText) rootView.findViewById(R.id.eggLevel);
        EditText saladLevel = (EditText) rootView.findViewById(R.id.saladLevel);
        EditText restaurantLevel = (EditText) rootView.findViewById(R.id.restaurantLevel);

        Spinner spinnerPrefer = (Spinner) rootView.findViewById(R.id.spinnerPrefer);
        ArrayAdapter<CharSequence> preferAdapter = ArrayAdapter.createFromResource(getActivity(),R.array.Boolean, android.R.layout.simple_spinner_item);
        preferAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPrefer.setAdapter(preferAdapter);

        Button calculateButton = (Button) rootView.findViewById(R.id.calculateButton);
        calculateButton.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(getActivity(),"Calculated and saved",Toast.LENGTH_LONG).show();

        //TODO kerää annetut tiedot parametreihin. Näiden parametrien avulla rakennetaan linkki,
        // nämä linkin tulokset kirjoitetaan xml tiedostoon. Total riittää!!!!
        // mikäli kirjoitus ei syystä tai toisesta onnistu Toastaa siitä!
        // faktaa https://www.nutrilett.fi/paivittaiset-kalorit-ja-kaloreiden-kulutus/
    }
}
