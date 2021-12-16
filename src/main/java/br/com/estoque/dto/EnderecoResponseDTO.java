package br.com.estoque.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoResponseDTO {

    private CidadeResponseDTO cidade;
    private RuaResponseDTO rua;
    private BairroResponseDTO bairro;
    private String cep;
}
