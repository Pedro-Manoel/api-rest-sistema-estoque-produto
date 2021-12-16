package br.com.estoque.service.implementation;

import br.com.estoque.exception.TipoProdutoExistenteException;
import br.com.estoque.exception.TipoProdutoNaoExisteException;
import br.com.estoque.model.TipoProduto;
import br.com.estoque.repository.TipoProdutoRepository;
import br.com.estoque.service.TipoProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoProdutoServiceImpl implements TipoProdutoService {

    @Autowired
    private TipoProdutoRepository tipoProdutoRepository;

    public TipoProduto getTipoProdutoById(Long id) {
        return tipoProdutoRepository.findById(id)
                .orElseThrow(()-> new TipoProdutoNaoExisteException(id));
    }

    public TipoProduto criarTipoProduto(TipoProduto tipoProduto) {
        if (tipoProdutoRepository.existsByNome(tipoProduto.getNome())) {
            throw new TipoProdutoExistenteException(tipoProduto.getNome());
        }

        return tipoProdutoRepository.save(tipoProduto);
    }

    public void removerTipoProduto(Long id) {
        TipoProduto tipoProduto = getTipoProdutoById(id);

        tipoProdutoRepository.delete(tipoProduto);
    }

    public TipoProduto atualizarTipoProduto(Long id, TipoProduto novoTipoProduto) {
        TipoProduto tipoProduto = getTipoProdutoById(id);
        tipoProduto.setNome(novoTipoProduto.getNome());

        return tipoProdutoRepository.save(tipoProduto);
    }

    public List<TipoProduto> listarTiposProduto(){
        return tipoProdutoRepository.findAll();
    }

}
