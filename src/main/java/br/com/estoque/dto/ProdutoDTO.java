package br.com.estoque.dto;

import br.com.estoque.model.Fornecedor;
import br.com.estoque.model.Produto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoDTO {

    private Long id;
    private String nome;
    private BigDecimal precoVenda;
    private BigDecimal precoCompra;
    private Long qttEstoque;

    private FornecedorDTO fornecedor;
    private TipoProdutoDTO tipoProduto;

    public static Produto toEntity(ProdutoDTO produtoDTO) {
        Produto produto = new Produto();
        produto.setNome(produtoDTO.getNome());
        produto.setPrecoVenda(produtoDTO.getPrecoVenda());
        produto.setPrecoCompra(produtoDTO.getPrecoCompra());
        produto.setQttEstoque(produtoDTO.getQttEstoque());
        produto.setFornecedor(FornecedorDTO.toEntity(produtoDTO.getFornecedor()));
        produto.setTipo(TipoProdutoDTO.toEntity(produtoDTO.getTipoProduto()));


        return produto;
    }

    public static ProdutoDTO toDTO(Produto produto) {
        ProdutoDTO produtoDTO = new ProdutoDTO();
        produtoDTO.setNome(produto.getNome());
        produtoDTO.setPrecoVenda(produto.getPrecoVenda());
        produtoDTO.setPrecoCompra(produto.getPrecoCompra());
        produtoDTO.setQttEstoque(produto.getQttEstoque());
        produtoDTO.setFornecedor(FornecedorDTO.toDTO(produto.getFornecedor()));
        produtoDTO.setTipoProduto(TipoProdutoDTO.toDTO(produto.getTipo()));

        return produtoDTO;
    }
}
