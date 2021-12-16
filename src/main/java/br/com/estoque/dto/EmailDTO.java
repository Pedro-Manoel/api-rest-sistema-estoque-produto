package br.com.estoque.dto;

import br.com.estoque.model.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmailDTO {
    private String email;

    public static Email toEntity(EmailDTO emailDTO) {
        Email email = new Email();
        email.setEmail(emailDTO.getEmail());

        return email;
    }

    public static EmailDTO toDTO(Email email) {
        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setEmail(email.getEmail());

        return emailDTO;
    }
}
