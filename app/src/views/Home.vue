<template>
    <div id="main" class="container-fluid">

      <h1> Trabalho 3 </h1>
      <p>Esse trabalho é uma continuação do Trabalho 1 e Trabalho 2; caso você não tenha feito alguma funcionalidade, é de extrema importância que você faça.</p>

      <h2>Visão geral</h2>
  <ol>
      <li>  A pagina /index.html deve possuir o login, contendo os campos de usuário e senha e botão de logar: </li>

      <ol>
        <li> Deve ser desenvolvido um serviço, no back-end, para verificar se o usuário e senha estão corretos; </li>

        <li>  Esse usuário e senha devem ser armazenados no front-end e passados em todas as requisições feitas pelo sistema front-end. </li>

        <li>  Os erros ocorridos no back-end devem ser mostrados no front-end. </li>
      </ol>


      <li>   A pagina /produto.html deve possuir uma Grid listando todos os produtos do sistema. </li>


      <ol>
        <li>  Em cima da grid, deve possuir no mínimo duas opções de filtragem para os produtos. </li>

        <li>  Em cima da grid, deve existir um botão “Novo Item” que ira direcionar o sistema para o cadastro de um novo registro. </li>

        <li>  Cada item da Grid deve possuir os botoes de Editar e Deletar. Os botoes devem efetuar suas operações conforme seus nomes. </li>
      </ol>


      <li>   A pagina /pessoa.html deve possuir uma Grid listando todas as pessoas do sistema. </li>

      <ol>
        <li>   Em cima da grid, deve possuir no mínimo duas opções de filtragem para Pessoas. </li>

        <li>   Em cima da grid, deve existir um botão “Novo Item” que ira direcionar o sistema para o cadastro de um novo registro. </li>

        <li>   O primeiro campo desse cadastro deve ser o Tipo (Pessoa física ou jurídica) e os campos CPF, RG e CNPJ, serão mostrados de acordo com o Tipo de pesso. </li>

        <li>  Cada item da Grid deve possuir os botoes de Editar e Deletar. Os botoes devem efetuar suas operações conforme seus nomes. </li>
      </ol>
  </ol>
    </div>
</template>

<script>
    import crud from '../js/crud'

    export default {
        name: "Home",
        data() {
            return {
                URL: 'http://192.168.0.112:8081/albus/produto/',
                produtos: [],
            }
        },
        created() {
            this.carregarProdutos()
        },
        methods: {
            carregarProdutos() {

                this.produtos = []

                crud.getItens("produto")
                    .then(response => this.produtos = response.data)
                    .catch(function (error) {
                        console.log(error.status);
                    });
            },

            comprarProduto(id) {

                this.pegarProduto(id)
                    .then(response => {
                        this.produto = response.data;
                        this.produto.quantidade--;
                        crud.updateItem("produto", this.produto);
                        this.carregarProdutos();
                    })
                    .catch(function (error) {
                        console.log(error.status);
                    });
            },

            async pegarProduto(id) {
                return await crud.getItem("produto", id);
            }
        }
    }
</script>

<style scoped>
    #main {
        margin: 20px 0;
    }

    header {
        margin-bottom: 20px;
    }

    h1 {
      margin-bottom: 20px;
    }

    h1, h2 {
        font-size: 37px;
        margin-top: 50px;
    }

    ol li {
        margin-bottom: 15px;
    }

    ol {
        margin: 20px;
        text-align: justify;
    }
</style>