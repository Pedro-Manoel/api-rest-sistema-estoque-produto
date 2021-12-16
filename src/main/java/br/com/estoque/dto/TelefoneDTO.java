package br.com.estoque.dto;

import br.com.estoque.model.Telefone;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TelefoneDTO {

    private String numero;

    public static Telefone toEntity(TelefoneDTO telefoneDTO) {
        Telefone telefone = new Telefone();
        telefone.setTelefone(telefoneDTO.getNumero());

        return telefone;
    }

    public static TelefoneDTO toDTO(Telefone telefone) {
        TelefoneDTO telefoneDTO = new TelefoneDTO();
        telefone.setTelefone(telefone.getTelefone());

        return telefoneDTO;
    }

}
