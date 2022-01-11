package br.com.estoque.dto;

import br.com.estoque.model.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoDTO {

    private String cep;

    private Long numero;

    private String rua;

    private String bairro;

    private String cidade;

    private String uf;

    public EnderecoDTO (Endereco endereco) {
        this.cep = endereco.getCep();
        this.cidade = endereco.getCidade();
        this.rua = endereco.getRua();
        this.bairro = endereco.getBairro();
        this.uf = endereco.getUf();
    }

    public static Endereco toEntity (EnderecoDTO enderecoDTO) {
        return Endereco
                .builder()
                .cep(enderecoDTO.getCep())
                .cidade(enderecoDTO.getCidade())
                .rua(enderecoDTO.getRua())
                .bairro(enderecoDTO.getBairro())
                .uf(enderecoDTO.getUf())
                .build();
    }
}
