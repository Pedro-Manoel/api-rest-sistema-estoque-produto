package br.com.estoque.service.implementation;

import br.com.estoque.dto.EnderecoDTO;
import br.com.estoque.dto.FornecedorDTO;
import br.com.estoque.dto.ProdutoDTO;
import br.com.estoque.exception.FornecedorExistenteException;
import br.com.estoque.exception.FornecedorNaoExisteException;
import br.com.estoque.exception.ProdutoExistenteException;
import br.com.estoque.model.Fornecedor;
import br.com.estoque.repository.FornecedorRepository;
import br.com.estoque.service.FornecedorService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class FornecedorServiceImpl implements FornecedorService {

    private FornecedorRepository fornecedorRepository;

    public Fornecedor getFornecedorById (Long id) {
        return fornecedorRepository.findById(id).orElseThrow(() -> new FornecedorNaoExisteException(id));
    }

    public FornecedorDTO getFornecedor (Long id) {
        return new FornecedorDTO(getFornecedorById(id));
    }

    @Transactional
    public FornecedorDTO criarFornecedor (FornecedorDTO fornecedorDTO) {
        verificarFornecedorExiste(fornecedorDTO);

        Fornecedor fornecedor = FornecedorDTO.toEntity(fornecedorDTO);

        return new FornecedorDTO(fornecedorRepository.save(fornecedor));
    }

    public void removerFornecedor (Long id) {
        Fornecedor fornecedor = getFornecedorById(id);

        fornecedorRepository.delete(fornecedor);
    }

    @Transactional
    public FornecedorDTO atualizarFornecedor (Long id, FornecedorDTO fornecedorDTO) {
        Fornecedor fornecedor = getFornecedorById(id);
        if (!fornecedor.getCnpj().equals(fornecedorDTO.getCnpj())) {
            verificarFornecedorExiste(fornecedorDTO);
        }

        fornecedor.setNome(fornecedorDTO.getNome());
        fornecedor.setCnpj(fornecedorDTO.getCnpj());
        fornecedor.setEndereco(EnderecoDTO.toEntity(fornecedorDTO.getEndereco()));
        fornecedor.setTelefone(fornecedorDTO.getTelefone());
        fornecedor.setEmail(fornecedorDTO.getEmail());

        return new FornecedorDTO(fornecedorRepository.save(fornecedor));
    }

    public List<FornecedorDTO> listarFornecedores (){
        return FornecedorDTO.toListDTO(fornecedorRepository.findAll());
    }

    private void verificarFornecedorExiste (FornecedorDTO fornecedorDTO) {
        if (fornecedorRepository.existsByCnpj(fornecedorDTO.getCnpj())) {
            throw new FornecedorExistenteException(fornecedorDTO.getCnpj());
        }
    }
}
