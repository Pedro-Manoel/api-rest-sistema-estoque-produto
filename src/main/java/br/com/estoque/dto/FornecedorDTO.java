package br.com.estoque.dto;

import br.com.estoque.model.Email;
import br.com.estoque.model.Fornecedor;
import br.com.estoque.model.Telefone;
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
public class FornecedorDTO {

    @ApiModelProperty(hidden = true)
    private Long id;

    private String nome;
    private String cnpj;
    private EnderecoDTO endereco;
    List<String> telefones;
    List<String> emails;

    public static Fornecedor toEntity(FornecedorDTO fornecedorDTO) {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setId(fornecedorDTO.getId());
        fornecedor.setNome(fornecedorDTO.getNome());
        fornecedor.setCnpj(fornecedorDTO.getCnpj());
        fornecedor.setEndereco(EnderecoDTO.toEntity(fornecedorDTO.getEndereco()));
        fornecedor.setEmails(fornecedorDTO.getEmails()
                .stream().map(Email::new).collect(Collectors.toList()));
        fornecedor.setTelefones(fornecedorDTO.getTelefones()
                .stream().map(Telefone::new).collect(Collectors.toList()));

        return fornecedor;
    }

    public static FornecedorDTO toDTO(Fornecedor fornecedor) {
        FornecedorDTO fornecedorDTO = new FornecedorDTO();
        fornecedorDTO.setId(fornecedor.getId());
        fornecedorDTO.setNome(fornecedor.getNome());
        fornecedorDTO.setCnpj(fornecedor.getCnpj());
        fornecedorDTO.setEndereco(EnderecoDTO.toDTO(fornecedor.getEndereco()));
        fornecedorDTO.setEmails(fornecedor.getEmails()
                .stream().map(Email::getEmail).collect(Collectors.toList()));
        fornecedorDTO.setTelefones(fornecedor.getTelefones()
                .stream().map(Telefone::getTelefone).collect(Collectors.toList()));

        return fornecedorDTO;
    }

    public static List<FornecedorDTO> toDTOs(List<Fornecedor> fornecedores) {
        return fornecedores
                .stream()
                .map(FornecedorDTO::toDTO)
                .collect(Collectors.toList());
    }

}
