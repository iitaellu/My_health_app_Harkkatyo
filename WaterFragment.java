//java -> eka file

package com.example.harjoitustyo_ida_viia;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class WaterFragment extends Fragment implements View.OnClickListener {

    private int water = 0;
    TextView infoWater;
    String name = "WaterCounter.csv";
    /*String[] permissions = new String[]{
            Manifest.permission.INTERNET,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.VIBRATE,
            Manifest.permission.RECORD_AUDIO,
    };*/

    Button smallGlass, mediumGlass, bigGlass, largeGlass;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_water, container, false);
        //checkPermissions();
        TextView thisMuchWater = (TextView) rootView.findViewById(R.id.thisMuchWater);
        infoWater = (TextView) rootView.findViewById(R.id.infoWater);
        makeFile();

        thisMuchWater.setText("You should drink 1-1,5l of water per day.");
        infoWater.setText("You are drank 0 ml of water today");

        Button smallGlass = (Button) rootView.findViewById(R.id.smallGlass);
        Button mediumGlass = (Button) rootView.findViewById(R.id.mediumGlass);
        Button bigGlass = (Button) rootView.findViewById(R.id.bigGlass);
        Button largeGlass = (Button) rootView.findViewById(R.id.largeGlass);
        Button reset = (Button) rootView.findViewById(R.id.reset);

        smallGlass.setOnClickListener(this);
        mediumGlass.setOnClickListener(this);
        bigGlass.setOnClickListener(this);
        largeGlass.setOnClickListener(this);
        reset.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.smallGlass:
                water = water + 250;
                infoWater.setText("You are drank " + Integer.toString(water) + "ml of water today");
                writeFile(Integer.toString(water));
                readFile();
                Toast.makeText(getActivity(), "250ml drank", Toast.LENGTH_LONG).show();
                break;
            case R.id.mediumGlass:
                water = water + 500;
                infoWater.setText("You are drank " + Integer.toString(water) + "ml of water today");
                //writeFile(Integer.toString(water));
                //System.out.println(water);
                Toast.makeText(getActivity(), "500ml drank", Toast.LENGTH_LONG).show();
                break;
            case R.id.bigGlass:
                water = water + 600;
                infoWater.setText("You are drank " + Integer.toString(water) + "ml of water today");
                //writeFile(Integer.toString(water));
                Toast.makeText(getActivity(), "600ml drank", Toast.LENGTH_LONG).show();
                break;
            case R.id.largeGlass:
                water = water + 1000;
                infoWater.setText("You are drank " + Integer.toString(water) + "ml of water today");
                //writeFile(Integer.toString(water));
                Toast.makeText(getActivity(), "1l drank", Toast.LENGTH_LONG).show();
                break;
            case R.id.reset:
                water = 0;
                infoWater.setText("You are drank " + Integer.toString(water) + "ml of water today");
                //writeFile(Integer.toString(water));
                Toast.makeText(getActivity(), "New day!", Toast.LENGTH_LONG).show();
                break;
        }
    }

   public void makeFile() {
        try {
            String content = "drank ml of water;\n";
            File file = new File(getActivity().getFilesDir().getPath() +"/"+ name);

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

    public void writeFile(String ml) {
        try (FileWriter fw = new FileWriter(getActivity().getFilesDir().getPath() +"/"+ name, true)) {
            BufferedWriter writer = new BufferedWriter(fw);
            writer.append(ml+";\n");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFile(){
        BufferedReader br = null;
        try {
            String line;
            br = new BufferedReader(new FileReader(getActivity().getFilesDir().getPath() +"/"+name));
            while ((line = br.readLine()) != null){
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }


        //TODO Tietojen tallennus (päivämäärä + uusin vesimäärä)
        // faktaa https://www.mtvuutiset.fi/makuja/artikkeli/ravitsemisterapeutti-nain-paljon-vetta-pitaisi-oikeasti-juoda-paivassa/6188936#gs.6y3sm0
}
