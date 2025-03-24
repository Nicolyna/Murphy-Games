/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package murphygames;

import java.time.LocalDateTime;
import java.util.List;

public class Venda {
    private int id;
    private Cliente cliente;
    private Produto produto;
    private int quantidade;
    private LocalDateTime data;

    public Venda(Cliente cliente, Produto produto, int quantidade) {
        this.cliente = cliente;
        this.produto = produto;
        this.quantidade = quantidade;
        this.data = LocalDateTime.now();
    }

    // Getters
    public Cliente getCliente() { return cliente; }
    public Produto getProduto() { return produto; }
    public int getQuantidade() { return quantidade; }
    public LocalDateTime getData() { return data; }
}
