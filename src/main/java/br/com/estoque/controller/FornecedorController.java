package br.com.estoque.controller;

import br.com.estoque.dto.FornecedorDTO;
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
        List<FornecedorDTO> fornecedoresDTO = FornecedorDTO.toDTOs(fornecedorService.listarFornecedores());

        return new ResponseEntity<>(fornecedoresDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> retornarFornecedor(@PathVariable("id") Long id){
        FornecedorDTO fornecedorDTO = FornecedorDTO.toDTO(fornecedorService.getFornecedorById(id));

        return new ResponseEntity<>(fornecedorDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> criarFornecedor(@RequestBody FornecedorDTO fornecedorDTO){
        Fornecedor fornecedor = FornecedorDTO.toEntity(fornecedorDTO);
        FornecedorDTO fornecedorCriadoDTO = FornecedorDTO.toDTO(fornecedorService.criarFornecedor(fornecedor));

        return new ResponseEntity<>(fornecedorCriadoDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removerFornecedores(@PathVariable("id") Long id) {
        fornecedorService.removerFornecedor(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarFornecedor(@PathVariable("id") Long id, @RequestBody FornecedorDTO fornecedorDTO){
        Fornecedor fornecedor = FornecedorDTO.toEntity(fornecedorDTO);
        FornecedorDTO fornecedorAtualizadoDTO = FornecedorDTO.toDTO(fornecedorService.atualizarFornecedor(id, fornecedor));

        return new ResponseEntity<>(fornecedorAtualizadoDTO, HttpStatus.OK);
    }



}
