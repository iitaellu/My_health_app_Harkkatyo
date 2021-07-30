package com.example.harjoitustyo_ida_viia;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class HomeFragment extends Fragment implements View.OnClickListener{

    String person;

    String Food = ".FoodCounter.csv";
    String Water = ".WaterCounter.csv";
    String Exercise = ".Exercise.csv";
    String date;
    TextView textFood, textWater, textSport, textPeriod;
    
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;

    //TODO pitäs siis tervehtiä käyttäjä-oliota


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        TextView dateTextHead = (TextView) rootView.findViewById(R.id.dateTextHead);
        
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        person = fAuth.getCurrentUser().getUid();

        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.DATE_FIELD).format(calendar.getTime());
        SimpleDateFormat format = new SimpleDateFormat("d.M.yyyy");
        date = format.format(Date.parse(currentDate));
        dateTextHead.setText(date);

        textFood = (TextView) rootView.findViewById(R.id.textFood);
        textWater = (TextView) rootView.findViewById(R.id.textWater);
        textSport = (TextView) rootView.findViewById(R.id.textSport);
        textPeriod = (TextView) rootView.findViewById(R.id.textPeriod);

        setFoodText();
        setWaterText();
        setExerciseText();
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
    public String[] readFile(String name, String person) {
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
            String[] info = wanted.split(";");
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
        String[] info = null;
        return info;
    }

    //This method set to food counter info-box and check if there is old result to show
    public void setFoodText(){
        String[] foodInfo = readFile(Food, person);

        if (foodInfo != null) {
            textFood.setText("    Food counter\n    " + foodInfo[1] + " Kg CO2 last time");
        }
        else{
            textFood.setText("    Food counter\n    0 Kg CO2 last time");
        }
    }

    //This method set to water counter info-box and check if there is result from that day
    public void setWaterText(){
        String[] waterInfo = readFile(Water, person);

        if (waterInfo != null) {
            String oldDate = waterInfo[0];
            if (oldDate.equals(date)){
                textWater.setText("    Water counter\n    " + waterInfo[1] + "ml today");
            }
            else{
                textWater.setText("    Water counter\n    0 ml today");;
            }
        }
        else {
            textWater.setText("    Water counter\n    0 ml today");
        }
    }

    //This method set to exercise info-box and check if there is result from that day
    public void setExerciseText(){
        String[] exerciseInfo = readFile(Exercise, person);

        if (exerciseInfo != null) {
            String oldDate = (exerciseInfo[0]).trim();
            System.out.println(oldDate);
            if (oldDate.equals(date)){
                textSport.setText("    Exercise\n    " + exerciseInfo[1] + ": " + exerciseInfo[2] + "h today");
            }
            else {
                textSport.setText("    Exercise\n    Type: 0.00h today");
            }
        }
        else {
            textSport.setText("    Exercise\n    Type: 0.00h today");
        }
    }
}
