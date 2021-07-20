//java -> eka file
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

public class ProfileFragment extends Fragment implements View.OnClickListener {

    Fragment fragment;
    Button edit;
    TextView userAge, userWeight, userHeight;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        userAge = (TextView) rootView.findViewById(R.id.userAge);
        userWeight = (TextView) rootView.findViewById(R.id.userWeight);
        userHeight = (TextView) rootView.findViewById(R.id.userHeight);

        //TODO tiedot saatava käyttäjä-oliosta

        userAge.setText("User age");
        userHeight.setText("User Height");
        userWeight.setText("User Weight");

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
}
