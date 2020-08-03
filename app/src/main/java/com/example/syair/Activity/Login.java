package com.example.syair.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.syair.R;
import com.example.syair.Sharepreferences;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    private EditText inputemail, inputpassword;
    private TextView klikdaftar;
    private Button masuk;
    private CheckBox checkBoxs;
    Sharepreferences sessions;
    ProgressDialog progressDialog;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        inputemail = findViewById(R.id.email);
        inputpassword = findViewById(R.id.password);
        masuk = findViewById(R.id. buttonlogin);
        klikdaftar = findViewById(R.id.daftar);
        checkBoxs = findViewById(R.id.checkBox);
        sessions = new Sharepreferences((Login.this.getApplicationContext()));
        String gemail = sessions.getEmail();
        String gpassword = sessions.getPassword();
        inputemail.setText(gemail);
        inputpassword.setText(gpassword);

        if (inputemail.getText().toString().length() > 0 && inputpassword.getText().toString().length() > 0){
            Intent i = new Intent(Login.this, Dashboard.class);
            startActivity(i);
            finish();
        }
        else if ((inputemail.getText().toString().length()==0) && (inputpassword.getText().toString().length()==0)){
            masuk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String statusemail = inputemail.getText().toString();
                    final String statuspassword = inputpassword.getText().toString();
                    if (statusemail.equals("")){
                        inputemail.setError("Masukkan alamat email");
                        inputemail.requestFocus();
                        return;
                    }
                    if (statuspassword.equals("")){
                        inputpassword.setError("Masukkan password");
                        inputpassword.requestFocus();
                        return;
                    }
                    else {
                        mAuth.signInWithEmailAndPassword(statusemail, statuspassword)
                                .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()){
                                            FirebaseUser user = mAuth.getCurrentUser();
                                            Intent i = new Intent(Login.this, Dashboard.class);
                                            startActivity(i);
                                            finish();
                                            if (checkBoxs.isChecked()){
                                                sessions.setEmail(statusemail);
                                                sessions.setPassword(statuspassword);
                                            }
                                            progressDialog.dismiss();
                                        }
                                        else {
                                            Toast.makeText(Login.this, "Login Gagal", Toast.LENGTH_SHORT).show();
                                            progressDialog.dismiss();
                                        }
                                    }
                                });
                    }
                    progressDialog = new ProgressDialog(Login.this);
                    progressDialog.setMessage("Memuat Data");
                    progressDialog.setIndeterminate(false);
                    progressDialog.setCancelable(true);
                    progressDialog.show();
                }
            });
        }
        klikdaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, Register.class);
                startActivity(i);
                finish();
            }
        });

    }
}