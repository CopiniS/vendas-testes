
package models;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class BancoProdutos {
     List<Produto> lista = new ArrayList<Produto>();
    public List criaProdutos(){
       
        
        Produto p1 = new Produto("Mouse gamer", 150.50);
        p1.setId(1);
        lista.add(p1);
        
        Produto p2 = new Produto("Teclado gamer", 150.50);
        p2.setId(2);
        lista.add(p2);
        
        Produto p3 = new Produto("HeadSet gamer", 150.50);
        p3.setId(3);
        lista.add(p3);
        
        Produto p4 = new Produto("Monitor gamer", 150.50);
        p4.setId(4);
        lista.add(p4);
        
        Produto p5 = new Produto("Cadeira gamer", 150.50);
        p5.setId(5);
        lista.add(p5);
        
        Produto p6 = new Produto("Mouse escritório", 150.50);
        p6.setId(6);
        lista.add(p6);
        
        Produto p7 = new Produto("Teclado escritório", 150.50);
        p7.setId(7);
        lista.add(p7);
        
        Produto p8 = new Produto("Fone de ouvido", 150.50);
        p8.setId(8);
        lista.add(p8);
        
        return lista;
    }
    
    public String mostraProdutos(){
        String textoProdutos = "Lista de produtos: \n\n";
        
        for(Produto p : this.lista){
           textoProdutos = textoProdutos + "ID: " + p.getId() + "  -  Produto: " + p.getNome() + "  -  valor: " + p.getValor() + "\n";
        }
        return textoProdutos;
    }
    
    public Produto retornaProdutoPeloId(int idProduto){
        for(Produto p : this.lista){
            if(p.getId() == idProduto){
                return p;
            }
        }
        return null;
    }
    
    public static boolean isInteger(String str) {
        return str.matches("\\d+");
    }
    
    public String mostraPedido(Pedido pedido){
        String textoProdutos = "\nLista de produtos adicionados: \n\n";
        
        for(Item i : pedido.getItensvenda()){
           textoProdutos = textoProdutos + "ID: " + i.getProduto().getId() + "  -  Produto: " + i.getProduto().getNome() + 
                   "  -  quantidade: " + i.getQuantidade() + "  -  valor: " +i.getProduto().getValor() * i.getQuantidade() + "\n";
        }
        pedido.calculaValorTotal();
        textoProdutos = textoProdutos + "Valor Total: " + pedido.getValorTotal();
        return textoProdutos;
    }
    
    public Produto retornaProdutoPeloIdPedidos(int idProduto, List<Item> lista){
        for(Item i : lista){
            if(i.getProduto().getId() == idProduto){
                return i.getProduto();
            }
        }
        return null;
    }
    
    public boolean verificaProdutosPedido(Produto produto, List<Item> lista){
        boolean resultado =false;
        for(Item i : lista){
            if(i.getProduto().equals(produto)){
                resultado = true;
            }
        }
        return resultado;
    }
}
