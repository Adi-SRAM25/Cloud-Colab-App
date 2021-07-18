package com.example.aditya;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    FirebaseAuth mAuth;
    EditText editTextEmail, editTextPassword;
    ProgressBar progressBar;
    Button btn;
    TextView register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        editTextEmail = (EditText) findViewById(R.id.editText);
        editTextPassword = (EditText) findViewById(R.id.editText2);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btn = (Button) findViewById(R.id.button);
        register=(TextView) findViewById(R.id.textView);


        btn.setOnClickListener(this);
        register.setOnClickListener(this);

        progressBar.setVisibility(View.GONE);

    }



    private void registeruser() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (email.isEmpty()) {
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Please enter a valid email");
            editTextEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            editTextPassword.setError("Minimum lenght of password should be 6");
            editTextPassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                progressBar.setVisibility(View.GONE);
                if(task.isSuccessful()) {

                    Toast.makeText(getApplicationContext(),"User Registered Successfully",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this,Dashboard.class));

                }

                else
                {
                    Toast.makeText(getApplicationContext(),"Some error occurred or Already Registered",Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.textView:
                startActivity(new Intent(this,Loginactivity.class));
                break;

            case R.id.button:
                registeruser();
                break;
        }

    }
}
