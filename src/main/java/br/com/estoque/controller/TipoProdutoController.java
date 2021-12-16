package br.com.estoque.controller;

import br.com.estoque.dto.TipoProdutoRequestDTO;
import br.com.estoque.model.TipoProduto;
import br.com.estoque.service.TipoProdutoService;
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
        List<TipoProduto> listaTiposProdutos = tipoProdutoService.listarTiposProduto();

        return new ResponseEntity<List<TipoProduto>>(listaTiposProdutos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> criarTipoProduto(@RequestBody TipoProdutoRequestDTO tipoProdutoRequestDTO) {
        TipoProduto tipoProduto = tipoProdutoService.criarTipoProduto(tipoProdutoRequestDTO.getNome());

        return new ResponseEntity<TipoProduto>(tipoProduto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removerTipoProduto(@PathVariable("id") Long id) {
        tipoProdutoService.removerTipoProduto(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarTipoProduto(@PathVariable ("id") Long id,
                                                  @RequestBody TipoProdutoRequestDTO tipoProdutoRequestDTO){
        TipoProduto tipoProduto = tipoProdutoService.criarTipoProduto(tipoProdutoRequestDTO.getNome());
        TipoProduto novoTipoProduto = tipoProdutoService.atualizarTipoProduto(id, tipoProduto);

        return new ResponseEntity<TipoProduto>(novoTipoProduto, HttpStatus.OK);
    }
}
