package com.example.harjoitustyo_ida_viia;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class WaterFragment extends Fragment implements View.OnClickListener {
    int water = 0;
    TextView infoWater;
    String name = "WaterCounter.csv";

    String date;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_water, container, false);

        TextView thisMuchWater = (TextView) rootView.findViewById(R.id.thisMuchWater);
        infoWater = (TextView) rootView.findViewById(R.id.infoWater);

        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.DATE_FIELD).format(calendar.getTime());
        SimpleDateFormat format = new SimpleDateFormat("d.M.yyyy");
        date = format.format(Date.parse(currentDate));
        set();

        makeFile();

        thisMuchWater.setText("You should drink 1-1,5l of water per day.");
        //infoWater.setText("You are drank 0 ml of water today");

        Button smallGlass = (Button) rootView.findViewById(R.id.smallGlass);
        Button mediumGlass = (Button) rootView.findViewById(R.id.mediumGlass);
        Button bigGlass = (Button) rootView.findViewById(R.id.bigGlass);
        Button largeGlass = (Button) rootView.findViewById(R.id.largeGlass);

        smallGlass.setOnClickListener(this);
        mediumGlass.setOnClickListener(this);
        bigGlass.setOnClickListener(this);
        largeGlass.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.smallGlass:
                water = water + 250;
                infoWater.setText("You are drank " + Integer.toString(water) + "ml of water today");
                writeFile(Integer.toString(water));
                Toast.makeText(getActivity(), "250ml drank", Toast.LENGTH_LONG).show();
                break;
            case R.id.mediumGlass:
                water = water + 500;
                infoWater.setText("You are drank " + Integer.toString(water) + "ml of water today");
                writeFile(Integer.toString(water));
                Toast.makeText(getActivity(), "500ml drank", Toast.LENGTH_LONG).show();
                break;
            case R.id.bigGlass:
                water = water + 600;
                infoWater.setText("You are drank " + Integer.toString(water) + "ml of water today");
                writeFile(Integer.toString(water));
                Toast.makeText(getActivity(), "600ml drank", Toast.LENGTH_LONG).show();
                break;
            case R.id.largeGlass:
                water = water + 1000;
                infoWater.setText("You are drank " + Integer.toString(water) + "ml of water today");
                writeFile(Integer.toString(water));
                Toast.makeText(getActivity(), "1l drank", Toast.LENGTH_LONG).show();
                break;
        }
    }

    //This method make file, if there is not one yet
    public void makeFile() {
        try {
            String content = "Date;Drank ml of water\n";
            File file = new File(getActivity().getFilesDir().getPath() + "/" + name);

            if (!file.exists()) {
                file.createNewFile();
                System.out.println(file);
                OutputStreamWriter writer = new OutputStreamWriter(getActivity().openFileOutput(name, Context.MODE_PRIVATE));
                writer.write(content);
                writer.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //This method write data in file.
    public void writeFile(String ml) {
        try (FileWriter fw = new FileWriter(getActivity().getFilesDir().getPath() + "/" + name, true)) {
            BufferedWriter writer = new BufferedWriter(fw);
            writer.append(date+ ";"+ ml + "\n");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //With this method program gets last information from file,
    // so program can compare last date to current date tell user how much water user is drank today

    public String[] readFile() {
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
            String[] date_ml = wanted.split(";");
            return date_ml;
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

    //This method check if date is changed and set right amount of drinking water of that day.
    // if day is changed, water amount is 0ml if not program get right amount of the file, with rideFile.
    public void set() {
        String[] dates = readFile();

        if(dates != null) {
            String current = dates[0].trim();
            if (current.equals(date)) {
                water = Integer.parseInt(dates[1]);
                infoWater.setText("You are drank " + Integer.toString(water) + "ml of water today");
            } else {
                water = 0;
                infoWater.setText("You are drank " + Integer.toString(water) + "ml of water today");
            }
        }
        else {
            infoWater.setText("You are drank 0ml of water today");
        }
    }
}
