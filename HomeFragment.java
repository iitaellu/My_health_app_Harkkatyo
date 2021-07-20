//java -> eka file

package com.example.harjoitustyo_ida_viia;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

public class HomeFragment extends Fragment implements View.OnClickListener{


    Button addFood, addWater, addSport, startPeriod;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        Button addFood = (Button) rootView.findViewById(R.id.addFood);
        Button addWater = (Button) rootView.findViewById(R.id.addWater);
        Button addSport = (Button) rootView.findViewById(R.id.addSport);
        Button startPeriod = (Button) rootView.findViewById(R.id.startPeriod);
        Button statistics = (Button) rootView.findViewById(R.id.statistics);

        addFood.setOnClickListener(this);
        addWater.setOnClickListener(this);
        addSport.setOnClickListener(this);
        startPeriod.setOnClickListener(this);
        statistics.setOnClickListener(this);

        return rootView;
    }
    @Override
    public void onClick(View v) {
        Fragment fragment = null;
        switch (v.getId()) {
            case R.id.addFood:
                fragment = new FoodFragment();
                replaceFragment(fragment);
                break;
            case R.id.addWater:
                fragment = new WaterFragment();
                replaceFragment(fragment);
                break;
            case R.id.addSport:
                fragment = new SportFragment();
                replaceFragment(fragment);
                break;
            case R.id.startPeriod:
                fragment = new PeriodFragment();
                replaceFragment(fragment);
                break;
            case R.id.statistics:
                fragment = new StatisticsFragment();
                replaceFragment(fragment);
                break;
        }
    }

    public void replaceFragment(Fragment someFragment) {
        FragmentTransaction transition = getFragmentManager().beginTransaction();
        transition.replace(R.id.fragment_container, someFragment);
        transition.addToBackStack(null);
        transition.commit();
    }
}
