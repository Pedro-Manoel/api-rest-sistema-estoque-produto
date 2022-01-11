package br.com.estoque.service.implementation;

import br.com.estoque.dto.ProdutoDTO;
import br.com.estoque.dto.ProdutoEstoqueDTO;
import br.com.estoque.exception.ProdutoExistenteException;
import br.com.estoque.exception.ProdutoNaoExisteException;
import br.com.estoque.model.Fornecedor;
import br.com.estoque.model.Produto;
import br.com.estoque.model.TipoProduto;
import br.com.estoque.repository.ProdutoRepository;
import br.com.estoque.service.FornecedorService;
import br.com.estoque.service.ProdutoService;
import br.com.estoque.service.TipoProdutoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository produtoRepository;

    private final FornecedorService fornecedorService;
    private final TipoProdutoService tipoProdutoService;

    private Produto getProdutoById (Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new ProdutoNaoExisteException(id));
    }

    public ProdutoDTO getProduto (Long id) {
        return new ProdutoDTO(getProdutoById(id));
    }

    public ProdutoDTO criarProduto (ProdutoDTO produtoDTO) {
        verificarProdutoExiste(produtoDTO);

        Fornecedor fornecedor = fornecedorService.getFornecedorById(produtoDTO.getFornecedorId());
        TipoProduto tipoProduto = tipoProdutoService.getTipoProdutoById(produtoDTO.getTipoProdutoId());

        Produto produto = ProdutoDTO.toEntity(produtoDTO);
        produto.setFornecedor(fornecedor);
        produto.setTipo(tipoProduto);

        return new ProdutoDTO(produtoRepository.save(produto));
    }

    public List<ProdutoDTO> listarProdutos () {
        return ProdutoDTO.toListDTO(produtoRepository.findAll());
    }

    public void removerProduto (Long id) {
        Produto produto = getProdutoById(id);

        produtoRepository.delete(produto);
    }

    public ProdutoDTO atualizarProduto (Long id, ProdutoDTO produtoDTO) {
        Produto produto = getProdutoById(id);
        if (!produto.getCodBarra().equals(produtoDTO.getCodBarra())) {
            verificarProdutoExiste(produtoDTO);
        }

        Fornecedor fornecedor = fornecedorService.getFornecedorById(produtoDTO.getFornecedorId());
        TipoProduto tipoProduto = tipoProdutoService.getTipoProdutoById(produtoDTO.getTipoProdutoId());

        produto.setTipo(tipoProduto);
        produto.setFornecedor(fornecedor);
        produto.setNome(produtoDTO.getNome());
        produto.setPrecoCompra(produtoDTO.getPrecoCompra());
        produto.setPrecoVenda(produtoDTO.getPrecoVenda());
        produto.setQuantEstoque(produtoDTO.getQuantEstoque());
        produto.setCodBarra(produtoDTO.getCodBarra());

        return new ProdutoDTO(produtoRepository.save(produto));
    }

    public ProdutoDTO atualizarEstoqueProduto (Long id, ProdutoEstoqueDTO produtoEstoqueDTO) {
        Produto produto = getProdutoById(id);

        produto.setQuantEstoque(produtoEstoqueDTO.getQuantEstoque());

        return new ProdutoDTO(produtoRepository.save(produto));
    }

    private void verificarProdutoExiste (ProdutoDTO produtoDTO) {
        if (produtoRepository.existsByCodBarra(produtoDTO.getCodBarra())) {
            throw new ProdutoExistenteException(produtoDTO.getCodBarra());
        }
    }
}
