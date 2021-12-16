package br.com.estoque.dto;

import br.com.estoque.model.Fornecedor;
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

    private Long id;
    private String nome;
    private String cnpj;
    private EnderecoDTO endereco;
    List<TelefoneDTO> telefones;
    List<EmailDTO> emails;

    public static Fornecedor toEntity(FornecedorDTO fornecedorDTO) {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setNome(fornecedorDTO.getNome());
        fornecedor.setCnpj(fornecedorDTO.getCnpj());
        fornecedor.setEndereco(EnderecoDTO.toEntity(fornecedorDTO.getEndereco()));
        fornecedor.setEmails(fornecedorDTO.getEmails()
                .stream().map(EmailDTO::toEntity).collect(Collectors.toList()));
        fornecedor.setTelefones(fornecedorDTO.getTelefones()
                .stream().map(TelefoneDTO::toEntity).collect(Collectors.toList()));

        return fornecedor;
    }

    public static FornecedorDTO toDTO(Fornecedor fornecedor) {
        FornecedorDTO fornecedorDTO = new FornecedorDTO();
        fornecedorDTO.setNome(fornecedor.getNome());
        fornecedorDTO.setCnpj(fornecedor.getCnpj());
        fornecedorDTO.setEndereco(EnderecoDTO.toDTO(fornecedor.getEndereco()));
        fornecedorDTO.setEmails(fornecedor.getEmails()
                .stream().map(EmailDTO::toDTO).collect(Collectors.toList()));
        fornecedorDTO.setTelefones(fornecedor.getTelefones()
                .stream().map(TelefoneDTO::toDTO).collect(Collectors.toList()));

        return fornecedorDTO;
    }

}
