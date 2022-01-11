package br.com.estoque.service;

import br.com.estoque.dto.FornecedorDTO;
import br.com.estoque.model.Fornecedor;

import java.util.List;

public interface FornecedorService {
    Fornecedor getFornecedorById (Long id);

    FornecedorDTO getFornecedor (Long id);

    FornecedorDTO criarFornecedor (FornecedorDTO fornecedorDTO);

    void removerFornecedor (Long id);

    FornecedorDTO atualizarFornecedor (Long id, FornecedorDTO fornecedorDTOr);

    List<FornecedorDTO> listarFornecedores ();
}
