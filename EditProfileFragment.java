//java -> eka file

package com.example.harjoitustyo_ida_viia;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class EditProfileFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    Spinner ageSpinner, weightSpinner, heightSpinner;
    Button save;
    Fragment fragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_editprofile, container, false);

        ageSpinner = rootView.findViewById(R.id.ageSpinner);
        weightSpinner = rootView.findViewById(R.id.weightSpinner);
        heightSpinner = rootView.findViewById(R.id.heightSpinner);

        //PopulateAgeSpinner();

        //Populate Age spinner
        Integer [] age = new Integer[100];
        int Age = 15;

        for (int i = 0; i < age.length; i++){
            age[i] = Age;
            Age++;
        }
        ArrayAdapter<Integer> ageAdapter = new ArrayAdapter<Integer>(getActivity(), android.R.layout.simple_spinner_item, age);
        ageAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        ageSpinner.setAdapter(ageAdapter);

        //Populate weight spinner
        Double [] weight = new Double[250];
        double Weight = 30;

        for (int i = 0; i < weight.length; i++){
            weight[i] = Weight;
            Weight = (Weight + 0.5);
        }

        ArrayAdapter<Double> weightAdapter = new ArrayAdapter<Double>(getActivity(), android.R.layout.simple_spinner_item, weight);
        weightAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        weightSpinner.setAdapter(weightAdapter);

        //Populate height spinner
        Double [] height = new Double[320];
        double Height = 50;

        for (int i = 0; i < height.length; i++){
            height[i] = Height;
            Height = (Height + 0.5);
        }
        ArrayAdapter<Double> heightAdapter = new ArrayAdapter<Double>(getActivity(), android.R.layout.simple_spinner_item, height);
        heightAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        heightSpinner.setAdapter(heightAdapter);

        ageSpinner.setOnItemSelectedListener(this);
        weightSpinner.setOnItemSelectedListener(this);
        heightSpinner.setOnItemSelectedListener(this);

        //Syystä tai toisesra ylimääräiset huutelut, ennen minkään valitsemista...

        Button save = rootView.findViewById(R.id.edit);

        save.setOnClickListener(this);

        return rootView;
    }

    /*private void PopulateAgeSpinner(){
        Integer [] age = new Integer[100];
        int Age = 15;

        for (int i = 0; i < age.length; i++){
            age[i] = Age;
            Age++;
        }
        ArrayAdapter<Integer> ageAdapter = new ArrayAdapter<Integer>(getActivity(), android.R.layout.simple_spinner_item, age);
        ageAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        ageSpinner.setAdapter(ageAdapter);
    }*/
    @Override
    public void onClick(View v) {

        //TODO Tallenna valitut arvot tässäkohtaa,
        // muussa tapauksessa niiden on unohduttava

        fragment = new ProfileFragment();
        FragmentTransaction transition = getFragmentManager().beginTransaction();
        transition.replace(R.id.fragment_container, fragment);
        transition.addToBackStack(null);
        transition.commit();
        Toast.makeText(getActivity(),"Saved",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(parent.getId()==R.id.ageSpinner){
            Toast.makeText(getActivity(), "Age selected", Toast.LENGTH_SHORT).show();
        }
        else if(parent.getId()==R.id.weightSpinner){
            Toast.makeText(getActivity(), "Weight selected", Toast.LENGTH_SHORT).show();
        }
        else if (parent.getId()==R.id.heightSpinner){
            Toast.makeText(getActivity(), "Height selected", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}

//TODO Täytyy tallentaa tiedot ja koodin on kyettävä ottamaan spinnerien arvot talteen
