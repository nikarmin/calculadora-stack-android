package com.example.calculadora_stack_android;

import java.util.List;

public class ConversorPosfixa
{
    public static String converter(String infixa) throws Exception
    {
        if (infixa == null || infixa.equals(""))
            throw new Exception("Expressão infixa inválida!");

        String resultado = "";
        PilhaLista<Character> pilha = new PilhaLista<>();
        char operadorComMaiorPrecedencia = ' ';

        for (int x = 0; x < infixa.length(); x++)
        {
            char simboloLido = infixa.charAt(x);

            if (!ehOperador(simboloLido))
                resultado += simboloLido;
            else
            {
                boolean parar = false;

                try
                {
                    while (!parar && !pilha.estaVazia() && temPrecendecia(pilha.oTopo(), simboloLido))
                    {
                        operadorComMaiorPrecedencia = pilha.desempilhar();

                        if (operadorComMaiorPrecedencia != '(')
                            resultado += operadorComMaiorPrecedencia;
                        else
                        {
                            parar = true;
                            pilha.empilhar(operadorComMaiorPrecedencia);
                        }
                    }
                }
                catch (Exception underflowPilha)
                {
                    System.err.println(underflowPilha.getMessage());
                }

                if (simboloLido != ')')
                    pilha.empilhar(simboloLido);
                else
                    operadorComMaiorPrecedencia = pilha.desempilhar();
            }
        }

        while (!pilha.estaVazia())
        {
            operadorComMaiorPrecedencia = pilha.desempilhar();
            if (operadorComMaiorPrecedencia != '(')
                resultado += operadorComMaiorPrecedencia;
        }

        return resultado;
    }

    private static boolean temPrecendecia(char topo, char simbol)
    {
        byte pTopo = verificarValorDePrecedencia(topo);
        byte pSimbol = verificarValorDePrecedencia(simbol);

        if (pTopo < pSimbol)
            return true;
        else if (pTopo == pSimbol && pTopo != 1)
            return true;

        return false;
    }

    private static byte verificarValorDePrecedencia(char simbolo)
    {

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

    private static boolean ehOperador (Character c)
    {
        return "/*-+()^@#".contains(c.toString());
    }
}
