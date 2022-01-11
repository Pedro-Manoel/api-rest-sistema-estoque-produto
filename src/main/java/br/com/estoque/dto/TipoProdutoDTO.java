package br.com.estoque.dto;

import br.com.estoque.model.TipoProduto;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

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

    public TipoProdutoDTO (TipoProduto tipoProduto) {
        this.id = tipoProduto.getId();
        this.nome = tipoProduto.getNome();
    }

    public static TipoProduto toEntity (TipoProdutoDTO tipoProdutoDTO) {
        return TipoProduto
                .builder()
                .nome(tipoProdutoDTO.getNome())
                .build();
    }

    public static List<TipoProdutoDTO> toListDTO (List<TipoProduto> tiposProduto) {
        return tiposProduto
                .stream()
                .map(TipoProdutoDTO::new)
                .collect(Collectors.toList());
    }
}
