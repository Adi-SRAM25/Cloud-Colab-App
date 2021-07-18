package com.example.aditya;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.SignInMethodQueryResult;

public class Loginactivity extends AppCompatActivity implements View.OnClickListener {

    FirebaseAuth mAuth;
    EditText editTextEmail, editTextPassword;
    ProgressBar progressBar;
    Button btn;
    TextView register;
    int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginactivity);

        mAuth = FirebaseAuth.getInstance();

        editTextEmail = (EditText) findViewById(R.id.editText);
        editTextPassword = (EditText) findViewById(R.id.editText2);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        btn =(Button)findViewById(R.id.button);
        register=(TextView) findViewById(R.id.textView);

        btn.setOnClickListener(this);
        register.setOnClickListener(this);

        progressBar.setVisibility(View.GONE);




    }

    private void loginuser(){

        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();


        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {    i=1;
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(),"Login Successful",Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(new Intent(Loginactivity.this,Dashboard.class));
                }

                else {
                    Toast.makeText(Loginactivity.this,"Not a valid User!", Toast.LENGTH_SHORT).show();

                }
            }


        });






    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.textView:
                startActivity(new Intent(Loginactivity.this,MainActivity.class));
                break;

            case R.id.button:
                loginuser();
                break;
        }
    }
}
