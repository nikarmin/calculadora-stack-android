package com.example.calculadora_stack_android;

import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class CalculadoraStack {
        private List<Double> operandos; // lista com os operandos da expressão
        private Stack<Double> pilhaValor; // auxilia no cálculo do valor da expressão
        private String expPosfix, expInfx; // guarda a expressão posfixa e infixa
        private double resultado;          // guarda o resultado da conta

        public CalculadoraStack(String expNumeros) throws Exception
        {
             if (!ExpressaoValida(expInfx))
                throw new Exception("Expressão inválida!");

            this.expInfx   = formarExpressaoInfixa(expNumeros);
            this.expPosfix = converterInfixaParaPosfixa(expInfx);
        }

        private Boolean ExpressaoValida(String expressao) {

            // ERRADO ERRADO ERRADO ERRADO AAAAAAAAAAHAHAHAHAHAHAH

            if (expressao != null) {

                for (int x = 0; x < expressao.length(); x++){
                    if (!EhOperador(expressao.charAt(x)) || Character.isLetter(expressao.charAt(x))){
                        return false;
                    }
                }
                return true;
            }
            return false;
        }

        private String converterInfixaParaPosfixa(String expInfx) throws Exception {
            String expPosfix = "";
            PilhaLista<Character> pilhaAux = new PilhaLista<Character>();
            char operadorComMaiorPrecedencia;

            for (int x = 0; x < expInfx.length(); x++)
            {
                char simboloLido = expInfx.charAt(x);
                if (!EhOperador(simboloLido))
                    expPosfix += simboloLido;
                else
                {
                    boolean parar = false;

                    try
                    {
                        while (!parar && !pilhaAux.estaVazia() && TemPrecendecia(pilhaAux.oTopo(), simboloLido))
                        {
                            operadorComMaiorPrecedencia = pilhaAux.desempilhar();

                            if (operadorComMaiorPrecedencia != '(')
                                expPosfix += operadorComMaiorPrecedencia;
                            else
                            {
                                parar = true;
                                pilhaAux.empilhar(operadorComMaiorPrecedencia);
                            }
                        }
                    }
                    catch (Exception underflowPilha)
                    {
                        System.err.println(underflowPilha.getMessage());
                    }


                    if (simboloLido != ')')
                        pilhaAux.empilhar(simboloLido);
                    else
                        operadorComMaiorPrecedencia = pilhaAux.desempilhar();
                }
            }

            return expPosfix;
        }

        private String formarExpressaoInfixa(String expressao) throws Exception {
            String expInfix = "";
            operandos = new ArrayList<Double>();

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

        private boolean verificarValidezDaExpressao (String expressao)
        {
            if (expressao == "" || expressao == null)
                return false;

            // verificar letras e afins
            for (int i = 0; i < expressao.length(); i++)
            {
                char caractere = expressao.charAt(i);
                if (i == expressao.length() - 1 && EhOperador(caractere))
                    return false;

            }

            return true;
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
        byte pTopo = VerificarValorDePrecedencia(topo);
        byte pSimboloLido = VerificarValorDePrecedencia(simboloLido);

        if (pTopo < pSimboloLido)
            return true;
        else if (pTopo == pSimboloLido && pTopo != 1)
            return true;

        return false;
    }

    Boolean EhOperador(Character c){

        if ("/*-+()^@#".contains(c.toString()))
        {
            return true;
        }

        else
            return false;
    }
}
