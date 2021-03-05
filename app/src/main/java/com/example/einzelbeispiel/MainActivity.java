package com.example.einzelbeispiel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button button;
    EditText matrikelnummer;
    TextView ausgabe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        matrikelnummer = findViewById(R.id.matrikelnummer);
        ausgabe = findViewById(R.id.ausgabe);

        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int x=0;
        String [] s = matrikelnummer.getText().toString().split("(?!^)");
        for(int i=0;i<s.length;i++){
            if(i%2==0){
                x=x+Integer.parseInt(s[i]);
            }else{
                x=x-Integer.parseInt(s[i]);
            }
        }
        if(x%2==0){
            ausgabe.setText(x+": ist gerade!");
        } else {
            ausgabe.setText(x+": ist ungerade!");
        }
    }
}