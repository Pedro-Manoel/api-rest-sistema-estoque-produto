package br.com.estoque.service;

import br.com.estoque.model.TipoProduto;

import java.util.List;

public interface TipoProdutoService {
    TipoProduto getTipoProdutoById(Long id);

    TipoProduto criarTipoProduto(TipoProduto tipoProduto);

    void removerTipoProduto(Long id);

    TipoProduto atualizarTipoProduto(Long id, TipoProduto novoTipoProduto);

    List<TipoProduto> listarTiposProduto();
}
