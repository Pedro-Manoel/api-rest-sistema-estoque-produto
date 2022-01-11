package br.com.estoque.service;

import br.com.estoque.dto.TipoProdutoDTO;
import br.com.estoque.model.TipoProduto;

import java.util.List;

public interface TipoProdutoService {
    TipoProduto getTipoProdutoById (Long id);

    TipoProdutoDTO getTipoProduto (Long id);

    TipoProdutoDTO criarTipoProduto (TipoProdutoDTO tipoProdutoDTO);

    void removerTipoProduto (Long id);

    TipoProdutoDTO atualizarTipoProduto (Long id, TipoProdutoDTO tipoProdutoDTO);

    List<TipoProdutoDTO> listarTiposProduto ();
}
