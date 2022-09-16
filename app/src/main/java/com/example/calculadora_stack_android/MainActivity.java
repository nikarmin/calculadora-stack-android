package com.example.calculadora_stack_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.Normalizer;

public class MainActivity extends AppCompatActivity
{
    EditText edtVisor;
    TextView tvResultado, tvPosInfi;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtVisor    = (EditText) findViewById(R.id.edtVisor);
        tvPosInfi   = (TextView) findViewById(R.id.tvPosInfi);
        tvResultado = (TextView) findViewById(R.id.tvResultado);
    }

    public void clickBotao (View view)
    {
        Button btn = (Button) view;
        Character c = btn.getText().charAt(0);

        switch (c)
        {
            case '=':
                // calcular a expressao
                String expressao = edtVisor.getText().toString();
                if (!expressao.equals("") && VerificadorDeBalanceamento.estaBalanceada(expressao))
                {
                    try
                    {
                        String expInfixa = FormadorDeExpressaoInfixa.formarExpressaoInfixa(expressao);
                        String result = ConversorPosfixa.converter(expInfixa);
                        Double teste = ConversorPosfixa.valorDaExpressaoPosfixa(result, FormadorDeExpressaoInfixa.getOperandos());
                        Toast.makeText(MainActivity.this, teste.toString(), Toast.LENGTH_LONG).show();
                    }
                    catch (Exception err)
                    {
                        Toast.makeText(MainActivity.this, "Exp invalida", Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    Toast.makeText(MainActivity.this, "A expressão está incorreta!", Toast.LENGTH_LONG).show();
                }
                break;
            case 'C':
                // resetamos a tela
                edtVisor.setText("");
                tvResultado.setText("RESULTADO:");
                tvPosInfi.setText("PÓSFIXA/INFIXA:");
                break;
            default:
                // adicionamos o dígito selecionado no visor
                String exp = edtVisor.getText().toString() + c;
                edtVisor.setText(exp);
                break;
        }
    }
}