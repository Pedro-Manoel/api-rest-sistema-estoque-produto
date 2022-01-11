package br.com.estoque.integration;

import br.com.estoque.dto.FornecedorDTO;
import br.com.estoque.model.Endereco;
import br.com.estoque.model.Fornecedor;
import br.com.estoque.repository.FornecedorRepository;
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
public class FornecedorControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private FornecedorRepository fornecedorRepository;

    private Fornecedor fornecedor;

    private final String URL = "/api/fornecedores";

    @BeforeAll
    public void iniciar () {
        fornecedor = Fornecedor
                .builder()
                .cnpj("123")
                .endereco(new Endereco())
                .build();
    }

    @AfterEach
    public void finalizar () {
        fornecedorRepository.deleteAll();
    }

    @Test
    public void testCriarFornecedor() {
        Fornecedor fornecedorTest = Fornecedor
                .builder()
                .cnpj("339")
                .endereco(new Endereco())
                .build();

        HttpEntity<Fornecedor> httpEntity = new HttpEntity<>(fornecedorTest);

        ResponseEntity<FornecedorDTO> response = testRestTemplate
                .exchange(URL, HttpMethod.POST, httpEntity, FornecedorDTO.class);

        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
        assertEquals(Objects.requireNonNull(response.getBody()).getCnpj(), fornecedorTest.getCnpj());
    }

    @Test
    public void testCriarFornecedorQueJaExiste() {
        fornecedorRepository.save(fornecedor);

        Fornecedor fornecedorTest = Fornecedor
                .builder()
                .cnpj(fornecedor.getCnpj())
                .endereco(new Endereco())
                .build();

        HttpEntity<Fornecedor> httpEntity = new HttpEntity<>(fornecedorTest);

        ResponseEntity<FornecedorDTO> response = testRestTemplate
                .exchange(URL, HttpMethod.POST, httpEntity, FornecedorDTO.class);

        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testRetornarFornecedor() {
        Fornecedor fornecedorCriado = fornecedorRepository.save(fornecedor);

        ResponseEntity<FornecedorDTO> response = testRestTemplate
                .exchange(URL + "/" + fornecedorCriado.getId(), HttpMethod.GET, null, FornecedorDTO.class);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(Objects.requireNonNull(response.getBody()).getCnpj(), fornecedorCriado.getCnpj());
    }

    @Test
    public void testRetornarFornecedores() {
        ResponseEntity<FornecedorDTO[]> response = testRestTemplate
                .exchange(URL, HttpMethod.GET, null, FornecedorDTO[].class);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testRemoverFornecedor() {
        Fornecedor fornecedorCriado = fornecedorRepository.save(fornecedor);

        ResponseEntity<Void> response = testRestTemplate
                .exchange(URL + "/" + fornecedorCriado.getId(), HttpMethod.DELETE, null, Void.class);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testRemoverFornecedorQueNaoExiste() {
        ResponseEntity<Void> response = testRestTemplate
                .exchange(URL + "/0", HttpMethod.DELETE, null, Void.class);

        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void testAtualizarFornecedor() {
        Fornecedor fornecedorCriado = fornecedorRepository.save(fornecedor);

        Fornecedor fornecedorAtualizado = Fornecedor
                .builder()
                .cnpj("111")
                .endereco(new Endereco())
                .build();

        HttpEntity<Fornecedor> httpEntity = new HttpEntity<>(fornecedorAtualizado);

        ResponseEntity<FornecedorDTO> response = testRestTemplate
                .exchange(URL + "/" + fornecedorCriado.getId(), HttpMethod.PUT, httpEntity, FornecedorDTO.class);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(Objects.requireNonNull(response.getBody()).getCnpj(), fornecedorAtualizado.getCnpj());
    }

    @Test
    public void testAtualizarCpnjQueJaExiste() {
        Fornecedor fornecedorCriado = fornecedorRepository.save(fornecedor);
        Fornecedor fornecedor2 = Fornecedor
                .builder()
                .cnpj("121")
                .endereco(new Endereco())
                .build();

        fornecedorRepository.save(fornecedor2);

        Fornecedor fornecedorAtualizado = Fornecedor
                .builder()
                .cnpj(fornecedor2.getCnpj())
                .endereco(new Endereco())
                .build();

        HttpEntity<Fornecedor> httpEntity = new HttpEntity<>(fornecedorAtualizado);

        ResponseEntity<FornecedorDTO> response = testRestTemplate
                .exchange(URL + "/" + fornecedorCriado.getId(), HttpMethod.PUT, httpEntity, FornecedorDTO.class);

        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testAtualizarFornecedorQueNaoExiste() {
        Fornecedor fornecedorAtualizado = Fornecedor
                .builder()
                .cnpj("810")
                .build();

        HttpEntity<Fornecedor> httpEntity = new HttpEntity<>(fornecedorAtualizado);

        ResponseEntity<FornecedorDTO> response = testRestTemplate
                .exchange(URL + "/0", HttpMethod.PUT, httpEntity, FornecedorDTO.class);

        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }
}
