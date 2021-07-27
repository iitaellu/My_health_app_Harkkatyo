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

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class HomeFragment extends Fragment implements View.OnClickListener{


    Button addFood, addWater, addSport, startPeriod;
    String Food = "FoodCounter.csv";
    String Water = "WaterCounter.csv";
    String Exercise = "Exercise.csv";

    //TODO pitäs siis tervehtiä käyttäjä-oliota


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

        String totalFood = readSimpleFile(Food);
        String totalWater = readSimpleFile(Water);
        String[] totalInfo = readComplicatedFile(Exercise);

        textFood.setText("    Food counter\n    "+totalFood+" Kg CO2 last week");
        textWater.setText("    Water counter\n    "+totalWater+"ml today");
        textSport.setText("    Exercise\n    "+ totalInfo[0]+": "+totalInfo[2]+"h today");
        textPeriod.setText("    Menstrual tracker\n    29 day left");

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

    //This method read smaller files, like Water counter and food counter.
    //It returns total from last row.
    public String readSimpleFile(String name) {
        BufferedReader br = null;
        try {
            String line;
            String[] lines;
            br = new BufferedReader(new FileReader(getActivity().getFilesDir().getPath() + "/" + name));
            StringBuffer buffer = new StringBuffer();
            while ((line = br.readLine()) != null) {
                line = line+",";
                buffer.append(line);
            }
            String result = buffer.toString();
            lines = result.split(",");

            String wanted = lines[lines.length-1];
            String[] info = wanted.split(";");
            String total = info[1];
            return total;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }
    
    //This method read little bigger files, like exercise file. 
    // It returns last row of file.
    public String[] readComplicatedFile(String name) {
        BufferedReader br = null;
        try {
            String line;
            String[] lines;
            br = new BufferedReader(new FileReader(getActivity().getFilesDir().getPath() + "/" + name));
            StringBuffer buffer = new StringBuffer();
            while ((line = br.readLine()) != null) {
                line = line+",";
                buffer.append(line);
            }
            String result = buffer.toString();
            lines = result.split(",");

            String wanted = lines[lines.length-1];
            String[] info = wanted.split(":");
            return info;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }
}
