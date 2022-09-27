/**
 @authors Nicoli Ferreira e Julia Flausino
 @since 2022
 */

package com.example.calculadora_stack_android;

import java.util.ArrayList;
import java.util.List;

// como faz pra D implementar Comparable?
public class PilhaLista<D extends Comparable<D>> implements Stack<D>
{
    private NoLista<D> topo;
    private int tamanho;

    public PilhaLista()
    {
        topo = null;
        tamanho = 0;
    }

    @Override
    public void empilhar(D dado)
    {
        NoLista<D> novoNo = new NoLista<D>(dado, topo);
        topo = novoNo;
        tamanho++;
    }

    @Override
    public D desempilhar() throws Exception
    {
        if (estaVazia())
            throw new Exception("Underflow da pilha!");
        D dado = topo.getInfo();
        topo = topo.getProx();
        tamanho--;
        return dado;
    }

    @Override
    public D oTopo() throws Exception
    {
        if (estaVazia())
            throw new Exception("Underflow da pilha!");
        return topo.getInfo();
    }

    @Override
    public int getTamanho()
    {
        return tamanho;
    }

    @Override
    public boolean estaVazia()
    {
        return topo == null;
    }

    @Override
    public List<D> dadosDaPilha()
    {
        List<D> lista = new ArrayList<>();
        NoLista<D> atual = topo;

        while (atual != null)
        {
            lista.add(atual.getInfo());
            atual = atual.getProx();
        }
        return lista;
    }
}