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
    private Long fornecedorId;
    private Long tipoProdutoId;

    public static Produto toEntity(ProdutoDTO produtoDTO) {
        Produto produto = new Produto();
        produto.setNome(produtoDTO.getNome());
        produto.setPrecoVenda(produtoDTO.getPrecoVenda());
        produto.setPrecoCompra(produtoDTO.getPrecoCompra());
        produto.setQttEstoque(produtoDTO.getQttEstoque());

        return produto;
    }

    public static ProdutoDTO toDTO(Produto produto) {
        ProdutoDTO produtoDTO = new ProdutoDTO();
        produtoDTO.setId(produto.getId());
        produtoDTO.setNome(produto.getNome());
        produtoDTO.setPrecoVenda(produto.getPrecoVenda());
        produtoDTO.setPrecoCompra(produto.getPrecoCompra());
        produtoDTO.setQttEstoque(produto.getQttEstoque());
        produtoDTO.setFornecedorId(produto.getFornecedor().getId());
        produtoDTO.setTipoProdutoId(produto.getTipo().getId());

        return produtoDTO;
    }
}
