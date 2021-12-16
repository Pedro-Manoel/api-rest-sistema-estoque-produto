package br.com.estoque.service;

import br.com.estoque.exception.FornecedorNaoExisteException;
import br.com.estoque.model.Fornecedor;
import br.com.estoque.repository.FornecedorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FornecedorService {

    private FornecedorRepository fornecedorRepository;

    public Fornecedor getFornecedorById(Long id) {
        return fornecedorRepository.findById(id).orElseThrow(() -> new FornecedorNaoExisteException(id));
    }

    public Fornecedor criarFornecedor(Fornecedor fornecedor) {
        return fornecedorRepository.save(fornecedor);
    }

    public void removerFornecedor(Long id) {
        Fornecedor fornecedor = getFornecedorById(id);
        fornecedorRepository.delete(fornecedor);
    }

    public Fornecedor atualizarFornecedor(Long id, Fornecedor fornecedor) {
        Fornecedor novoFornecedor = getFornecedorById(id);
        novoFornecedor.setNome(fornecedor.getNome());
        novoFornecedor.setCnpj(fornecedor.getCnpj());
        novoFornecedor.setEndereco(fornecedor.getEndereco());
        novoFornecedor.setTelefones(fornecedor.getTelefones());
        novoFornecedor.setEmails(fornecedor.getEmails());

        return fornecedorRepository.save(novoFornecedor);
    }

    public List<Fornecedor> listarFornecedores(){
        return fornecedorRepository.findAll();
    }

}
