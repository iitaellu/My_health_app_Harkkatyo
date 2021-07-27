//java -> eka file

package com.example.harjoitustyo_ida_viia;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class HomeFragment extends Fragment implements View.OnClickListener{


    Button addFood, addWater, addSport, startPeriod;

    //TODO pitäs siis tervehtiä käyttäjä-oliota
    // Date https://stackoverflow.com/questions/8654990/how-can-i-get-current-date-in-android


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        TextView dateTextHead = (TextView) rootView.findViewById(R.id.dateTextHead);

        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.DATE_FIELD).format(calendar.getTime());
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        String date = format.format(Date.parse(currentDate));
        dateTextHead.setText(date);

        TextView textFood = (TextView) rootView.findViewById(R.id.textFood);
        TextView textWater = (TextView) rootView.findViewById(R.id.textWater);
        TextView textSport = (TextView) rootView.findViewById(R.id.textSport);
        TextView textPeriod = (TextView) rootView.findViewById(R.id.textPeriod);
        

        //TODO seuraavat infot pitäs saada jostain
        textFood.setText("    Food counter\n    0/1800 kcal");
        textWater.setText("    Water counter\n    0/5 Glasses");
        textSport.setText("    Exercise\n    0 min");
        textPeriod.setText("    Menstrual tracker\n    15 day left");

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
    
    //This method change page what user see
    public void replaceFragment(Fragment someFragment) {
        FragmentTransaction transition = getFragmentManager().beginTransaction();
        transition.replace(R.id.fragment_container, someFragment);
        transition.addToBackStack(null);
        transition.commit();
    }
}
