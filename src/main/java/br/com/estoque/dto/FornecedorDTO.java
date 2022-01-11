package br.com.estoque.dto;

import br.com.estoque.model.Fornecedor;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

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

    private String telefone;

    private String email;

    public FornecedorDTO (Fornecedor fornecedor) {
        this.id = fornecedor.getId();
        this.nome = fornecedor.getNome();
        this.cnpj = fornecedor.getCnpj();
        this.endereco = new EnderecoDTO(fornecedor.getEndereco());
        this.telefone = fornecedor.getTelefone();
        this.email = fornecedor.getEmail();
    }

    public static Fornecedor toEntity (FornecedorDTO fornecedorDTO) {
        return Fornecedor
                .builder()
                .nome(fornecedorDTO.getNome())
                .cnpj(fornecedorDTO.getCnpj())
                .endereco(EnderecoDTO.toEntity(fornecedorDTO.getEndereco()))
                .telefone(fornecedorDTO.getTelefone())
                .email(fornecedorDTO.getEmail())
                .build();
    }

    public static List<FornecedorDTO> toListDTO (List<Fornecedor> fornecedores) {
        return fornecedores
                .stream()
                .map(FornecedorDTO::new)
                .collect(Collectors.toList());
    }
}
