package br.com.estoque.controller;

import br.com.estoque.dto.MessageDTO;
import br.com.estoque.dto.ProdutoDTO;
import br.com.estoque.dto.ProdutoEstoqueDTO;
import br.com.estoque.service.ProdutoService;
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
@Api(tags = "Produto Controller", produces = "application/json", consumes = "application/json")
@RequestMapping("/api/produtos")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ProdutoController {

    private final ProdutoService produtoService;

    @ApiOperation("Criar um produto.")
    @PostMapping
    public ResponseEntity<?> criarProduto (@RequestBody ProdutoDTO produtoDTO) {
        ProdutoDTO produtoCriadoDTO = produtoService.criarProduto(produtoDTO);

        return new ResponseEntity<>(produtoCriadoDTO, HttpStatus.CREATED);
    }

    @ApiOperation("Listar todos os produtos.")
    @GetMapping
    public ResponseEntity<?> listarProdutos () {
        List<ProdutoDTO> produtosDTO = produtoService.listarProdutos();

        return new ResponseEntity<>(produtosDTO, HttpStatus.OK);
    }

    @ApiOperation("Retornar um produto pelo seu id.")
    @GetMapping("/{id}")
    public ResponseEntity<?> retornarProduto (@PathVariable("id") Long id) {
        ProdutoDTO produtoDTO = produtoService.getProduto(id);

        return new ResponseEntity<>(produtoDTO, HttpStatus.OK);
    }

    @ApiOperation("Atualizar um produto pelo seu id.")
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarProduto (@PathVariable("id") Long id, @RequestBody ProdutoDTO produtoDTO){
        ProdutoDTO produtoAtualizadoDTO = produtoService.atualizarProduto(id, produtoDTO);

        return new ResponseEntity<>(produtoAtualizadoDTO, HttpStatus.OK);
    }

    @ApiOperation("Atualizar a quantidade em estoque de um produto pelo seu id.")
    @PatchMapping("/{id}/estoque")
    public ResponseEntity<?> atualizarEstoqueProduto(@PathVariable("id") Long id, @RequestBody ProdutoEstoqueDTO produtoEstoqueDTO){
        ProdutoDTO produtoEstoqueAtualizadoDTO = produtoService.atualizarEstoqueProduto(id, produtoEstoqueDTO);

        return new ResponseEntity<>(produtoEstoqueAtualizadoDTO, HttpStatus.OK);
    }

    @ApiOperation("Remover um produto pelo seu id.")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removerProduto (@PathVariable("id") Long id) {
        produtoService.removerProduto(id);
        MessageDTO messageDTO = new MessageDTO(String.format("Produto com id %d foi removido com sucesso", id));

        return new ResponseEntity<>(messageDTO, HttpStatus.OK);
    }
}
