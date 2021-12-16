package br.com.estoque.controller;

import br.com.estoque.dto.TipoProdutoDTO;
import br.com.estoque.model.TipoProduto;
import br.com.estoque.service.TipoProdutoService;
import br.com.estoque.service.implementation.TipoProdutoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/tiposproduto")
public class TipoProdutoController {

    @Autowired
    TipoProdutoService tipoProdutoService;

    @GetMapping
    public ResponseEntity<?> listarTiposProdutos(){
        List<TipoProdutoDTO> tiposProdutoDTO = TipoProdutoDTO.toDTOs(tipoProdutoService.listarTiposProduto());


        return new ResponseEntity<>(tiposProdutoDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> retornarTipoProduto(@PathVariable("id") Long id){
        TipoProdutoDTO tipoProdutoDTO = TipoProdutoDTO.toDTO(tipoProdutoService.getTipoProdutoById(id));

        return new ResponseEntity<>(tipoProdutoDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> criarTipoProduto(@RequestBody TipoProdutoDTO tipoProdutoDTO) {
        TipoProduto tipoProduto = TipoProdutoDTO.toEntity(tipoProdutoDTO);
        TipoProdutoDTO tipoProdutoCriadoDTO = TipoProdutoDTO.toDTO(tipoProdutoService.criarTipoProduto(tipoProduto));

        return new ResponseEntity<>(tipoProdutoCriadoDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removerTipoProduto(@PathVariable ("id") Long id) {
        tipoProdutoService.removerTipoProduto(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarTipoProduto(@PathVariable ("id") Long id, @RequestBody TipoProdutoDTO tipoProdutoDTO){
        TipoProduto tipoProduto = TipoProdutoDTO.toEntity(tipoProdutoDTO);
        TipoProdutoDTO tipoProdutoAtualizadoDTO = TipoProdutoDTO.toDTO(tipoProdutoService.atualizarTipoProduto(id, tipoProduto));

        return new ResponseEntity<>(tipoProdutoAtualizadoDTO, HttpStatus.OK);
    }
}
