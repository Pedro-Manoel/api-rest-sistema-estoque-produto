package br.com.estoque.dto;

import br.com.estoque.model.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoDTO {

    private String cidade;
    private String rua;
    private String bairro;
    private String cep;
    private String uf;


    public static Endereco toEntity(EnderecoDTO enderecoDTO) {
       Endereco endereco = new Endereco();
       endereco.setCep(enderecoDTO.getCep());
       endereco.setCidade(enderecoDTO.getCidade());
       endereco.setRua(enderecoDTO.getRua());
       endereco.setBairro(enderecoDTO.getBairro());
       endereco.setUf(enderecoDTO.getUf());

       return endereco;
    }

    public static EnderecoDTO toDTO(Endereco endereco) {
        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setCep(endereco.getCep());
        enderecoDTO.setCidade(endereco.getCidade());
        enderecoDTO.setRua(endereco.getRua());
        enderecoDTO.setBairro(endereco.getBairro());
        enderecoDTO.setUf(endereco.getUf());

        return enderecoDTO;
    }



}
