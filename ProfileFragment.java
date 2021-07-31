package com.example.harjoitustyo_ida_viia;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ProfileFragment extends Fragment implements View.OnClickListener {

    String person;
    String filename = ".ProfileInfo.csv";
    String name = ".newProf.csv";

    Fragment fragment;
    Button edit;
    TextView userProfile, userAge, userWeight, userHeight;

    FirebaseAuth fAuth;
    FirebaseFirestore fStore;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        person = fAuth.getCurrentUser().getUid();


        userAge = (TextView) rootView.findViewById(R.id.userAge);
        userWeight = (TextView) rootView.findViewById(R.id.userWeight);
        userHeight = (TextView) rootView.findViewById(R.id.userHeight);
        userProfile = (TextView) rootView.findViewById(R.id.userProfile);

        //TODO tiedot saatava käyttäjä-oliosta

        String [] userinfo = readFile(filename, person);
        if(userinfo != null) {
            userAge.setText(userinfo[0]);
            userWeight.setText(userinfo[1]);
            userHeight.setText(userinfo[2]);
        }
        else{
            userAge.setText("");
            userWeight.setText("");
            userHeight.setText("");
        }

        String [] nameinfo = readFile(name, person);
        if(nameinfo != null) {
            userProfile.setText(nameinfo[0]);
        }
        else {
            userProfile.setText("USer name");
        }

        Button edit = rootView.findViewById(R.id.edit);

        edit.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        fragment = new EditProfileFragment();
        FragmentTransaction transition = getFragmentManager().beginTransaction();
        transition.replace(R.id.fragment_container, fragment);
        transition.addToBackStack(null);
        transition.commit();
    }

    public String[] readFile(String name,String person) {
        BufferedReader br = null;
        try {
            String line;
            String[] lines;
            br = new BufferedReader(new FileReader(getActivity().getFilesDir().getPath() + "/" + person+name));
            StringBuffer buffer = new StringBuffer();
            while ((line = br.readLine()) != null) {
                line = line+",";
                buffer.append(line);
            }
            String result = buffer.toString();
            lines = result.split(",");

            String wanted = lines[lines.length-1];
            String[] userinfo = wanted.split(";");
            return userinfo;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        String[] userinfo = null;
        return userinfo;
    }

}

