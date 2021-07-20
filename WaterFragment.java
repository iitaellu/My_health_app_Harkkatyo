//java -> eka file

package com.example.harjoitustyo_ida_viia;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class WaterFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_water, container, false);
    }

    //TODO käyttäjän antamien tietojen perusteella arvioidaan montako lasillista (250ml), käyttäjän on juotava päivässä.
    // Käyttäjä painaa nappia niin monesti, kuin on juonut vaaditun määrän
    // joka päivä nollautuu (if time == 00.00) tai jotain. en tie
}
