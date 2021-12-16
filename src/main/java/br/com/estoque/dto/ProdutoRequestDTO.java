package br.com.estoque.dto;

import br.com.estoque.model.Fornecedor;
import br.com.estoque.model.TipoProduto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoRequestDTO {

    private String nome;
    private BigDecimal precoVenda;
    private BigDecimal precoCompra;
    private Long qttEstoque;

    private Long fornecedorId;

    private Long tipoId;
}
