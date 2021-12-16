package br.com.estoque.service;

import br.com.estoque.model.Produto;

import java.util.List;

public interface ProdutoService {
    Produto getProdutoById (Long id);

    Produto criarProduto(Produto produto);

    List<Produto> listarProdutos ();

    void removerProduto (Long id);

    Produto atualizarProduto (Long id, Produto produto);

    Produto atualizarEstoqueProduto (Long id, Long novoEstoque);
}
