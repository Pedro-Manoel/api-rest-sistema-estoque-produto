package br.com.estoque.controller;

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
    public ResponseEntity<?> criarProduto(@RequestBody ProdutoRequestDTO produtoRequestDTO) {

        Fornecedor fornecedor = fornecedorService.getFornecedorById(produtoRequestDTO.getFornecedorId());
        TipoProduto tipoProduto = tipoProdutoService.getTipoProdutoById(produtoRequestDTO.getTipoId());

        Produto produto = new Produto(produtoRequestDTO.getTipoId(), produtoRequestDTO.getNome(), produtoRequestDTO.getPrecoVenda(),
                produtoRequestDTO.getPrecoCompra(), produtoRequestDTO.getQttEstoque(), fornecedor, tipoProduto);

        Produto produtoCriado = produtoService.criarProduto(produto);

        return new ResponseEntity<Produto>(produtoCriado, HttpStatus.OK);
    }


}
