package br.com.estoque.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cep;

    @OneToOne
    @JoinColumn(name = "rua_id")
    private Rua rua;

    @OneToOne
    @JoinColumn(name = "bairro_id")
    private Bairro bairro;

    @OneToOne
    @JoinColumn(name = "cidade_id")
    private Cidade cidade;
}
