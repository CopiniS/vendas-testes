
package com.mycompany.sistema.vendas;

import java.util.List;
import javax.swing.JOptionPane;
import models.BancoProdutos;
import models.Pedido;
import models.Produto;

public class SistemaVendas {

    public static void main(String[] args) {
            String inicialInfo = JOptionPane.showInputDialog("list - para ver os produtos disponiveis\n"
                + "add - para adicionar um produto ao carrinho\n"
                + "remove - para remover um produto do pedido \n "
                + "update - para alterar a quantidade de um produto do pedido \n"
                + "pay - para finalizar a compra \n"
                + "end - para encerrar o programa \n");
        
        Pedido pedido = new Pedido();
        BancoProdutos bd = new BancoProdutos();
        List listaProdutos = bd.criaProdutos();
   
        while(!inicialInfo.equals("end")){
            String idSelecionado;
            switch (inicialInfo) {
                case "list":
                    JOptionPane.showMessageDialog(null, bd.mostraProdutos());
                    break;
                
                case "add":
                    idSelecionado = JOptionPane.showInputDialog("DIGITE O ID DO PRODUTO DESEJADO" + bd.mostraProdutos());
                    if(bd.isInteger(idSelecionado)){
                        Produto p = bd.retornaProdutoPeloId(Integer.parseInt(idSelecionado));
                        if(p != null){
                            String quantidade = JOptionPane.showInputDialog("Digite a quantidade que deseja do produto " + p.getNome());
                            if(bd.isInteger(quantidade) && !bd.verificaProdutosPedido(p, pedido.getItensvenda())){
                                pedido.adicionaProduto(p, Integer.parseInt(quantidade));
                                JOptionPane.showMessageDialog(null, "Produto adicionado com sucesso");
                            }
                            else if(bd.verificaProdutosPedido(p, pedido.getItensvenda())){
                                pedido.alteraProduto(Integer.parseInt(quantidade), p.getId());
                                JOptionPane.showMessageDialog(null, "Produto adicionado com sucesso");
                            }
                            else{
                               JOptionPane.showMessageDialog(null, quantidade + " não é um valor valido, digite um numero inteiro positivo"); 
                            }
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Id incorreto, digite um valor valido");
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Id incorreto, digite um valor valido");
                    }
                    break;
                    
                case "remove": 
                    idSelecionado = JOptionPane.showInputDialog("DIGITE O ID DO PRODUTO QUE DESEJA REMOVER DO PEDIDO" + bd.mostraPedido(pedido));
                    if(bd.isInteger(idSelecionado)){
                        Produto p = bd.retornaProdutoPeloIdPedidos(Integer.parseInt(idSelecionado), pedido.getItensvenda());
                        if(p != null){
                            pedido.removeProduto(p.getId());
                            JOptionPane.showMessageDialog(null, "Produto removido com sucesso");
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Id incorreto, digite um valor valido");
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Id incorreto, digite um valor valido");
                    }
                    break;
                    
                case "update":
                    idSelecionado = JOptionPane.showInputDialog("DIGITE O ID DO PRODUTO QUE DESEJA ALTERAR DO PEDIDO" + bd.mostraPedido(pedido));
                    if(bd.isInteger(idSelecionado)){
                        Produto p = bd.retornaProdutoPeloIdPedidos(Integer.parseInt(idSelecionado), pedido.getItensvenda());
                        if(p != null){
                            String quantidade = JOptionPane.showInputDialog("Digite a quantidade desejada do produto " + p.getNome());
                            pedido.alteraProduto(Integer.parseInt(quantidade), p.getId());
                            JOptionPane.showMessageDialog(null, "Produto alterado com sucesso");
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Id incorreto, digite um valor valido");
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Id incorreto, digite um valor valido");
                    }
                    break;
                    
                case "pay":
                    String[] opcoesPagamento = { "Dinheiro", "Pix", "Cartão de Crédito", "Cartão de Débito" };

                    // Exibir caixa de diálogo de seleção de pagamento
                    int opcaoSelecionada = JOptionPane.showOptionDialog(null, bd.mostraPedido(pedido) + "\n\nSELECIONE A FORMA DE PAGAMENTO:", "Pagamento",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcoesPagamento,
                            opcoesPagamento[0]);

                    // Verificar a opção selecionada pelo usuário
                    if (opcaoSelecionada >= 0 && opcaoSelecionada < opcoesPagamento.length && !pedido.verificaPedidoVazio()) {
                        String formaPagamento = opcoesPagamento[opcaoSelecionada];
                        if(formaPagamento.equals("Cartão de Crédito")){
                            String parcelas = JOptionPane.showInputDialog("Digite a quantidade de parcelas desejadas para o valor: " + pedido.getValorTotal());
                            if(bd.isInteger(parcelas)){
                                if(pedido.verificaParcelasCredito(Double.parseDouble(parcelas))){
                                    JOptionPane.showMessageDialog(null, "Forma de pagamento escolhida: "+ formaPagamento + "\nQuantidade de parcelas: "+parcelas);
                                }
                                else{
                                    JOptionPane.showMessageDialog(null, "Quantidade de parcelas invalida.\nAs parcelas não podem ser inferiores nem iguais a R$ 20,00");
                                }
                            }
                            else{
                                JOptionPane.showMessageDialog(null, parcelas + " é um valor invalido para as parcelas");
                            }
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Foma de pagamento escolhida: " + formaPagamento);
                        }
                        
                    } 
                    else if(pedido.verificaPedidoVazio()) {
                        JOptionPane.showMessageDialog(null, "Nenhum produto adicionado ao pedido, adicione um antes de escolher a forma de pagamento");
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Nenhuma forma de pagamento selecionada");
                    }
                    
                    
                    
                    break;
                                      
            }
            
            inicialInfo = JOptionPane.showInputDialog("list - para ver os produtos disponiveis\n"
                + "add - para adicionar um produto ao carrinho\n"
                + "remove - para remover um produto do pedido \n "
                + "update - para alterar a quantidade de um produto do pedido \n"
                + "pay - para finalizar a compra \n"
                + "end - para encerrar o programa \n");
        }
    }
}
