package br.com.estoque.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "fornecedor_id")
    Fornecedor fornecedor;

    @OneToOne
    @JoinColumn(name = "tipo_id")
    private TipoProduto tipo;

    @Column(unique = true)
    private String codBarra;

    private String nome;

    private BigDecimal precoVenda;

    private BigDecimal precoCompra;

    private Long quantEstoque;
}
