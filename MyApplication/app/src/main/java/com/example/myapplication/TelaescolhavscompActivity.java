package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TelaescolhavscompActivity extends AppCompatActivity {

    Button xis, bolinha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telaescolhavscomp);

        xis = findViewById(R.id.xis);
        bolinha = findViewById(R.id.bolinha);

        xis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TelaescolhavscompActivity.this, tela1vscomp.class);
                intent.putExtra("xis", xis.getText().toString());
                startActivity(intent);
                finish();
            }
        });

        bolinha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TelaescolhavscompActivity.this, tela1vscomp.class);
                intent.putExtra("bolinha", bolinha.getText().toString());
                startActivity(intent);
                finish();
            }
        });
    }
}