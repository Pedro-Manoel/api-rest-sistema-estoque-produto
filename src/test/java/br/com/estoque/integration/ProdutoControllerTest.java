package br.com.estoque.integration;

import br.com.estoque.dto.ProdutoDTO;
import br.com.estoque.dto.ProdutoEstoqueDTO;
import br.com.estoque.model.Fornecedor;
import br.com.estoque.model.Produto;
import br.com.estoque.model.TipoProduto;
import br.com.estoque.repository.FornecedorRepository;
import br.com.estoque.repository.ProdutoRepository;
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
public class ProdutoControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private TipoProdutoRepository tipoProdutoRepository;

    @Autowired
    private FornecedorRepository fornecedorRepository;

    private Produto produto;
    private TipoProduto tipoProduto;
    private Fornecedor fornecedor;

    private final String URL = "/api/produtos";

    @BeforeAll
    public void iniciar () {
        tipoProduto = tipoProdutoRepository.save(TipoProduto.builder().nome("bem de conveniência").build());
        fornecedor = fornecedorRepository.save(Fornecedor.builder().cnpj("860").build());
        produto = Produto
                .builder()
                .codBarra("AB12")
                .tipo(tipoProduto)
                .fornecedor(fornecedor)
                .quantEstoque(5L)
                .build();
    }

    @AfterEach
    public void finalizar () {
        produtoRepository.deleteAll();
    }

    @Test
    public void testCriarProduto() {
        Produto produtoTest = Produto
                .builder()
                .codBarra("FN41")
                .tipo(tipoProduto)
                .fornecedor(fornecedor)
                .build();

        HttpEntity<ProdutoDTO> httpEntity = new HttpEntity<>(new ProdutoDTO(produtoTest));

        ResponseEntity<ProdutoDTO> response = testRestTemplate
                .exchange(URL, HttpMethod.POST, httpEntity, ProdutoDTO.class);

        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
        assertEquals(Objects.requireNonNull(response.getBody()).getCodBarra(), produtoTest.getCodBarra());
    }

    @Test
    public void testCriarProdutoQueJaExiste() {
        produtoRepository.save(produto);

        Produto produtoTest = Produto
                .builder()
                .codBarra(produto.getCodBarra())
                .tipo(tipoProduto)
                .fornecedor(fornecedor)
                .build();

        HttpEntity<Produto> httpEntity = new HttpEntity<>(produtoTest);

        ResponseEntity<ProdutoDTO> response = testRestTemplate
                .exchange(URL, HttpMethod.POST, httpEntity, ProdutoDTO.class);

        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testRetornarProduto() {
        Produto produtoCriado = produtoRepository.save(produto);

        ResponseEntity<ProdutoDTO> response = testRestTemplate
                .exchange(URL + "/" + produtoCriado.getId(), HttpMethod.GET, null, ProdutoDTO.class);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(Objects.requireNonNull(response.getBody()).getCodBarra(), produtoCriado.getCodBarra());
    }

    @Test
    public void testRetornarProdutos() {
        ResponseEntity<ProdutoDTO[]> response = testRestTemplate
                .exchange(URL, HttpMethod.GET, null, ProdutoDTO[].class);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testRemoverProduto() {
        Produto produtoCriado = produtoRepository.save(produto);

        ResponseEntity<Void> response = testRestTemplate
                .exchange(URL + "/" + produtoCriado.getId(), HttpMethod.DELETE, null, Void.class);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testRemoverProdutoQueNaoExiste() {
        ResponseEntity<Void> response = testRestTemplate
                .exchange(URL + "/0", HttpMethod.DELETE, null, Void.class);

        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void testAtualizarProduto() {
        Produto produtoCriado = produtoRepository.save(produto);

        Produto produtoTest = Produto
                .builder()
                .codBarra("PO55")
                .tipo(tipoProduto)
                .fornecedor(fornecedor)
                .build();

        HttpEntity<ProdutoDTO> httpEntity = new HttpEntity<>(new ProdutoDTO(produtoTest));

        ResponseEntity<ProdutoDTO> response = testRestTemplate
                .exchange(URL + "/" + produtoCriado.getId(), HttpMethod.PUT, httpEntity, ProdutoDTO.class);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(Objects.requireNonNull(response.getBody()).getCodBarra(), produtoTest.getCodBarra());
    }

    @Test
    public void testAtualizarCodBarraQueJaExiste() {
        Produto produtoCriado = produtoRepository.save(produto);
        Produto produto1 = Produto
                .builder()
                .codBarra("TN11")
                .tipo(tipoProduto)
                .fornecedor(fornecedor)
                .build();
        produtoRepository.save(produto1);

        Produto produtoTest = Produto
                .builder()
                .codBarra(produto1.getCodBarra())
                .tipo(tipoProduto)
                .fornecedor(fornecedor)
                .build();

        HttpEntity<ProdutoDTO> httpEntity = new HttpEntity<>(new ProdutoDTO(produtoTest));

        ResponseEntity<ProdutoDTO> response = testRestTemplate
                .exchange(URL + "/" + produtoCriado.getId(), HttpMethod.PUT, httpEntity, ProdutoDTO.class);

        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testAtualizarEstoqueProduto() {
        Produto produtoCriado = produtoRepository.save(produto);
        Long novoEstoque = 8L;

        HttpEntity<ProdutoEstoqueDTO> httpEntity = new HttpEntity<>(new ProdutoEstoqueDTO(novoEstoque));

        ResponseEntity<ProdutoDTO> response = testRestTemplate
                .exchange(URL + "/" + produtoCriado.getId() + "/estoque", HttpMethod.PATCH, httpEntity, ProdutoDTO.class);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(Objects.requireNonNull(response.getBody()).getQuantEstoque(), novoEstoque);
    }

    @Test
    public void testAtualizarProdutoQueNaoExiste() {
        Produto produtoTest = Produto
                .builder()
                .codBarra("KL78")
                .tipo(tipoProduto)
                .fornecedor(fornecedor)
                .build();

        HttpEntity<ProdutoDTO> httpEntity = new HttpEntity<>(new ProdutoDTO(produtoTest));

        ResponseEntity<ProdutoDTO> response = testRestTemplate
                .exchange(URL + "/0", HttpMethod.PUT, httpEntity, ProdutoDTO.class);

        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }
}
