package br.com.estoque.dto;

import br.com.estoque.model.Produto;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoDTO {

    @ApiModelProperty(hidden = true)
    private Long id;

    private String nome;

    private BigDecimal precoVenda;

    private BigDecimal precoCompra;

    private Long quantEstoque;

    private String codBarra;

    private Long fornecedorId;

    private Long tipoProdutoId;

    public ProdutoDTO (Produto produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.precoVenda = produto.getPrecoVenda();
        this.precoCompra = produto.getPrecoCompra();
        this.quantEstoque = produto.getQuantEstoque();
        this.codBarra = produto.getCodBarra();
        this.fornecedorId = produto.getFornecedor().getId();
        this.tipoProdutoId = produto.getTipo().getId();
    }

    public static Produto toEntity (ProdutoDTO produtoDTO) {
        return Produto
                .builder()
                .nome(produtoDTO.getNome())
                .precoVenda(produtoDTO.getPrecoVenda())
                .precoCompra(produtoDTO.getPrecoCompra())
                .quantEstoque(produtoDTO.getQuantEstoque())
                .codBarra(produtoDTO.getCodBarra())
                .build();
    }

    public static List<ProdutoDTO> toListDTO (List<Produto> produtos) {
        return produtos
                .stream()
                .map(ProdutoDTO::new)
                .collect(Collectors.toList());
    }
}
