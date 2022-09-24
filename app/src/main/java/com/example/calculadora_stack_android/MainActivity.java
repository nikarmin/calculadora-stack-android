package com.example.calculadora_stack_android;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.Normalizer;

public class MainActivity extends AppCompatActivity
{
    EditText edtVisor;
    TextView tvResultado, tvPosInfi;
    FloatingActionButton btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtVisor    = (EditText) findViewById(R.id.edtVisor);
        tvPosInfi   = (TextView) findViewById(R.id.tvPosInfi);
        tvResultado = (TextView) findViewById(R.id.tvResultado);
        btnDelete   = (FloatingActionButton) findViewById(R.id.btnDelete);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*Toast toast = new Toast(getApplicationContext());
                ImageView view = new ImageView(getApplicationContext());*/

                if (edtVisor.length() == 0) {
                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.toast,
                            (ViewGroup) findViewById(R.id.toast_layout_root));

                    ImageView image = (ImageView) layout.findViewById(R.id.image);
                    image.setImageResource(R.drawable.emoji);
                    TextView text = (TextView) layout.findViewById(R.id.text);
                    text.setText("Digite alguma coisa!");

                    Toast toast = new Toast(getApplicationContext());
                    toast.setGravity(Gravity.BOTTOM, 0, 0);
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.setView(layout);
                    toast.show();
                }
                else{
                    String text = edtVisor.getText().toString();
                    edtVisor.setText(text.substring(0, text.length()-1));
                }

            }
        });
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
                        String expPosfixa = ConversorPosfixa.converter(expInfixa);
                        Double resultado = ConversorPosfixa.valorDaExpressaoPosfixa(expPosfixa, FormadorDeExpressaoInfixa.getOperandos());


                        expInfixa = expInfixa.replace('@', '-').replace('#', '+');
                        expPosfixa = expPosfixa.replace('@', '-').replace('#', '+');

                        edtVisor.setText(resultado.toString());
                        String posfxInfx = "Pósfixa / Infixa: " + expInfixa + " | " + expPosfixa;

                        tvPosInfi.setText(posfxInfx);
                        tvResultado.setText("Resultado: " + resultado);

                        //Toast.makeText(MainActivity.this, resultado.toString(), Toast.LENGTH_LONG).show();
                    }
                    catch (Exception err)
                    {
                        Toast.makeText(MainActivity.this, "A expressão está incorreta!", Toast.LENGTH_LONG).show();
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
                tvResultado.setText("Resultado:");
                tvPosInfi.setText("Pósfixa / Infixa:");
                break;
            default:
                // adicionamos o dígito selecionado no visor
                String exp = edtVisor.getText().toString() + c;
                edtVisor.setText(exp);
                break;
        }
    }
}