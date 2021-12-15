package br.com.estoque.model;

import io.swagger.models.properties.EmailProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Fornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cnpj;

    @OneToOne
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    @OneToMany
    @JoinColumn(name = "fornecedor_id")
    List<Telefone> telefones;

    @OneToMany
    @JoinColumn(name = "fornecedor_id")
    List<Email> emails;
}
