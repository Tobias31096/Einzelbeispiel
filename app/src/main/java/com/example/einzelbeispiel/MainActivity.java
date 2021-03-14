package com.example.einzelbeispiel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    Button abschicken;
    Button berechnen;
    EditText matrikelnummer;
    TextView ausgabe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        abschicken = findViewById(R.id.abschicken);
        berechnen = findViewById(R.id.berechnen);
        matrikelnummer = findViewById(R.id.matrikelnummer);
        ausgabe = findViewById(R.id.ausgabe);

        abschicken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TCPClient tcpClient = new TCPClient(matrikelnummer.getText().toString());
                tcpClient.start();
                try {
                    tcpClient.join();
                }catch (Exception e){
                    e.getStackTrace();
                }
                ausgabe.setText(tcpClient.returnModifiedSentence());
            }
        });
        berechnen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!matrikelnummer.getText().toString().equals("")){
                    if(matrikelnummer.getText().toString().length()<7||matrikelnummer.getText().toString().length()>9){
                        ausgabe.setText("Bitte gib eine gültige Matrikelnummer ein!");
                    } else{
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
                            ausgabe.setText("Die alternierende Quersumme deiner Matrikelnummer ist " +x+". "+'\n'+ "Diese Zahl ist gerade!");
                        } else {
                            ausgabe.setText("Die alternierende Quersumme deiner Matrikelnummer ist " +x+". "+'\n'+ "Diese Zahl ist ungerade!");
                        }
                    }
                } else {
                    ausgabe.setText("Bitte gib zuerst deine Matrikelnummer ein!");
                }
            }
        });
    }
}