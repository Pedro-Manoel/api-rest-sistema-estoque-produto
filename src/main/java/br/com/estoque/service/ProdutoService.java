package br.com.estoque.service;

import br.com.estoque.dto.ProdutoDTO;
import br.com.estoque.dto.ProdutoEstoqueDTO;

import java.util.List;

public interface ProdutoService {
    ProdutoDTO getProduto (Long id);

    ProdutoDTO criarProduto (ProdutoDTO produtoDTO);

    List<ProdutoDTO> listarProdutos ();

    void removerProduto (Long id);

    ProdutoDTO atualizarProduto (Long id, ProdutoDTO produtoDTO);

    ProdutoDTO atualizarEstoqueProduto (Long id, ProdutoEstoqueDTO produtoEstoqueDTO);
}
