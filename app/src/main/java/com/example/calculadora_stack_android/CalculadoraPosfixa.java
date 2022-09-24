package com.example.calculadora_stack_android;

import java.util.List;

public class CalculadoraPosfixa
{
    public static double valorDaExpressaoPosfixa(String cadeiaPosfixa, List<Double> numeros) throws Exception
    {
        PilhaLista<Double> pilha = new PilhaLista<Double>();

        for (int i = 0; i < cadeiaPosfixa.length(); i++)
        {
            char simbol = cadeiaPosfixa.charAt(i);

            if (!ehOperador(simbol))
                pilha.empilhar(numeros.get(simbol - 'A'));
            else
            {
                try {
                    if (simbol == '@')
                        pilha.empilhar(-pilha.desempilhar());
                    else if (simbol == '#')
                        continue;
                    else
                    {
                        double operando2 = pilha.desempilhar();
                        double operando1 = pilha.desempilhar();
                        double valor = valorDaSubExpressao(operando1, simbol, operando2);
                        pilha.empilhar(valor);
                    }
                }
                catch (Exception err){}
            }
        }

        return pilha.desempilhar();
    }

    private static double valorDaSubExpressao(double op1, char simbol, double op2){
        double resultado = 0;

        switch (simbol)
        {
            case '+':
                resultado = op1 + op2;
                break;

            case '-':
                resultado = op1 - op2;
                break;

            case '*':
                resultado = op1 * op2;
                break;

            case '/':
                resultado = op1 / op2;
                break;

            case '^':

                if ((op1 < 0 && op2 < 0) && op2 % 2 == 0)
                    resultado = Math.pow(op1, op2);
                else if ((op1 < 0 && op2 > 0) && op2 % 2 == 0)
                    resultado = Math.pow(op1, op2);
                else if (op1 > 0)
                    resultado = Math.pow(op1, op2);
                else
                    resultado = -1 * Math.pow(-op1, op2);
                break;
        }

        return resultado;
    }

    private static boolean ehOperador (Character c)
    {
        return "/*-+()^@#".contains(c.toString());
    }
}
