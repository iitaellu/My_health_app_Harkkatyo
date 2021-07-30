package com.example.harjoitustyo_ida_viia;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class EditProfileFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    Spinner ageSpinner, weightSpinner, heightSpinner;
    Button save;
    Fragment fragment;

    String person;
    String name =".ProfileInfo.csv";

    FirebaseAuth fAuth;
    FirebaseFirestore fStore;

    //String diet = spinnerDiet.getSelectedItem().toString();   tällä saat arvot spinneresitä....

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_editprofile, container, false);

        ageSpinner = rootView.findViewById(R.id.ageSpinner);
        weightSpinner = rootView.findViewById(R.id.weightSpinner);
        heightSpinner = rootView.findViewById(R.id.heightSpinner);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        person = fAuth.getCurrentUser().getUid();

        createFile(person);

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

        String userAge = ageSpinner.getSelectedItem().toString();

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

        String userWeight = weightSpinner.getSelectedItem().toString();

        //Populate height spinner
        Double [] height = new Double[320];
        double Height = 120;

        for (int i = 0; i < height.length; i++){
            height[i] = Height;
            Height = (Height + 0.5);
        }
        ArrayAdapter<Double> heightAdapter = new ArrayAdapter<Double>(getActivity(), android.R.layout.simple_spinner_item, height);
        heightAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        heightSpinner.setAdapter(heightAdapter);

        String userHeight = heightSpinner.getSelectedItem().toString();

        ageSpinner.setOnItemSelectedListener(this);
        weightSpinner.setOnItemSelectedListener(this);
        heightSpinner.setOnItemSelectedListener(this);

        //Syystä tai toisesra ylimääräiset huutelut, ennen minkään valitsemista...

        createFile(person);

        Button save = rootView.findViewById(R.id.edit);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeFile(userAge,userWeight, userHeight, person);

                fragment = new ProfileFragment();
                FragmentTransaction transition = getFragmentManager().beginTransaction();
                transition.replace(R.id.fragment_container, fragment);
                transition.addToBackStack(null);
                transition.commit();
                Toast.makeText(getActivity(),"Saved",Toast.LENGTH_LONG).show();
            }
        });

        //save.setOnClickListener(this);

        return rootView;
    }

    /*
    @Override
    public void onClick(View v) {

        //TODO Tallenna valitut arvot tässäkohtaa,
        // muussa tapauksessa niiden on unohduttava

        writeFile(userAge,userWeight, userHeight, person);

        fragment = new ProfileFragment();
        FragmentTransaction transition = getFragmentManager().beginTransaction();
        transition.replace(R.id.fragment_container, fragment);
        transition.addToBackStack(null);
        transition.commit();
        Toast.makeText(getActivity(),"Saved",Toast.LENGTH_LONG).show();
    }

     */

    //tän voi ottaa pois luokan.
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(parent.getId()==R.id.ageSpinner){
            //Toast.makeText(getActivity(), "Age selected", Toast.LENGTH_SHORT).show();
        }
        else if(parent.getId()==R.id.weightSpinner){
            //Toast.makeText(getActivity(), "Weight selected", Toast.LENGTH_SHORT).show();
        }
        else if (parent.getId()==R.id.heightSpinner){
            //Toast.makeText(getActivity(), "Height selected", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }


    public void createFile(String person){
        try {
            String content = "Age;Weight;Height\n";
            File file = new File(getActivity().getFilesDir().getPath()+"/"+person+name);

            if(!file.exists()){
                file.createNewFile();
                System.out.println(file);
                OutputStreamWriter writer = new OutputStreamWriter(getActivity().openFileOutput(person+name, Context.MODE_PRIVATE));
                writer.write(content);
                writer.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void writeFile(String age, String weight, String height, String person) {
        try (FileWriter fw = new FileWriter(getActivity().getFilesDir().getPath() +"/"+ person+name, true)) {
            BufferedWriter writer = new BufferedWriter(fw);
            writer.append(age+";"+weight+";"+height+"\n");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
