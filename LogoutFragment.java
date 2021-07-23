//java -> eka file

package com.example.harjoitustyo_ida_viia;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.google.firebase.auth.FirebaseAuth;

public class LogoutFragment extends Fragment {
    
    TextView tvLogOut;
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //check the LogoutFragment xml is fragment_login or not
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        
        //check the name of the logout tv is it tvLogout or not
        tvLogOut = view.findViewById(R.id.tvLogout);
        return view;
    }
    
    public void tvLogOut(View v){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), Login.class));
        finish();
    }
}
