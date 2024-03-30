

import models.Item;
import models.Mock;
import models.Pedido;
import models.Produto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author gabri
 */
public class PedidoTest {
    private Pedido pedido;
    Item item;
    Item item2;
    Produto produto;
    Produto produto2;
    private double valorTotal;
    
    public PedidoTest() {
    }

    @BeforeEach
    public void setUp() {
        this.produto = new Produto("Mouse gamer", 150);
        this.produto2 = new Produto("Teclado Gamer", 100);
        
        this.pedido = new Pedido();
        pedido.setValorTotal(0.0);
        
    }
    @Test
    public void testVerificaProdutosVazio() {
        assertEquals(true, this.pedido.verificaProdutos());
    }

    @Test
    public void retornaFalseSeItemExluidoNaoEstiverNosItensVenda(){
        
        this.pedido.adicionaProduto(produto, 1);
        this.produto.setId(1);
        Item item = new Item(this.produto2, 1);
        this.produto2.setId(2);
        this.pedido.getItensvenda().add(item);

        // Remove um item de venda existente na lista
        this.pedido.removeProduto(2);

        // Verifica se o item foi removido corretamente
        assertEquals(false, this.pedido.getItensvenda().contains(item));
    }
    
    @Test
    public void retornaQuantidadeAlterada(){
        Item item = new Item(this.produto2, 1);
        item.setProduto(this.produto);
        this.produto.setNome("headset gamer");
        this.produto.setValor(200);
        item.getProduto().setId(1);
        Item itemAux = new Item(produto, 3);
        this.pedido.getItensvenda().add(item);
        itemAux.setQuantidade(3);
        this.pedido.alteraProduto(3, 1);
        assertEquals(itemAux.getQuantidade(), this.pedido.getItensvenda().get(0).getQuantidade());
    }
    
    @Test
    public void testVerificaProdutosNaoVazio() {
        this.pedido.adicionaProduto(this.produto, 1);
        assertEquals(false, this.pedido.verificaProdutos());
    }
    
    @Test
    public void retornaFalseSeValorParcelasMenorQue20(){
        this.pedido.setValorTotal(199.0);
        double quantidadeParcelas = 10;
        
        assertEquals(false, this.pedido.verificaParcelasCredito(quantidadeParcelas));
    }
    
    @Test
    public void retornaFalseSeValorParcelasIgualA20(){
        this.pedido.setValorTotal(200.0);
        double quantidadeParcelas = 10;
        
        assertEquals(false, this.pedido.verificaParcelasCredito(quantidadeParcelas));
    }
    
    @Test
    public void retornaTrueSeValorParcelasMaiorQue20(){
        this.pedido.setValorTotal(210.0);
        double quantidadeParcelas = 10;
        
        assertEquals(true, this.pedido.verificaParcelasCredito(quantidadeParcelas));
    }
    
    @Test
    public void verificaCalculaValorTotal(){
    }
    
    @Test
    public void retornaFalseSeListaDeItensNaoEstiverVazia(){
        this.pedido.adicionaProduto(this.produto, 1);
        assertEquals(false, this.pedido.verificaPedidoVazio());
    }
    
    @Test
    public void retornaTrueSeListaDeItensEstiverVazia(){
        assertEquals(true, this.pedido.verificaPedidoVazio());
    }
    
    @Test 
    public void retornaApenasStrDoTituloSeListaEstiverVazia(){
        String titulo = "\nLista de produtos adicionados: \n\n"; 
        String texto = titulo + "Valor Total: " + this.pedido.getValorTotal();
        assertEquals(texto, this.pedido.mostraPedido());
    }
    
    @Test
    public void retornaStrDeProdutosEquantidadesEvalorTotalDoPedido(){
        String titulo = "\nLista de produtos adicionados: \n\n";
        String textoProdutos = titulo;
        this.pedido.setValorTotal(300.0);
        this.pedido.adicionaProduto(produto, 2);
        if(!this.pedido.getItensvenda().isEmpty()){
            textoProdutos = textoProdutos + "ID: " + this.produto.getId() + "  -  Produto: " + this.produto.getNome() + 
                       "  -  quantidade: " + this.pedido.getItensvenda().get(0).getQuantidade() + "  -  valor: " + this.pedido.getValorTotal() + "\n";
            
        }
        textoProdutos = textoProdutos + "Valor Total: " + pedido.getValorTotal();
        
        assertEquals(textoProdutos, pedido.mostraPedido());
    }
    
    @Test 
    public void retornaNullSeIdNaoEstiverEmItensVenda(){
        this.produto.setId(1);
        this.pedido.adicionaProduto(this.produto, 2);
        assertEquals(null, this.pedido.retornaProdutoPeloIdPedidos(0));
    }
    
    @Test
    public void retornaProdutoSeIdEstiverEmItensVenda(){
        this.produto.setId(1);
        this.pedido.adicionaProduto(this.produto, 1);
        this.produto2.setId(2);
        this.pedido.adicionaProduto(this.produto2, 1);
        assertEquals(this.produto2, this.pedido.retornaProdutoPeloIdPedidos(2));
    }
    
    @Test 
    public void retornaNullSeItensVendaVazia(){
         assertEquals(null, this.pedido.retornaProdutoPeloIdPedidos(0));
    }
    
    @Test 
    public void RetornaTrueSeProdutoEstiverEmItensVenda(){
        this.pedido.adicionaProduto(this.produto, 1);
        this.pedido.adicionaProduto(this.produto2, 2);
        
        assertEquals(true, this.pedido.verificaProdutoDentroPedido(this.produto2));
    } 
    
    @Test
    public void RetornaFalseSeProdutoNaoEstiverEmItensVenda(){
        Produto produto3 = new Produto("MousePad", 30);
        this.pedido.adicionaProduto(this.produto, 1);
        this.pedido.adicionaProduto(this.produto2, 2);
        
        assertEquals(false, pedido.verificaProdutoDentroPedido(produto3));
    }
    
    @Test
    public void RetornaFalseSeItensVendaEstiverVazia(){
        assertEquals(false, this.pedido.verificaProdutoDentroPedido(this.produto));
    }
}
