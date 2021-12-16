package br.com.estoque.service;

import br.com.estoque.exception.TipoProdutoNaoExisteException;
import br.com.estoque.model.TipoProduto;
import br.com.estoque.repository.TipoProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoProdutoService {

    @Autowired
    private TipoProdutoRepository tipoProdutoRepository;

    public TipoProduto getTipoProdutoById(Long id) {
        return tipoProdutoRepository.findById(id).orElseThrow(()-> new TipoProdutoNaoExisteException(id));
    }

    public TipoProduto criarTipoProduto(TipoProduto tipoProduto) {
        return tipoProdutoRepository.save(tipoProduto);
    }

    public void removerTipoProduto(Long id) {
        TipoProduto tipoProduto = getTipoProdutoById(id);
        tipoProdutoRepository.delete(tipoProduto);
    }

    public TipoProduto atualizarTipoProduto(Long id, TipoProduto tipoProduto) {
        TipoProduto novoTipoProduto = getTipoProdutoById(id);
        novoTipoProduto.setNome(tipoProduto.getNome());

        return tipoProdutoRepository.save(novoTipoProduto);
    }

    public List<TipoProduto> listarTiposProduto(){
        return tipoProdutoRepository.findAll();
    }

}
