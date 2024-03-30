
package models;

import java.util.ArrayList;
import java.util.List;

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
    
    public String mostraPedido(){
        String textoProdutos = "\nLista de produtos adicionados: \n\n";
        if(!this.itensvenda.isEmpty()){
            for(Item i : this.itensvenda){
               textoProdutos = textoProdutos + "ID: " + i.getProduto().getId() + "  -  Produto: " + i.getProduto().getNome() + 
                       "  -  quantidade: " + i.getQuantidade() + "  -  valor: " +i.getProduto().getValor() * i.getQuantidade() + "\n";
            }
        }
        calculaValorTotal();
        textoProdutos = textoProdutos + "Valor Total: " + this.valorTotal;
        return textoProdutos;
    }
    
    public Produto retornaProdutoPeloIdPedidos(int idProduto){
        boolean temProduto = false;
        Produto produto = new Produto("", 0);
        for(Item i : this.itensvenda){
            if(i.getProduto().getId() == idProduto){
                temProduto = true;
                produto = i.getProduto();
            }
        }
        if(temProduto){
            return produto;
        }
        else{
            return null;
        }
    }
    
    public boolean verificaProdutoDentroPedido(Produto produto){
        boolean resultado =false;
        for(Item i : this.itensvenda){
            if(i.getProduto().equals(produto)){
                resultado = true;
            }
        }
        return resultado;
    }
    
}
