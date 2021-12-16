package br.com.estoque.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FornecedorResponseDTO {

    private Long id;
    private String nome;
    private String cnpj;
    private EnderecoResponseDTO endereco;
    List<TelefoneResponseDTO> telefones;
    List<EmailResponseDTO> emails;

}
