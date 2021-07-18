package com.example.aditya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Dashboard extends AppCompatActivity {



    Button btn1, btn2, btn3,btn4,btn5,btn6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        btn1=findViewById(R.id.maths);
        btn2=findViewById(R.id.phy);
        btn3=findViewById(R.id.thermo);
        btn4=findViewById(R.id.net);
        btn5=findViewById(R.id.inst);
        btn6=findViewById(R.id.ana);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this,Maths.class));
                Test.address="maths";
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this,AfterLogin.class));
                Test.address="user";
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this,Thermo.class));
                Test.address="thermo";
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this,Net.class));
                Test.address="net";
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this,Inst.class));
                Test.address="inst";
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this,Ana.class));
                Test.address="ana";
            }
        });
    }
}
