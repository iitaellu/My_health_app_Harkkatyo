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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class SportFragment extends Fragment {

    String person;
    String name = ".Exercise.csv";

    EditText etWDate, etWTime, etWDistance, etRDate, etRTime, etRDistance;
    TextView tvEHistory;
    Button btnRSave, btnWSave;

    FirebaseAuth fAuth;
    FirebaseFirestore fStore;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sport, container, false);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        person = fAuth.getCurrentUser().getUid();

        createFile(person);

        etWDate = rootView.findViewById(R.id.etWDate);
        etWTime = rootView.findViewById(R.id.etWTime);
        etWDistance = rootView.findViewById(R.id.etWDistance);

        etRDate = rootView.findViewById(R.id.etRDate);
        etRTime = rootView.findViewById(R.id.etRTime);
        etRDistance = rootView.findViewById(R.id.etRDistance);

        tvEHistory = rootView.findViewById(R.id.tvEHistory);

        btnRSave = rootView.findViewById(R.id.btnRSave);
        btnWSave = rootView.findViewById(R.id.btnWSave);


        tvEHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "View previous exercises in csv file: "+getContext().getFilesDir()+ "/" +name, Toast.LENGTH_LONG).show();
            }
        });

        btnWSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Save walking information to the csv file

                writeFile("Walk",etWDate.getText().toString(), etWTime.getText().toString(), etWDistance.getText().toString(), person);

                //calculating time

                etWDate.setText("");
                etWTime.setText("");
                etWDistance.setText("");
                Toast.makeText(getActivity(), "Walk saved", Toast.LENGTH_SHORT).show();
            }
        });


        btnRSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Save running information to the csv file
                writeFile("Run",etRDate.getText().toString(), etRTime.getText().toString(), etRDistance.getText().toString(), person);


                //calculating time

                etRDate.setText("");
                etRTime.setText("");
                etRDistance.setText("");
                Toast.makeText(getActivity(), "Run saved", Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }


    public void createFile(String person){
        try {
            String content = "Date;Exercise;Time(h.min);Distance(km)\n";
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


    public void writeFile(String exer, String date, String time, String distance, String person) {
        try (FileWriter fw = new FileWriter(getActivity().getFilesDir().getPath() +"/"+ person+name, true)) {
            BufferedWriter writer = new BufferedWriter(fw);
            writer.append(date+";"+exer+";"+time+";"+distance+"\n");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
