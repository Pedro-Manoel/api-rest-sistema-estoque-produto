package br.com.estoque.controller;

import br.com.estoque.dto.MessageDTO;
import br.com.estoque.dto.TipoProdutoDTO;
import br.com.estoque.service.TipoProdutoService;
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
@Api(tags = "TipoProduto Controller", produces = "application/json", consumes = "application/json")
@RequestMapping("/api/tiposproduto")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class TipoProdutoController {

    private final TipoProdutoService tipoProdutoService;

    @ApiOperation("Criar um tipo de produto.")
    @PostMapping
    public ResponseEntity<?> criarTipoProduto (@RequestBody TipoProdutoDTO tipoProdutoDTO) {
        TipoProdutoDTO tipoProdutoCriadoDTO = tipoProdutoService.criarTipoProduto(tipoProdutoDTO);

        return new ResponseEntity<>(tipoProdutoCriadoDTO, HttpStatus.CREATED);
    }

    @ApiOperation("Listar todos os tipos de produtos.")
    @GetMapping
    public ResponseEntity<?> listarTiposProdutos (){
        List<TipoProdutoDTO> tiposProdutoDTO = tipoProdutoService.listarTiposProduto();

        return new ResponseEntity<>(tiposProdutoDTO, HttpStatus.OK);
    }

    @ApiOperation("Retornar um tipo de produto pelo seu id.")
    @GetMapping("/{id}")
    public ResponseEntity<?> retornarTipoProduto (@PathVariable("id") Long id){
        TipoProdutoDTO tipoProdutoDTO = tipoProdutoService.getTipoProduto(id);

        return new ResponseEntity<>(tipoProdutoDTO, HttpStatus.OK);
    }

    @ApiOperation("Atualizar um tipo de produto pelo seu id.")
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarTipoProduto(@PathVariable ("id") Long id, @RequestBody TipoProdutoDTO tipoProdutoDTO){
        TipoProdutoDTO tipoProdutoAtualizadoDTO = tipoProdutoService.atualizarTipoProduto(id, tipoProdutoDTO);

        return new ResponseEntity<>(tipoProdutoAtualizadoDTO, HttpStatus.OK);
    }

    @ApiOperation("Remover um tipo de produto pelo seu id.")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removerTipoProduto(@PathVariable ("id") Long id) {
        tipoProdutoService.removerTipoProduto(id);
        MessageDTO messageDTO = new MessageDTO(String.format("Tipo de produto com id %d foi removido com sucesso", id));

        return new ResponseEntity<>(messageDTO, HttpStatus.OK);
    }
}
