package com.example.harjoitustyo_ida_viia;

//https://www.youtube.com/watch?v=RiHGwJ_u27k&list=PLlGT4GXi8_8dDK5Y3KCxuKAPpil9V49rN&index=3&t=2s
//https://www.youtube.com/playlist?list=PLlGT4GXi8_8dDK5Y3KCxuKAPpil9V49rN

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class Signin extends AppCompatActivity {

    EditText username, email, password;
    Button sign;
    TextView login;

    String name = ".newProf.csv";

    FirebaseAuth fAuth;
    ProgressBar progressBar;
    FirebaseFirestore fStore;
    String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        username = findViewById(R.id.etUser);
        email = findViewById(R.id.etEmail);
        password = findViewById(R.id.etPassword);
        sign = findViewById(R.id.btnSignin);
        login = findViewById(R.id.tvOldUser);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        progressBar = findViewById(R.id.progressBar);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Signin.this, Login.class));
            }
        });

        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = email.getText().toString().trim();
                String pass = password.getText().toString().trim();
                String name = username.getText().toString().trim();
                String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=]).{12,}";

                if(TextUtils.isEmpty(name)){
                    username.setError("Username is required");
                    return;
                }
                if(TextUtils.isEmpty(mail)){
                    email.setError("Email is required");
                    return;
                }
                if(TextUtils.isEmpty(pass)){
                    password.setError("Password is required");
                    return;
                }
                //Should check the password is good
                if(!pass.matches(pattern)) {
                    //This check if the password is valid or not
                    password.setError("Password must have at least: 12 character, one uppercase, one number and one symbol");
                    System.out.println(pass.matches(pattern));
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);


                //Sign in user in farebase
                fAuth.createUserWithEmailAndPassword(mail, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Signin.this, "User created", Toast.LENGTH_SHORT).show();
                            //add user firestore
                            userID = fAuth.getCurrentUser().getUid();
                            createFile(userID);
                            writeFile(name,userID);
                            Toast.makeText(Signin.this, "this "+userID, Toast.LENGTH_SHORT).show();
                            DocumentReference documentReference = fStore.collection("users").document(userID);
                            Map<String, Object> user= new HashMap<>();
                            user.put("Username", name);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "onSuccess: user Profile is created for"+documentReference.getId());
                                }
                            });
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }else{
                            Toast.makeText(Signin.this, "Error "+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });
    }

    public void createFile(String person){
        try {
            String content = "Name;\n";
            File file = new File(this.getFilesDir().getPath()+"/"+person+name);

            if(!file.exists()){
                file.createNewFile();
                System.out.println(file);
                OutputStreamWriter writer = new OutputStreamWriter(this.openFileOutput(person+name, Context.MODE_PRIVATE));
                writer.write(content);
                writer.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void writeFile(String newname, String person) {
        try (FileWriter fw = new FileWriter(this.getFilesDir().getPath() +"/"+ person+name, true)) {
            BufferedWriter writer = new BufferedWriter(fw);
            writer.append(newname+";\n");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
