package br.com.estoque.controller;

import br.com.estoque.model.Fornecedor;
import br.com.estoque.service.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/fornecedores")
public class FornecedorController {

    @Autowired
    FornecedorService fornecedorService;

    @GetMapping
    public ResponseEntity<?> listarFornecedores(){
        List<Fornecedor> listaFornecedores = fornecedorService.listarFornecedores();

        return new ResponseEntity<List<Fornecedor>>(listaFornecedores, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removerFornecedores(@PathVariable("id") Long id) {
        fornecedorService.removerFornecedor(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<?> atualizarFornecedor(@PathVariable ("id") Long id,
//                                                 @RequestBody FornecedortDTO fornecedorRequestDTO){
//        Fornecedor fornecedor = fornecedorService.criarFornecedor(fornecedorRequestDTO.get);
//        TipoProduto novoTipoProduto = tipoProdutoService.atualizarTipoProduto(id, tipoProduto);
//
//        return new ResponseEntity<TipoProduto>(novoTipoProduto, HttpStatus.OK);
//    }

}
