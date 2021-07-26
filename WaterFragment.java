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

    private int water = 0;
    TextView infoWater;

    Button smallGlass, mediumGlass, bigGlass, largeGlass;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_water, container, false);

        TextView thisMuchWater = (TextView) rootView.findViewById(R.id.thisMuchWater);
        infoWater = (TextView) rootView.findViewById(R.id.infoWater);

        thisMuchWater.setText("You should drink 1-1,5l of water per day.");


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

        switch(v.getId()) {
            case R.id.smallGlass:
                water = water + 250;
                System.out.println(water);
                infoWater.setText("You are drank " + Integer.toString(water) + "ml of water today");
                Toast.makeText(getActivity(), "250ml drunk", Toast.LENGTH_LONG).show();
                break;
            case R.id.mediumGlass:
                water = water + 500;
                System.out.println(water);
                infoWater.setText("You are drank " + Integer.toString(water) + "ml of water today");
                Toast.makeText(getActivity(), "500ml drunk", Toast.LENGTH_LONG).show();
                break;
            case R.id.bigGlass:
                water = water + 600;
                System.out.println(water);
                infoWater.setText("You are drank " + Integer.toString(water) + "ml of water today");
                Toast.makeText(getActivity(), "600ml drunk", Toast.LENGTH_LONG).show();
                break;
            case R.id.largeGlass:
                water = water + 1000;
                System.out.println(water);
                infoWater.setText("You are drank " + Integer.toString(water) + "ml of water today");
                Toast.makeText(getActivity(), "1l drunk", Toast.LENGTH_LONG).show();
                break;
        }

    }



    //TODO Tietojen tallennus (päivämäärä + uusin vesimäärä) ja reset nappi, jotta käyttäjä voi nollata tiedot seuraavaa päivää varten
    // faktaa https://www.mtvuutiset.fi/makuja/artikkeli/ravitsemisterapeutti-nain-paljon-vetta-pitaisi-oikeasti-juoda-paivassa/6188936#gs.6y3sm0
}
