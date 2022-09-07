package com.example.calculadora_stack_android;

public class NoLista<Dado extends Comparable<Dado>>
{
    Dado info;
    NoLista<Dado> prox;

    public Dado getInfo() {
        return info;
    }

    public void setInfo(Dado info) {
        if (info != null)
            this.info = info;
    }

    public NoLista<Dado> getProx() {
        return prox;
    }

    public void setProx(NoLista<Dado> prox) {
        this.prox = prox;
    }

    public NoLista(Dado novaInfo, NoLista<Dado> proximoNo)
    {
        this.info = novaInfo;
        this.prox = proximoNo;
    }

    public NoLista(Dado novaInfo)
    {
        this.info = novaInfo;
        this.prox = null;
    }
}
