package com.example.myhealth;


//xml file:https://www.youtube.com/watch?v=IJEIQ1f5KB8


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.My_Health.R;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

public class StatisticsFragment extends Fragment {

    String Food = "FoodCounter.csv";
    String Water = "WaterCounter.csv";
    String Exercise = "Exercise.csv";

    String empt="The data file is empty";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.statistics_fragment, container, false);

        Button btnFoodData = rootView.findViewById(R.id.btnFoodData);
        Button btnWaterData = rootView.findViewById(R.id.btnWaterData);
        Button btnExercise = rootView.findViewById(R.id.btnExerciseData);

        TextView tvData = rootView.findViewById(R.id.tvDataDisplay);


        btnFoodData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BufferedReader br = null;
                String line ="";
                StringBuffer stringBuffer = new StringBuffer();
                try {
                    br = new BufferedReader(new FileReader(getActivity().getFilesDir().getPath() +"/"+Food));
                    while ((line = br.readLine()) != null){
                        stringBuffer.append(line+"\n");
                    }
                    tvData.setText(stringBuffer);
                } catch (IOException e) {
                    e.printStackTrace();
                    tvData.setText(empt);
                } finally {
                    try {
                        if (br != null)br.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }

            }
        });



        btnWaterData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BufferedReader br = null;
                String line="";
                StringBuffer stringBuffer = new StringBuffer();
                try {
                    br = new BufferedReader(new FileReader(getActivity().getFilesDir().getPath() +"/"+Water));
                    while ((line = br.readLine()) != null){
                        stringBuffer.append(line+"\n");
                    }
                    tvData.setText(stringBuffer);
                } catch (IOException e) {
                    e.printStackTrace();
                    tvData.setText(empt);
                } finally {
                    try {
                        if (br != null)br.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }

            }
        });



        btnExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BufferedReader br = null;
                String line ="";
                StringBuffer stringBuffer = new StringBuffer();
                try {
                    br = new BufferedReader(new FileReader(getActivity().getFilesDir().getPath() +"/"+Exercise));
                    while ((line = br.readLine()) != null){
                            stringBuffer.append(line+"\n");
                        }
                        tvData.setText(stringBuffer);

                } catch (IOException e) {
                    e.printStackTrace();
                    tvData.setText(empt);
                } finally {
                    try {
                        if (br != null)br.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }

            }
        });

        return rootView;
    }
}
