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
import com.example.My_Health.R;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;



public class SportFragment extends Fragment {


    private static final String FILE_NAME = "data.txt";

    String name = "Exercise.csv";

    EditText etWDate, etWTime, etWDistance, etRDate, etRTime, etRDistance;
    TextView tvEHistory;
    Button btnRSave, btnWSave;



    //should calculate the day's exercised time and show it in home screen

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sport, container, false);

        createFile();

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

                writeFile("Walk",etWDate.getText().toString(), etWTime.getText().toString(), etWDistance.getText().toString());

                //calculating time

                etWDate.setText("");
                etWTime.setText("");
                etWDistance.setText("");
                Toast.makeText(getActivity(), "Walk saved to "+getContext().getFilesDir()+ "/" +name, Toast.LENGTH_LONG).show();


            }
        });


        btnRSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Save running information to the csv file
                writeFile("Run",etRDate.getText().toString(), etRTime.getText().toString(), etRDistance.getText().toString());


                //calculating time

                etRDate.setText("");
                etRTime.setText("");
                etRDistance.setText("");

                Toast.makeText(getActivity(), "Run saved to "+getContext().getFilesDir()+ "/" +name, Toast.LENGTH_LONG).show();

            }
        });
        
        return rootView;

    }


    public void createFile(){
        try {
        String content = "Exercise:Date:Time(h.min):Distance(km)\n";
        File file = new File(getActivity().getFilesDir().getPath()+"/"+name);

        if(!file.exists()){
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


    public void writeFile(String exer, String date, String time, String distance) {
        try (FileWriter fw = new FileWriter(getActivity().getFilesDir().getPath() +"/"+ name, true)) {
            BufferedWriter writer = new BufferedWriter(fw);
            writer.append(exer+":"+date+":"+time+":"+distance+"\n");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

   
}
