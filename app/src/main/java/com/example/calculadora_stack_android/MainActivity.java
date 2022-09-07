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

    Byte VerificarValorDePrecedencia(char simbolo) {

         byte precedencia = 0;

         switch (simbolo)
         {
             case '(':
                 precedencia = 0;
                 break;
             case '@': // @ é o - unário
             case '#': // # é o + unário
             case '^':
                 precedencia = 1;
                 break;
             case '*':
             case '/':
                 precedencia = 2;
                 break;
             case '+':
             case '-':
                 precedencia = 3;
                 break;
             case ')':
                 precedencia = 4;
                 break;
         }

         return precedencia;
    }

    Boolean TemPrecendecia(char topo, char simboloLido){
        byte pTopo = VerificarValorDePrecedencia(topo);               // pTopo recebe o valor de precedencia do simbolo do topo da pilha
        byte pSimboloLido = VerificarValorDePrecedencia(simboloLido); // pSimboloLido recebe o valor de precedencia do simbolo lido

        // se o valor de precedencia do simbolo do topo for menor que o do simbolo lido, o simbolo do topo tem maior precedência
        if (pTopo < pSimboloLido)
            return true;
            // se os dois simbolos tem o mesmo valor de precedencia, o simbolo que vem primeiro na expressao tem maior precedencia, ou seja,
            // o simbolo do topo da pilha (pois ele foi lido antes). isso só não acontece com os unários (precedencia 1) e com o operador de
            // potenciação (precedencia = 2)
        else if (pTopo == pSimboloLido && pTopo != 1)
            return true;

        return false;
    }

    Boolean EhOperador(Character c){

        if ("/*-+()^@#".contains(c.toString()))
        {
            Toast.makeText(this, "SIM HAHAHAHA", Toast.LENGTH_SHORT).show();
            return true;
        }

        else
            return false;
    }
}