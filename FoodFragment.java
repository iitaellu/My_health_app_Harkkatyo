//java -> eka file

package com.example.harjoitustyo_ida_viia;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FoodFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_food, container, false);

        //TODO joka API:n jutulle oma kohta (ei kuitenkaan pakko käyttää kaikkia mut kiakille niille mitä halutaan käyttää)
        // näiden tietojen avulla rakennetaan linkki. Sen lisäksi käyttäjän on kerrottava oleelliset tiedot kalorien laskemista varten
        // faktaa https://www.nutrilett.fi/paivittaiset-kalorit-ja-kaloreiden-kulutus/
    }
}
