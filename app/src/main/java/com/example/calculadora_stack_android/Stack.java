/**
 @authors Nicoli Ferreira e Julia Flausino
 @since 2022
 */

package com.example.calculadora_stack_android;

import java.util.List;

public interface Stack<D> {
    void empilhar(D dado);            // empilha o objeto "dado"
    D desempilhar() throws Exception; // remove e retorna o objeto do topo
    D oTopo() throws Exception;       // retorna o objeto do topo sem removê-lo
    int getTamanho();                 // informa a quantidade de itens empilhados
    boolean estaVazia();              // informa se a pilha está ou não vazia
    List<D> dadosDaPilha();           // percorre a pilha e retorna uma lista com todos os itens empilhados,
                                      // sem alterar o conteúdo da pilha
}
