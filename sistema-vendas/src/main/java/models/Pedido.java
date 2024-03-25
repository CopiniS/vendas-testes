
package models;

import java.util.ArrayList;

public class Pedido {
    private ArrayList<Item> itensvenda;
    private Double valorTotal;
    private String formaPagamento;

    public Pedido() {
        this.itensvenda = new ArrayList();
    }

    public ArrayList<Item> getItensvenda() {
        return itensvenda;
    }

    public void setItensvenda(ArrayList<Item> itensvenda) {
        this.itensvenda = itensvenda;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }
    
    
    public boolean verificaProdutos(){
        return itensvenda.isEmpty();
    }
    
    public void adicionaProduto(Produto produto, int quantidade){
        Item item = new Item(produto, quantidade);
        this.itensvenda.add(item);
    }
    
    public void removeProduto(int id){
        Item excluido = null;
        for(Item item : this.itensvenda){
            if(item.getProduto().getId() == id){
                excluido = item;
            }
        }
        this.itensvenda.remove(excluido);
    }
    
    public void alteraProduto(int quantidade, int id){
        for(Item item : this.itensvenda){
            if(item.getProduto().getId() == id){
                item.setQuantidade(quantidade);
            }
        }        
    }
    
    public boolean verificaParcelasCredito(double quantidadeParcelas){
        boolean maisDe20 = true;
        double valorParcelas = this.valorTotal / quantidadeParcelas;
        
        if(valorParcelas <= 20){
            maisDe20 = false;
        }
        
        return maisDe20;
    }
    
    public void calculaValorTotal(){
        this.valorTotal = 0.0;
        for(Item item : this.itensvenda){
            this.valorTotal = this.valorTotal + (item.getProduto().getValor() * item.getQuantidade());
        }
    }
    
    public boolean verificaPedidoVazio(){
        return this.itensvenda.isEmpty();
    }
    
}
