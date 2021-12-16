package br.com.estoque.controller;

import br.com.estoque.dto.FornecedorDTO;
import br.com.estoque.dto.ProdutoDTO;
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

        return new ResponseEntity<>(listaFornecedores, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> criarFornecedor(@RequestBody FornecedorDTO fornecedorDTO){
        Fornecedor fornecedor = FornecedorDTO.toEntity(fornecedorDTO);
        Fornecedor fornecedorCriado = fornecedorService.criarFornecedor(fornecedor);

        return new ResponseEntity<>(fornecedorCriado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removerFornecedores(@PathVariable("id") Long id) {
        fornecedorService.removerFornecedor(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarFornecedor(@PathVariable("id") Long id, @RequestBody FornecedorDTO fornecedorDTO){
        Fornecedor fornecedor = FornecedorDTO.toEntity(fornecedorDTO);
        Fornecedor fornecedorAtualizado = fornecedorService.atualizarFornecedor(id, fornecedor);

        return new ResponseEntity<>(fornecedorAtualizado, HttpStatus.OK);
    }



}
