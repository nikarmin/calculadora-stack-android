package com.example.calculadora_stack_android;

public class VerificadorDeBalanceamento
{
    public static boolean estaBalanceada(String expressao)
    {
        PilhaLista<Character> pilhaBalanceamento = new PilhaLista<>();
        boolean estaBalanceada = true;

        for (int i = 0; i < expressao.length() && estaBalanceada; i++)
        {
            char caractereLido = expressao.charAt(i);

            if (caractereLido == '(')
                pilhaBalanceamento.empilhar(caractereLido);

            else if (caractereLido == ')')
            {
                if (pilhaBalanceamento.estaVazia())
                    estaBalanceada = false;
                else
                {
                    char aberturaAnterior = ' ';

                    try
                    {
                        aberturaAnterior = pilhaBalanceamento.desempilhar();
                    }
                    catch (Exception ex)
                    {
                        estaBalanceada = false;
                    }
                }
            }
            else
                estaBalanceada = true;
        }

        if (!pilhaBalanceamento.estaVazia())
            estaBalanceada = false;

        if (estaBalanceada && pilhaBalanceamento.estaVazia())
            estaBalanceada = true;

        return estaBalanceada;
    }
}
