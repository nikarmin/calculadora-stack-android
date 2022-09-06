package br.unicamp.calculadora_stack;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnDivisao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDivisao = findViewById(R.id.btnDivisao);



    }

    public void clickBotao (View view)
    {
        Button btn = (Button) view;
        String caractere = btn.getText().toString();
        Toast.makeText(MainActivity.this, caractere, Toast.LENGTH_LONG).show();
    }
}