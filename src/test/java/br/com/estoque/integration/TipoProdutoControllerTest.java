package br.com.estoque.integration;

import br.com.estoque.dto.TipoProdutoDTO;
import br.com.estoque.model.TipoProduto;
import br.com.estoque.repository.TipoProdutoRepository;
import org.junit.jupiter.api.*;
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
        tipoProduto = new TipoProduto();
        tipoProduto.setNome("Perecível");
    }

    @AfterEach
    public void finalizar () {
        tipoProdutoRepository.deleteAll();
    }

    @Test
    public void testCriarTipoProduto() {
        TipoProduto tipoProdutoTest = new TipoProduto();
        tipoProdutoTest.setNome("Industrial");

        HttpEntity<TipoProduto> httpEntity = new HttpEntity<>(tipoProdutoTest);

        ResponseEntity<TipoProdutoDTO> response = testRestTemplate
                .exchange(URL, HttpMethod.POST, httpEntity, TipoProdutoDTO.class);

        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
        assertEquals(Objects.requireNonNull(response.getBody()).getNome(), tipoProdutoTest.getNome());
    }

    @Test
    public void testCriarTipoProdutoQueJaExiste() {
        tipoProdutoRepository.save(tipoProduto);

        TipoProduto tipoProdutoTest = new TipoProduto();
        tipoProdutoTest.setNome(tipoProduto.getNome());

        HttpEntity<TipoProduto> httpEntity = new HttpEntity<>(tipoProdutoTest);

        ResponseEntity<TipoProdutoDTO> response = testRestTemplate
                .exchange(URL, HttpMethod.POST, httpEntity, TipoProdutoDTO.class);

        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testRetornarTipoProduto() {
        TipoProduto tipoProdutoCriado = tipoProdutoRepository.save(tipoProduto);

        ResponseEntity<TipoProdutoDTO> response = testRestTemplate
                .exchange(URL + "/" + tipoProdutoCriado.getId(), HttpMethod.GET, null, TipoProdutoDTO.class);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(Objects.requireNonNull(response.getBody()).getNome(), tipoProdutoCriado.getNome());
    }

    @Test
    public void testRetornarTiposProduto() {
        ResponseEntity<TipoProdutoDTO[]> response = testRestTemplate
                .exchange(URL, HttpMethod.GET, null, TipoProdutoDTO[].class);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testRemoverTipoProduto() {
        TipoProduto tipoProdutoCriado = tipoProdutoRepository.save(tipoProduto);

        ResponseEntity<Void> response = testRestTemplate
                .exchange(URL + "/" + tipoProdutoCriado.getId(), HttpMethod.DELETE, null, Void.class);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testRemoverTipoProdutoQueNaoExiste() {
        ResponseEntity<Void> response = testRestTemplate
                .exchange(URL + "/0", HttpMethod.DELETE, null, Void.class);

        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void testAtualizarTipoProduto() {
        TipoProduto tipoProdutoCriado = tipoProdutoRepository.save(tipoProduto);

        TipoProduto tipoProdutoAtualizado = new TipoProduto();
        tipoProdutoAtualizado.setNome("Durável");

        HttpEntity<TipoProduto> httpEntity = new HttpEntity<>(tipoProdutoAtualizado);

        ResponseEntity<TipoProdutoDTO> response = testRestTemplate
                .exchange(URL + "/" + tipoProdutoCriado.getId(), HttpMethod.PUT, httpEntity, TipoProdutoDTO.class);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(Objects.requireNonNull(response.getBody()).getNome(), tipoProdutoAtualizado.getNome());
    }

    @Test
    public void testAtualizarNomeQueJaExiste() {
        TipoProduto tipoProdutoCriado = tipoProdutoRepository.save(tipoProduto);
        TipoProduto tipoProduto1 = new TipoProduto();
        tipoProduto1.setNome("Não Durável");
        tipoProdutoRepository.save(tipoProduto1);

        TipoProduto tipoProdutoAtualizado = new TipoProduto();
        tipoProdutoAtualizado.setNome(tipoProduto1.getNome());

        HttpEntity<TipoProduto> httpEntity = new HttpEntity<>(tipoProdutoAtualizado);

        ResponseEntity<TipoProdutoDTO> response = testRestTemplate
                .exchange(URL + "/" + tipoProdutoCriado.getId(), HttpMethod.PUT, httpEntity, TipoProdutoDTO.class);

        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testAtualizarTipoProdutoQueNaoExiste() {
        TipoProduto tipoProdutoAtualizado = new TipoProduto();
        tipoProdutoAtualizado.setNome("Consumo");

        HttpEntity<TipoProduto> httpEntity = new HttpEntity<>(tipoProdutoAtualizado);

        ResponseEntity<TipoProdutoDTO> response = testRestTemplate
                .exchange(URL + "/0", HttpMethod.PUT, httpEntity, TipoProdutoDTO.class);

        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }
}
