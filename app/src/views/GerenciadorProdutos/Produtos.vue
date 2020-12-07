<template>
  <div id="produto-list">
    <div class="actions">
      <a class="btn btn-success" data-target="#editarProduto" v-show="usuario.isAdministrador"
         @click="atribuirProduto(produto)" data-toggle="modal" tabindex="1">
        Novo
      </a>
    </div>

    <div id="filtragem">
      <label for="filtro" id="label-filtro">Filtre por:</label>

      <select name="filtro" id="filtro" class="filtro" @change="filtrarPorValor($event)">
        <option value="todos">Todos</option>
        <option value="maior">&gt;= R$ 100,00</option>
        <option value="menor">&lt;= R$ 100,00</option>
      </select>
    </div>

    <div id="filtragem-tipo-produto">
      <label for="filtro-produto" id="label-filtro-tipo-produto">Tipo de Produto: </label>

      <select name="filtro-produto" id="filtro-produto" class="filtro" @change="filtrarPorDescricao($event)">
        <option value="todos">Todos</option>
        <option value="Computador"> Computadores</option>
        <option value="Jubileu"> Jubileu</option>
      </select>
    </div>

    <table class="table" v-show="usuario.isAdministrador">
      <thead>
      <tr>
        <th>Id</th>
        <th>Descricao</th>
        <th>Qtd. Estoque</th>
        <th>Preço da Compra</th>
        <th>Preço da Venda Física</th>
        <th>Preço da Venda Juridica</th>
        <th>Actions</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="produto in produtos" :key="produto">
        <td> {{ produto.id }}</td>
        <td> {{ produto.descricao }}</td>
        <td> {{ produto.quantidadeEstoque }}</td>
        <td> {{ produto.precoCompra }}</td>
        <td> {{ produto.precoVendaFisica }}</td>
        <td> {{ produto.precoVendaJuridica }}</td>

        <td>

          <a class="btn-action" data-toggle="modal" data-target="#editarProduto"
             @click="atribuirProduto(produto)"><i class="fas fa-pen"></i></a>
          <a tabindex="1" class="btn-action btn-deletar" @click="deletarProduto(produto.id)">
            <i class="fas fa-minus"></i></a>
        </td>
      </tr>
      </tbody>
    </table>

    <table class="table" v-show="!usuario.isAdministrador">
      <thead>
      <tr>
        <th>Id</th>
        <th>Descricao</th>
        <th>Qtd. Estoque</th>
        <th>Preço da Compra</th>
        <th>Preço da Venda</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="produto in produtos" :key="produto">
        <td> {{ produto.id }}</td>
        <td> {{ produto.descricao }}</td>
        <td> {{ produto.quantidadeEstoque }}</td>
        <td> {{ produto.precoCompra }}</td>
        <td> {{ produto.precoVenda }}</td>
      </tr>
      </tbody>
    </table>

    <!-- Modal Editar Item -->
    <div class="modal fade" id="editarProduto" role="dialog">
      <div class="modal-dialog">

        <div class="modal-content">
          <div class="modal-header">
            <h4 class="modal-title" ng-show="produto.id" >Editar Produto</h4>
            <h4 class="modal-title" ng-show="!produto.id" >Criar Produto</h4>
            <button type="button" class="close" data-dismiss="modal" @click="limparProduto()">&times;
            </button>
          </div>
          <div class="modal-body">

            <div>
              <div class="form-group" v-show="produto.id">
                <label for="id">ID</label>
                <input type="text" class="form-control" id="id"
                       name="id" required v-model="produto.id" disabled>
              </div>
              <div class="form-group">
                <label for="descricao">Descrição</label>
                <input type="text" class="form-control" id="descricao"
                       name="descricao" required v-model="produto.descricao">
              </div>
              <div class="form-group">
                <label for="qtdEstoque">Quantidade</label>
                <input type="text" class="form-control" id="qtdEstoque"
                       name="qtdEstoque" required v-model="produto.quantidadeEstoque">
              </div>
              <div class="form-group">
                <label for="idadePermitida">Idade Permitida</label>
                <input type="number" class="form-control" id="idadePermitida"
                       name="idadePermitida" required v-model="produto.idadePermitida">
              </div>
              <div class="form-group">
                <label for="precoCompra">Preço da Compra</label>
                <input type="number" class="form-control" id="precoCompra"
                       name="precoCompra" required v-model="produto.precoCompra">
              </div>

              <div class="form-group" v-show="!produto.id || usuario.isAdministrador">
                <label for="precoVendaFisica">Preço da Venda Física</label>
                <input type="number" class="form-control" id="precoVendaFisica"
                       name="precoVendaFisica" required v-model="produto.precoVendaFisica">
              </div>
              <div class="form-group" v-show="!produto.id || usuario.isAdministrador">
                <label for="precoVendaJuridica">Preço da Venda Jurídica</label>
                <input type="number" class="form-control" id="precoVendaJuridica"
                       name="precoVendaJuridica" required v-model="produto.precoVendaJuridica">
              </div>
              <div>
                <button @click="salvar()" class="btn btn-primary" data-dismiss="modal">
                  Salvar
                </button>
              </div>
            </div>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal" @click="limparProduto()">
              Close
            </button>
          </div>
        </div>
      </div>
    </div>

  </div>

</template>

<script>
import crud from "../../js/crud";

export default {
  nome: "Produtos",

  data() {
    return {
      aux: {},
      usuario: {},
      produtos: [],
      produto: this.criarProdutoVazio(),
    }
  },

  created() {
    this.carregarProdutos();
    this.carregarUsuario();
  },

  methods: {
    carregarProdutos() {
      crud.getItens("produto")
          .then(response => this.produtos = response.data)
          .catch(function (error) {
            console.log(error.status);
          });
    },

    deletarProduto(id) {
      crud.deletar("produto", id).then(() => {
        this.limparProduto();
        this.carregarProdutos();
      });
    },

    atribuirProduto(produto) {
      this.produto = produto;
      this.aux = { ...produto};
    },

    salvar() {

      if (!this.produto.descricao || !this.produto.idadePermitida || !this.produto.quantidadeEstoque
          || !this.produto.precoCompra || !this.produto.precoVendaFisica || !this.produto.precoVendaJuridica) {
        alert("Preencha todos os campos obrigatórios!")
        this.clonarProduto(this.produto, this.aux);
        return;
      }

      this.produto.precoCompra = parseInt(this.produto.precoCompra);
      this.produto.precoVendaFisica = parseInt(this.produto.precoVendaFisica);
      this.produto.precoVendaJuridica = parseInt(this.produto.precoVendaJuridica);

      if (this.produto.id) {
        crud.updateItem('produto', this.produto)
            .then(() => {
              this.limparProduto();
            }).catch(function (error) {
          console.log(error);
        });
      } else {
        crud.postItem('produto', this.produto)
            .then(() => {
              this.limparProduto();
              this.carregarProdutos();
            }).catch(function (error) {
          console.log(error);
        });
      }
    },
    limparProduto() {
      this.produto = this.criarProdutoVazio();
    },

    filtrarPorValor(event) {

      var e = document.getElementById("filtro-produto");
      var selected = e.options[e.selectedIndex].value;
      var url;
      var aux = '';

      if (event.target.value === 'maior') {
        aux = 'min=100'
      } else if (event.target.value === 'menor') {
        aux = 'max=100'
      }

      if (selected !== 'todos' && event.target.value !== 'todos') {
        url = 'produto?' + aux + '&' + 'descricao=' + selected
      } else if (selected === 'todos' && event.target.value !== 'todos') {
        url = 'produto?' + aux;
      }

      if (url) {
        crud.getItens(url)
            .then(response => this.produtos = response.data)
            .catch(function (error) {
              console.log(error);
            });
      } else {
        crud.getItens('produto')
            .then(response => this.produtos = response.data)
            .catch(function (error) {
              console.log(error);
            });
      }

    },

    filtrarPorDescricao(event) {

      var e = document.getElementById("filtro");
      var selected = e.options[e.selectedIndex].text;
      var url;
      var aux = '';

      if (selected === 'maior') {
        aux = 'min=100'
      } else if (selected === 'menor') {
        aux = 'max=100'
      }

      if (selected !== 'todos' && event.target.value !== 'todos') {
        url = 'produto?descricao=' + event.target.value + '&' + aux;
      } else if (selected === 'todos' && event.target.value !== 'todos') {
        url = 'produto?' + aux;
      }

      if (url) {
        crud.getItens(url)
            .then(response => this.produtos = response.data)
            .catch(function (error) {
              console.log(error);
            });
      } else {
        crud.getItens('produto')
            .then(response => this.produtos = response.data)
            .catch(function (error) {
              console.log(error);
            });
      }
    },

    clonarProduto(produtoOriginal, cloneEstadoAnterior) {
      produtoOriginal.id = cloneEstadoAnterior.id;
      produtoOriginal.descricao = cloneEstadoAnterior.descricao;
      produtoOriginal.idadePermitida = cloneEstadoAnterior.idadePermitida;
      produtoOriginal.quantidadeEstoque = cloneEstadoAnterior.quantidadeEstoque;
      produtoOriginal.precoCompra = cloneEstadoAnterior.precoCompra;
      produtoOriginal.precoVenda = cloneEstadoAnterior.precoVenda;
      produtoOriginal.precoVendaFisica = cloneEstadoAnterior.precoVendaFisica;
      produtoOriginal.precoVendaJuridica = cloneEstadoAnterior.precoVendaJuridica;

    },

    criarProdutoVazio() {
      return {
        id: '',
        descricao: '',
        idadePermitida: '',
        quantidadeEstoque: '',
        precoCompra: '',
        precoVendaFisica: '',
        precoVendaJuridica: '',
        precoVenda: ''
      }
    },

    carregarUsuario() {
      crud.buscarUsuario(4)
        .then(response => this.usuario = response.data)
          .catch(function (error) {
            console.log(error);
          });
    }
  }
}

</script>

<style scoped>

    #produto-list {
      width: 80%;
      position: relative;
      left: 13%;
      font-size: 20px;
      margin: 50px 0;
    }

    a, #produto-list {
      font-size: 16px;
    }

    a {
      text-decoration: none;
      color: white;
    }

    input[type=number] {
      -moz-appearance: textfield;
    }

    #filtragem, #filtragem-tipo-produto {
      float: left;
    }

    .actions {
      float: right;
      padding: 10px 0;
      text-align: right;
    }

    .filtro {
      width: 50%;
      position: relative;
      left: 15px;
      margin: 20px 0;
    }

    .btn-action {
      width: 40px;
      cursor: pointer;
      margin: 0 5px;
    }

    .btn-deletar {
      color: #F44336;
    }

</style>