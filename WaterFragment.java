//java -> eka file

package com.example.harjoitustyo_ida_viia;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class WaterFragment extends Fragment implements View.OnClickListener {

    Button smallGlass, mediumGlass, bigGlass, largeGlass;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_water, container, false);

        TextView thisMuchWater = (TextView) rootView.findViewById(R.id.thisMuchWater);
        TextView infoWater = (TextView) rootView.findViewById(R.id.infoWater);

        thisMuchWater.setText("You should drink ... ml of water per day.");
        infoWater.setText("You are drunk ... ml of water today");

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

        switch(v.getId()){
            case R.id.smallGlass:
                //add 250ml
                Toast.makeText(getActivity(),"250ml drunk",Toast.LENGTH_LONG).show();
                break;
            case R.id.mediumGlass:
                //add 500ml
                Toast.makeText(getActivity(),"500ml drunk",Toast.LENGTH_LONG).show();
                break;
            case R.id.bigGlass:
                //add 600ml
                Toast.makeText(getActivity(),"600ml drunk",Toast.LENGTH_LONG).show();
                break;
            case R.id.largeGlass:
                //add 1000ml
                Toast.makeText(getActivity(),"1l drunk",Toast.LENGTH_LONG).show();
                break;
        }

    }

    //TODO käyttäjän antamien tietojen perusteella arvioidaan montako lasillista (250ml), käyttäjän on juotava päivässä.
    // Käyttäjä painaa nappia niin monesti, kuin on juonut vaaditun määrän
    // joka päivä nollautuu (if time == 00.00) tai jotain. en tie
    // faktaa https://www.mtvuutiset.fi/makuja/artikkeli/ravitsemisterapeutti-nain-paljon-vetta-pitaisi-oikeasti-juoda-paivassa/6188936#gs.6y3sm0
}


