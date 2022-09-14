package com.example.calculadora_stack_android;

import java.util.ArrayList;
import java.util.List;

public class FormadorDeExpressaoInfixa
{
    private static String formarExpressaoInfixa(String expressao) throws Exception
    {
        String expInfix = "";
        List<Double> operandos = new ArrayList<Double>();

        for (int i = 0; i < expressao.length(); i++)
        {
            char charAtual = expressao.charAt(i);
            if (EhOperador(charAtual))
            {
                boolean ehUnario = false;
                if (charAtual != '(' && charAtual != ')')
                {
                    if (i == 0)
                        ehUnario = true;
                    else if (expressao.charAt(i - 1) != ')' && !Character.isDigit(expressao.charAt(i - 1)))
                        ehUnario = true;
                }
                else if (charAtual == '(')
                {
                    if (i != 0 && Character.isDigit(expressao.charAt(i - 1)))
                        expInfix += '*';
                }

                if (ehUnario)
                {
                    if (charAtual == '-')
                        expInfix += '@';
                    else if (charAtual == '+')
                        expInfix += '#';
                    else
                        // tem que ver como analisar isso no verificar validez da expressao
                        throw new Exception("Expressao inválida!");
                }
                else
                    expInfix += charAtual;
            }
            else
            {
                String operando = "";

                int c = i;
                while (c != expressao.length() && !EhOperador(expressao.charAt(c)))
                {
                    operando += expressao.charAt(c);

                    c++;
                }

                if (operando.length() > 1)
                    i = --c;

                double numero = Double.parseDouble(operando);

                if (!operandos.contains(numero))
                    operandos.add(numero);

                expInfix += (char) (65 + operandos.indexOf(numero));
            }
        }

        return expInfix;
    }
}
