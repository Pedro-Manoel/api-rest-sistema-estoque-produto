package br.com.estoque.integration;

import br.com.estoque.dto.TipoProdutoDTO;
import br.com.estoque.model.TipoProduto;
import br.com.estoque.repository.TipoProdutoRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TipoProdutoControllerTest {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private TipoProdutoRepository tipoProdutoRepository;

    private TipoProduto tipoProduto;

    private final String URL = "/api/tiposproduto";

    @BeforeAll
    public void iniciar () {
        this.tipoProduto = new TipoProduto();
        tipoProduto.setNome("perecível");
    }

    @Test
    public void testCriarTipoProduto() {
        TipoProduto tipoProdutoTest = new TipoProduto();
        tipoProduto.setNome("industrial");

        HttpEntity<TipoProduto> httpEntity = new HttpEntity<>(tipoProdutoTest);

        ResponseEntity<TipoProdutoDTO> response = this.testRestTemplate
                .exchange(URL, HttpMethod.POST, httpEntity, TipoProdutoDTO.class);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(Objects.requireNonNull(response.getBody()).getNome(), tipoProdutoTest.getNome());
    }

    @Test
    public void testCriarTipoProdutoQueJaExiste() {
        this.tipoProdutoRepository.save(this.tipoProduto);

        TipoProduto tipoProdutoTest = new TipoProduto();
        tipoProdutoTest.setNome(this.tipoProduto.getNome());

        HttpEntity<TipoProduto> httpEntity = new HttpEntity<>(tipoProdutoTest);

        ResponseEntity<TipoProdutoDTO> response = this.testRestTemplate
                .exchange(URL, HttpMethod.POST, httpEntity, TipoProdutoDTO.class);

        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testRetornarTipoProduto() {
        TipoProduto tipoProdutoCriado = this.tipoProdutoRepository.save(this.tipoProduto);

        ResponseEntity<TipoProdutoDTO> response = this.testRestTemplate
                .exchange(URL + "/" + tipoProdutoCriado.getId(), HttpMethod.GET, null, TipoProdutoDTO.class);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(Objects.requireNonNull(response.getBody()).getNome(), tipoProdutoCriado.getNome());
    }

    @Test
    public void testRetornarTiposProduto() {
        ResponseEntity<TipoProdutoDTO[]> response = this.testRestTemplate
                .exchange(URL, HttpMethod.GET, null, TipoProdutoDTO[].class);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testRemoverTipoProduto() {
        TipoProduto tipoProdutoCriado = this.tipoProdutoRepository.save(this.tipoProduto);

        ResponseEntity<Void> response = this.testRestTemplate
                .exchange(URL + "/" + tipoProdutoCriado.getId(), HttpMethod.DELETE, null, Void.class);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testRemoverTipoProdutoQueNaoExiste() {
        ResponseEntity<Void> response = this.testRestTemplate
                .exchange(URL + "/0", HttpMethod.DELETE, null, Void.class);

        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void testAtualizarTipoProduto() {
        TipoProduto tipoProdutoCriado = this.tipoProdutoRepository.save(this.tipoProduto);

        TipoProduto tipoProdutoAtualizado = new TipoProduto();
        tipoProdutoAtualizado.setNome("industrial");

        HttpEntity<TipoProduto> httpEntity = new HttpEntity<>(tipoProdutoAtualizado);

        ResponseEntity<TipoProdutoDTO> response = this.testRestTemplate
                .exchange(URL + "/" + tipoProdutoCriado.getId(), HttpMethod.PUT, httpEntity, TipoProdutoDTO.class);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(Objects.requireNonNull(response.getBody()).getNome(), tipoProdutoAtualizado.getNome());
    }

    @Test
    public void testAtualizarTipoProdutoQueNaoExiste() {
        TipoProduto tipoProdutoAtualizado = new TipoProduto();
        tipoProdutoAtualizado.setNome("industrial");

        HttpEntity<TipoProduto> httpEntity = new HttpEntity<>(tipoProdutoAtualizado);

        ResponseEntity<TipoProdutoDTO> response = this.testRestTemplate
                .exchange(URL + "/0", HttpMethod.PUT, httpEntity, TipoProdutoDTO.class);

        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }

}
