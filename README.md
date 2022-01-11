<h1 align="center">
    <p> 📮 Sistema de Estoque de Produtos </p>
</h1>

<p align="center">
    <img alt="GitHub top language" src="https://img.shields.io/github/languages/top/Pedro-Manoel/
api-rest-sistema-estoque-produto?style=flat-square">
    <img alt="GitHub language count" src="https://img.shields.io/github/languages/count/Pedro-Manoel/api-rest-sistema-estoque-produto?style=flat-square">
    <img alt="Repository size" src="https://img.shields.io/github/repo-size/Pedro-Manoel/api-rest-sistema-estoque-produto?style=flat-square">
    <img alt="GitHub license" src="https://img.shields.io/github/license/Pedro-Manoel/api-rest-sistema-estoque-produto?style=flat-square"><br/>
    <a href="https://insomnia.rest/run/?label=sistema-estoque-produto&uri=https%3A%2F%2Fgithub.com%2FPedro-Manoel%2Fapi-rest-sistema-estoque-produto%2Fblob%2Fmain%2F.github%2FInsomnia.json" target="_blank"><img src="https://insomnia.rest/images/run.svg" alt="Run in Insomnia"></a>   
</p>

## 🔖 Sobre

API de estoque de produtos desenvolvida em [Spring Boot](https://spring.io/projects/spring-boot) para fins de aprendizagem. O banco de dados utilizado foi o [H2](https://www.h2database.com/html/main.html). Para os testes da API foi utilizado o [JUnit5](https://junit.org/junit5/), e para a documentação o [Swagger](https://swagger.io/).

## 💥 Utilização

- ### 👉 **Pré-requisitos**

  - É **necessário** possuir o **[Java](https://www.oracle.com/java/technologies/downloads//)** instalado no computador
  - É **necessário** possuir o **[Maven](https://maven.apache.org/install.html)** instalado no computador

1. Faça o clone do repositório:

```sh
  $ git clone https://github.com/Pedro-Manoel/api-rest-sistema-estoque-produto.git
```

2. Entre na pasta do projeto:

```sh
  $ cd api-rest-sistema-estoque-produto
```

3. Execute o seguinte comando
    
    1. Para executar a aplicação: 
    
    ```sh
    $ mvn spring-boot:run
    ```

    2. Para executar os testes:

    ```sh
    $ mvn test
    ```
## 🔰 Rotas da Aplicação

Um demostrativo do funcionamento das rotas da API utilizando dados fictícios. As rotas são as seguintes:

### 🚏 `/swagger-ui.html`

* **GET** : rota que retorna a documentação da API gerada pelo Swagger.

### 🚏 `/h2`

* **GET** : rota que retorna uma interface WEB para interagir com o banco de dados H2.

### 🚏 `/tiposproduto`

* **POST** : rota para criar um tipo de produto. 

    - ***Request***

        ```json
        {
            "nome": "Perecível"
        }
        ```

    - ***Response***

        ```json
        {
            "id": 1,
            "nome": "Perecível"
        }
        ```
* **GET** : rota para listar todos os tipos de produto cadastrados.

    - ***Response***

        ```json
        [
            {
                "id": 1,
                "nome": "Perecível"
            },
            {
                "id": 2,
                "nome": "Industrial"
            },
            {
                "id": 3,
                "nome": "Duravél"
            } 
        ]
        ```
### 🚏 `/tiposproduto/{id}`

* **GET** : rota para retornar um tipo de produto já cadastrado.

    - ***Response***

        ```json
        {
            "id": 1,
            "nome": "Perecível"
        }
        ```

* **PUT** :  rota para atualizar um tipo de produto já cadastrado. 

    - ***Request***

        ```json
        {
            "nome": "Não Duravél"
        }
        ```

    - ***Response***

        ```json
        {
            "id": 1,
            "nome": "Não Duravél"
        }
        ```
        
* **DELETE** : rota para remover um tipo de produto já cadastrado.

    - ***Response***

        ```json
        {
            "message": "Tipo de produto com id 1 foi removido com sucesso"
        }
        ```

### 🚏 `/fornecedor`

* **POST**: rota para criar um fornecedor.

    - ***Request***

        ```json
        {
            "nome": "Márcia Renata Souza",
	        "cnpj": "14.349.383/0001-12",
	        "telefone": "(83) 99431-0895",
	        "email": "marciarenata@flores.com.br",
	        "endereco" : {
		        "rua": "Rua Antônio Vicente",
		        "bairro": "Jaguaribe",
		        "cidade": "João Pessoa",
                "numero": 16,
		        "cep": "58015-210",
		        "uf": "PB"	
	        }
        }
        ```

    - ***Response***

        ```json
        {
            "id": 1,
            "nome": "Márcia Renata Souza",
	        "cnpj": "14.349.383/0001-12",
	        "telefone": "(83) 99431-0895",
	        "email": "marciarenata@flores.com.br",
	        "endereco" : {
		        "rua": "Rua Antônio Vicente",
		        "bairro": "Jaguaribe",
		        "cidade": "João Pessoa",
                "numero": 16,
		        "cep": "58015-210",
		        "uf": "PB"	
	        }
        }
        ```

* **GET**: rota para listar todos os fornecedores cadastrados.

    - ***Response***

        ```json
        [
            {
                "id": 1,
                "nome": "Márcia Renata Souza",
	            "cnpj": "14.349.383/0001-12",
	            "telefone": "(83) 99431-0895",
	            "email": "marciarenata@flores.com.br",
	            "endereco" : {
		            "rua": "Rua Antônio Vicente",
		            "bairro": "Jaguaribe",
		            "cidade": "João Pessoa",
                    "numero": 16,
		            "cep": "58015-210",
		            "uf": "PB"	
	            }
            },
            {
                "id": 2,
                "nome": "Paulo Anderson Silveira",
	            "cnpj": "69.496.726/0001-63",
	            "telefone": "(83) 99431-0895",
	            "email": "ppauloanderson@queirozgalvao.com",
	            "endereco" : {
		            "rua": "Rua Antônio Brant Ribeiro",
		            "bairro": "Centro",
		            "cidade": "Ponte Nova",
                    "numero": 18,
		            "cep": "35430-036",
		            "uf": "MG"	
	            }
            },
            {
                "id": 3,
                "nome": "Lorenzo Rodrigo Figueiredo",
	            "cnpj": "89.847.027/0001-28",
	            "telefone": "(34) 99276-3804",
	            "email": "llorenzorodrigofigueiredo@dyna.com.br",
	            "endereco" : {
		            "rua": "Rua das Laranjeiras",
		            "bairro": "Sobradinho",
		            "cidade": "Brasília",
                    "numero": 25,
		            "cep": "73015-135",
		            "uf": "DF"	
	            }
            }
             
        ]
        ```
### 🚏 `/fornecedores/{id}`

* **GET**: rota para retornar um fornecedor já cadastrado.

    - ***Response***

        ```json
        {
            "id": 1,
            "nome": "Márcia Renata Souza",
	        "cnpj": "14.349.383/0001-12",
	        "telefone": "(83) 99431-0895",
	        "email": "marciarenata@flores.com.br",
	        "endereco" : {
		        "rua": "Rua Antônio Vicente",
		        "bairro": "Jaguaribe",
		        "cidade": "João Pessoa",
                "numero": 16,
		        "cep": "58015-210",
		        "uf": "PB"	
	        }
        }
        ```

* **PUT**: rota para atualizar um fornecedor já cadastrado.

    - ***Request***

        ```json
        {
            "nome": "Márcia Renata Souza",
	        "cnpj": "14.349.383/0001-12",
	        "telefone": "(83) 99431-2245",
	        "email": "marciarenata@hotmail.com.br",
	        "endereco" : {
		        "rua": "Rua Treze",
		        "bairro": "Umbu",
		        "cidade": "Alvorada",
                "numero": 326,
		        "cep": "94834-280",
		        "uf": "RS"	
	        }
        }
        ```

    - ***Response***

        ```json
        {
            "id": 1,
            "nome": "Márcia Renata Souza",
	        "cnpj": "14.349.383/0001-12",
	        "telefone": "(83) 99431-2245",
	        "email": "marciarenata@hotmail.com.br",
	        "endereco" : {
		        "rua": "Rua Treze",
		        "bairro": "Umbu",
		        "cidade": "Alvorada",
                "numero": 326,
		        "cep": "94834-280",
		        "uf": "RS"	
	        }
        }
        ```

* **DELETE**: rota para remover um fornecedor já cadastrado.

    - ***Response***

        ```json
        {
            "message": "Fornecedor com id 1 foi removido com sucesso"
        }
        ```

### 🚏 `/produtos`

* **POST**: rota para criar um produto.

    - ***Request***

        ```json
        {
            "nome": "Arroz branco",
            "precoVenda": 2.50,
            "precoCompra": 2.80,
	        "codBarra": "508010204471",
            "quantEstoque": 18,
            "fornecedorId": 1,
            "tipoProdutoId": 1
        }
        ```

    - ***Response***

        ```json
        {
            "id": 1,
            "nome": "Arroz branco",
            "precoVenda": 2.50,
            "precoCompra": 2.80,
	        "codBarra": "508010204471",
            "quantEstoque": 18,
            "fornecedorId": 1,
            "tipoProdutoId": 1
        }
        ```

* **GET**: rota para listar todos os produtos cadastrados.

    - ***Response***

        ```json
        [
            {
                "id": 1,
                "nome": "Arroz branco",
                "precoVenda": 2.50,
                "precoCompra": 2.80,
                "codBarra": "508010204471",
                "quantEstoque": 18,
                "fornecedorId": 1,
                "tipoProdutoId": 1
            },
            {
                "id": 2,
                "nome": "Feijão preto",
                "precoVenda": 3.20,
                "precoCompra": 2.20,
                "codBarra": "664659445924",
                "quantEstoque": 13,
                "fornecedorId": 3,
                "tipoProdutoId": 1
            },
            {
                "id": 3,
                "nome": "Doce de leite",
                "precoVenda": 4.40,
                "precoCompra": 4.60,
                "codBarra": "328607918450",
                "quantEstoque": 23,
                "fornecedorId": 2,
                "tipoProdutoId": 2
            } 
        ]
        ```
### 🚏 `/produtos/{id}`

* **GET**: rota para retornar um produto já cadastrado.

    - ***Response***

        ```json
        {
            "id": 1,
            "nome": "Arroz branco",
            "precoVenda": 2.50,
            "precoCompra": 2.80,
	        "codBarra": "508010204471",
            "quantEstoque": 18,
            "fornecedorId": 1,
            "tipoProdutoId": 1
        }
        ```

* **PUT**: rota para atualizar um produto já cadastrado.

    - ***Request***

        ```json
        {
            "nome": "Arroz branco",
            "precoVenda": 3.50,
            "precoCompra": 3.80,
	        "codBarra": "508010204471",
            "quantEstoque": 9,
            "fornecedorId": 2,
            "tipoProdutoId": 1
        }
        ```

    - ***Response***

        ```json
        {
            "id": 1,
            "nome": "Arroz branco",
            "precoVenda": 3.50,
            "precoCompra": 3.80,
	        "codBarra": "508010204471",
            "quantEstoque": 9,
            "fornecedorId": 2,
            "tipoProdutoId": 1
        }
        ```

* **DELETE**: rota para remover um produto já cadastrado.

    - ***Response***

        ```json
        {
            "message": "Produto com id 1 foi removido com sucesso",
        }
        ```
### 🚏 `/produtos/{id}/estoque`
* **PATCH**: rota para atualizar o estoque de um produto já cadastrado.

    - ***Request***

        ```json
        {
            "quantEstoque": 12,
        }
        ```

    - ***Response***

        ```json
        {
            "id": 1,
            "nome": "Arroz branco",
            "precoVenda": 3.50,
            "precoCompra": 3.80,
	        "codBarra": "508010204471",
            "quantEstoque": 12,
            "fornecedorId": 2,
            "tipoProdutoId": 1
        }
        ```

## 📃 Licença

Esse projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.
