package com.example.syair.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.syair.Adapter.RequestNilai;
import com.example.syair.Adapter.RequestRegister;
import com.example.syair.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
    private EditText email, username, asal, password;
    private Button Daftar;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.emailregister);
        username = findViewById(R.id.username);
        asal = findViewById(R.id.asalsekolah);
        password = findViewById(R.id.katasandiregister);
        Daftar = findViewById(R.id.buttondaftar);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        Daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailregister = email.getText().toString();
                final String namapengguna = username.getText().toString();
                String asalsekolah = asal.getText().toString();
                String katasandi = password.getText().toString();

                if (emailregister.equals("")){
                    email.setError("Masukkan alamat email");
                    email.requestFocus();
                    return;
                }
                if (namapengguna.equals("")){
                    username.setError("Masukkan nama pengguna");
                    username.requestFocus();
                    return;
                }
                if (asalsekolah.equals("")){
                    asal.setError("Masukkan asal sekolah");
                    asal.requestFocus();
                    return;
                }
                if (katasandi.equals("")){
                    password.setError("Masukkan kata sandi");
                    password.requestFocus();
                    return;
                }
                else{
                    mAuth.createUserWithEmailAndPassword(emailregister, katasandi)
                            .addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        FirebaseUser user = mAuth.getCurrentUser();

                                        if (user != null) {
                                            UserProfileChangeRequest profile = new UserProfileChangeRequest.Builder()
                                                    .setDisplayName(namapengguna)
                                                    .build();

                                            user.updateProfile(profile)
                                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            if (task.isSuccessful()) {
                                                                progressDialog.dismiss();
                                                                Toast.makeText(Register.this, "Register Berhasil", Toast.LENGTH_SHORT).show();
                                                                Intent i = new Intent(Register.this, Login.class);
                                                                startActivity(i);
                                                                finish();
                                                            }
                                                        }
                                                    });
                                        }
                                    } else {
                                        if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                            Toast.makeText(getApplicationContext(), "Akun sudah terdaftar", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(Register.this, "Register Gagal", Toast.LENGTH_SHORT).show();
                                        }

                                    }
                                }
                            });

                    submitUser(new RequestRegister(emailregister, namapengguna, asalsekolah, katasandi));

                    progressDialog = new ProgressDialog(Register.this);
                    progressDialog.setMessage("Sedang Menyimpan data");
                    progressDialog.setIndeterminate(false);
                    progressDialog.setCancelable(true);
                    progressDialog.show();
                }

            }
        });
    }
    private void submitUser(RequestRegister requestRegister) {
        String nama = username.getText().toString();
        mDatabase.child("Data User").child("Daftar Akun")
                .child(nama)
                .setValue(requestRegister)
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        email.setText("");
                        username.setText("");
                        asal.setText("");
                        password.setText("");
                    }
                });
    }
}