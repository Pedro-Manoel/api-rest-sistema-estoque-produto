package br.com.estoque.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoResponseDTO {

    private Long id;
    private String nome;
    private BigDecimal precoVenda;
    private BigDecimal precoCompra;
    private Long qttEstoque;

    private FornecedorResponseDTO fornecedor;

    private TipoProdutoResponseDTO tipoProduto;
}
