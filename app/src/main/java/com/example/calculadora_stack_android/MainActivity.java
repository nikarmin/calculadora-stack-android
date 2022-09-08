package com.example.calculadora_stack_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.BoringLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnDivisao;
    EditText edtVisor;
    String caractere = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDivisao = findViewById(R.id.btnDivisao);
        edtVisor = findViewById(R.id.edtVisor);

    }

    public void clickBotao (View view)
    {
        Button btn = (Button) view;
        Character c = btn.getText().charAt(0);

        caractere += c;
        edtVisor.setText(caractere);

    }

    public void Limpar() {
        edtVisor.setText("");
    }
}