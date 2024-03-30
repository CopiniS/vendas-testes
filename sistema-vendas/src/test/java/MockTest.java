/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import models.Mock;
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
public class MockTest {
    Mock mock;
    public MockTest() {
    }

    @BeforeEach
    public void setUp() {
        mock = new Mock();
    }
    @Test
    public void retornaApenasTituloSeListaVazia(){
        String titulo = "Lista de produtos: \n\n";
        assertEquals(titulo, this.mock.mostraProdutos());
    }
    
    @Test
    public void retornaStringComListaDeProdutos(){
        String textoProdutos = "Lista de produtos: \n\n";
        Produto produto = new Produto("Mouse", 100);
        mock.getLista().add(produto);
        textoProdutos = textoProdutos + "ID: " + produto.getId() + "  -  Produto: " + produto.getNome() + "  -  valor: " + produto.getValor() + "\n";
        assertEquals(textoProdutos, mock.mostraProdutos());
    }
    
    @Test 
    public void retornaNullSeIdNaoEstiverNoMock(){
        mock.criaProdutos();
        assertEquals(null, mock.retornaProdutoPeloId(0));
    }
    @Test
    public void retornaProdutoSeIdEstiverNoMock(){
        mock.criaProdutos();
        assertEquals(mock.getLista().get(0), mock.retornaProdutoPeloId(1));
    }
    @Test
    public void retornaNullSeMockEstiverVazio(){
        assertEquals(null, mock.retornaProdutoPeloId(1));
    }
    
    @Test 
    public void retornaFalseSevalorNaoForInt(){
        String valor = "abcaxi";
        assertEquals(false, mock.isInteger(valor));
    }
    
    @Test 
    public void retornaTrueSevalorForInt(){
        String valor = "1";
        assertEquals(true, mock.isInteger(valor));
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
