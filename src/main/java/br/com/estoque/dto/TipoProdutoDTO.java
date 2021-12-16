package br.com.estoque.dto;

import br.com.estoque.model.Produto;
import br.com.estoque.model.TipoProduto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TipoProdutoDTO {

    private String nome;

    public static TipoProduto toEntity(TipoProdutoDTO tipoProdutoDTO) {
        TipoProduto tipoProduto = new TipoProduto();
        tipoProduto.setNome(tipoProduto.getNome());

        return tipoProduto;
    }

    public static TipoProdutoDTO toDTO(TipoProduto tipoProduto) {
        TipoProdutoDTO tipoProdutoDTO = new TipoProdutoDTO();
        tipoProdutoDTO.setNome(tipoProduto.getNome());

        return tipoProdutoDTO;
    }
}
