package br.com.estoque.controller;

import br.com.estoque.dto.FornecedorDTO;
import br.com.estoque.dto.MessageDTO;
import br.com.estoque.service.FornecedorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@Api(tags = "Fornecedor Controller", produces = "application/json", consumes = "application/json")
@RequestMapping("/api/fornecedores")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class FornecedorController {

    private final FornecedorService fornecedorService;

    @ApiOperation("Criar um fornecedor.")
    @PostMapping
    public ResponseEntity<?> criarFornecedor (@RequestBody FornecedorDTO fornecedorDTO) {
        FornecedorDTO fornecedorCriadoDTO = fornecedorService.criarFornecedor(fornecedorDTO);

        return new ResponseEntity<>(fornecedorCriadoDTO, HttpStatus.CREATED);
    }

    @ApiOperation("Listar todos os fornecedores.")
    @GetMapping
    public ResponseEntity<?> listarFornecedores () {
        List<FornecedorDTO> fornecedoresDTO = fornecedorService.listarFornecedores();

        return new ResponseEntity<>(fornecedoresDTO, HttpStatus.OK);
    }

    @ApiOperation("Retornar um fornecedor pelo seu id.")
    @GetMapping("/{id}")
    public ResponseEntity<?> retornarFornecedor (@PathVariable("id") Long id) {
        FornecedorDTO fornecedorDTO = fornecedorService.getFornecedor(id);

        return new ResponseEntity<>(fornecedorDTO, HttpStatus.OK);
    }

    @ApiOperation("Atualizar um fornecedor pelo seu id.")
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarFornecedor (@PathVariable("id") Long id, @RequestBody FornecedorDTO fornecedorDTO) {
        FornecedorDTO fornecedorAtualizadoDTO = fornecedorService.atualizarFornecedor(id, fornecedorDTO);

        return new ResponseEntity<>(fornecedorAtualizadoDTO, HttpStatus.OK);
    }

    @ApiOperation("Remover um fornecedor pelo seu id.")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removerFornecedores (@PathVariable("id") Long id) {
        fornecedorService.removerFornecedor(id);
        MessageDTO messageDTO = new MessageDTO(String.format("Fornecedor com id %d foi removido com sucesso", id));

        return new ResponseEntity<>(messageDTO, HttpStatus.OK);
    }
}
