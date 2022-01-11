package br.com.estoque.service.implementation;

import br.com.estoque.dto.TipoProdutoDTO;
import br.com.estoque.exception.TipoProdutoExistenteException;
import br.com.estoque.exception.TipoProdutoNaoExisteException;
import br.com.estoque.model.TipoProduto;
import br.com.estoque.repository.TipoProdutoRepository;
import br.com.estoque.service.TipoProdutoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class TipoProdutoServiceImpl implements TipoProdutoService {

    private TipoProdutoRepository tipoProdutoRepository;

    public TipoProduto getTipoProdutoById (Long id) {
        return tipoProdutoRepository.findById(id)
                .orElseThrow(()-> new TipoProdutoNaoExisteException(id));
    }

    public TipoProdutoDTO getTipoProduto (Long id) {
        return new TipoProdutoDTO(getTipoProdutoById(id));
    }

    public TipoProdutoDTO criarTipoProduto (TipoProdutoDTO tipoProdutoDTO) {
        verificarTipoProdutoExiste(tipoProdutoDTO);

        TipoProduto tipoProduto = TipoProdutoDTO.toEntity(tipoProdutoDTO);

        return new TipoProdutoDTO(tipoProdutoRepository.save(tipoProduto));
    }

    public void removerTipoProduto (Long id) {
        TipoProduto tipoProduto = getTipoProdutoById(id);

        tipoProdutoRepository.delete(tipoProduto);
    }

    public TipoProdutoDTO atualizarTipoProduto (Long id, TipoProdutoDTO tipoProdutoDTO) {
        TipoProduto tipoProduto = getTipoProdutoById(id);
        if (!tipoProduto.getNome().equals(tipoProdutoDTO.getNome())) {
            verificarTipoProdutoExiste(tipoProdutoDTO);
        }

        tipoProduto.setNome(tipoProdutoDTO.getNome());

        return new TipoProdutoDTO(tipoProdutoRepository.save(tipoProduto));
    }

    public List<TipoProdutoDTO> listarTiposProduto () {
        return TipoProdutoDTO.toListDTO(tipoProdutoRepository.findAll());
    }

    private void verificarTipoProdutoExiste (TipoProdutoDTO tipoProdutoDTO) {
        if (tipoProdutoRepository.existsByNome(tipoProdutoDTO.getNome())) {
            throw new TipoProdutoExistenteException(tipoProdutoDTO.getNome());
        }
    }
}
