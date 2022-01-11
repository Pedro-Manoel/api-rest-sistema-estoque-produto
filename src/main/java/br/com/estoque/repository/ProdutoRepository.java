package br.com.estoque.repository;

import br.com.estoque.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    boolean existsByCodBarra (String codBarra);
}
