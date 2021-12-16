package br.com.estoque.controller;

import br.com.estoque.dto.ProdutoDTO;
import br.com.estoque.model.Fornecedor;
import br.com.estoque.model.Produto;
import br.com.estoque.model.TipoProduto;
import br.com.estoque.service.FornecedorService;
import br.com.estoque.service.ProdutoService;
import br.com.estoque.service.TipoProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    FornecedorService fornecedorService;

    @Autowired
    TipoProdutoService tipoProdutoService;

    @Autowired
    ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<?> criarProduto (@RequestBody ProdutoDTO produtoDTO) {
        Fornecedor fornecedor = fornecedorService.getFornecedorById(produtoDTO.getFornecedorId());
        TipoProduto tipoProduto = tipoProdutoService.getTipoProdutoById(produtoDTO.getTipoProdutoId());

        Produto produto = ProdutoDTO.toEntity(produtoDTO);
        produto.setFornecedor(fornecedor);
        produto.setTipo(tipoProduto);

        ProdutoDTO produtoCriado = ProdutoDTO.toDTO(produtoService.criarProduto(produto));

        return new ResponseEntity<>(produtoCriado, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> listarProdutos () {
        List<Produto> produtos = produtoService.listarProdutos();

        return new ResponseEntity<>(produtos, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removerProduto (@PathVariable("id") Long id) {
        produtoService.removerProduto(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarProduto(@PathVariable("id") Long id, @RequestBody ProdutoDTO produtoDTO){
        Fornecedor fornecedor = fornecedorService.getFornecedorById(produtoDTO.getFornecedorId());
        TipoProduto tipoProduto = tipoProdutoService.getTipoProdutoById(produtoDTO.getTipoProdutoId());

        Produto produto = ProdutoDTO.toEntity(produtoDTO);
        produto.setFornecedor(fornecedor);
        produto.setTipo(tipoProduto);

        Produto produtoAtualizado = produtoService.atualizarProduto(id, produto);

        return new ResponseEntity<>(produtoAtualizado, HttpStatus.OK);
    }

}
