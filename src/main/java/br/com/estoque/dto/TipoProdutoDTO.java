package br.com.estoque.dto;

import br.com.estoque.model.TipoProduto;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TipoProdutoDTO {

    @ApiModelProperty(hidden = true)
    private Long id;

    private String nome;

    public static TipoProduto toEntity (TipoProdutoDTO tipoProdutoDTO) {
        TipoProduto tipoProduto = new TipoProduto();
        tipoProduto.setNome(tipoProdutoDTO.getNome());
        tipoProduto.setId(tipoProdutoDTO.getId());

        return tipoProduto;
    }

    public static TipoProdutoDTO toDTO (TipoProduto tipoProduto) {
        TipoProdutoDTO tipoProdutoDTO = new TipoProdutoDTO();
        tipoProdutoDTO.setNome(tipoProduto.getNome());
        tipoProdutoDTO.setId(tipoProduto.getId());

        return tipoProdutoDTO;
    }

    public static List<TipoProdutoDTO> toDTOs (List<TipoProduto> tiposProduto) {
        return tiposProduto
                .stream()
                .map(TipoProdutoDTO::toDTO)
                .collect(Collectors.toList());
    }
}
