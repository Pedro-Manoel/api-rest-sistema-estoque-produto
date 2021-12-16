package br.com.estoque.repository;
import br.com.estoque.model.TipoProduto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoProdutoRepository extends JpaRepository<TipoProduto, String> {
}
