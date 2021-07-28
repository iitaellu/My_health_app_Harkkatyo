package com.example.harjoitustyo_ida_viia;

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



public class PeriodFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_period, container, false);

        TextView tvDaysLeft = rootView.findViewById(R.id.tvDaysLeft);
        TextView tvDays = rootView.findViewById(R.id.tvDay);

        EditText etPeriodL = rootView.findViewById(R.id.etPeriodL);
        EditText etCycle = rootView.findViewById(R.id.etCycle);

        Button btnSave = rootView.findViewById(R.id.btnSave);
        Button btnStartEnd = rootView.findViewById(R.id.btnStartEnd);

        String text = "Start period";
        btnStartEnd.setText(text);

        btnStartEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Start adding one every day to tvDays until it is clicked again
                //Take a day away from the Next period until it is clicked again
                if(btnStartEnd.getText().toString().length() > 10) {
                    btnStartEnd.setText("End period");
                    tvDays.setText("1");

                }
                else{
                    btnStartEnd.setText(text);
                    tvDays.setText("0");

                    //tvDaysLeft countdown to the last period how many days are left to the next period
                    int dleft=Integer.parseInt(tvDaysLeft.getText().toString());
                    dleft--;
                    tvDaysLeft.setText(String.valueOf(dleft));
                    //Show how many days are left in homescreen.

                }
         }
        });


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvDaysLeft.setText(String.valueOf(etCycle.getText().toString()));
                Toast.makeText(getActivity(), "Information saved", Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }

}

//TODO Käyttäjän on jossain kohtaa annettava kuukautistensa kierto.
// Täällä kone laitetaan laskemaan joka päivä yksi päivä lisää kunnes käyttäjä painaa nappia, oka nollaa laskurin
