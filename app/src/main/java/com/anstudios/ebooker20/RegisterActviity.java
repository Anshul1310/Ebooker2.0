package com.anstudios.ebooker20;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActviity extends AppCompatActivity {

    private EditText name, email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_actviity);
        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.purpleTheme));
        name=findViewById(R.id.registerFullName);
        email=findViewById(R.id.registerEmailAddress);
        password=findViewById(R.id.registerPassword);
    }

    private void authenticateUser(){
        if(!name.getText().toString().equals("") || !email.getText().toString().endsWith("@gmail.com") || !password.getText().toString().equals("")){
            Toast.makeText(this, "Invalid Credentials.", Toast.LENGTH_SHORT).show();
        }else{
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                    .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            Toast.makeText(RegisterActviity.this, "Registered Sucessfully", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(RegisterActviity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void registerBtn(View view) {
        authenticateUser();
    }

    public void openLoginScreen(View view) {
        startActivity(new Intent(RegisterActviity.this,LoginActivity.class));
    }
}