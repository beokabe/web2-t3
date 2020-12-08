<template>
    <div id="pessoa-list">
        <div class="actions" id="options-cabecalho-template">
            <a class="btn btn-success" data-target="#editarPessoa"
               @click="atribuirPessoa(pessoa, 'pf')" data-toggle="modal" tabindex="1" v-show="usuario.isAdministrador">
                Nova Pessoa Física
            </a>

          <a class="btn btn-success" data-target="#editarPessoa"
             @click="atribuirPessoa(pessoa, 'pj')" data-toggle="modal" tabindex="1" v-show="usuario.isAdministrador">
            Nova Pessoa Jurídica
          </a>
        </div>

      <div id="filtragem">
        <label for="filtro">Filtre por:</label>

        <select name="filtro" id="filtro" @change="filtrar($event)">
          <option value="todos">Todos</option>
          <option value="pf">Física</option>
          <option value="pj">Jurídica</option>
        </select>
      </div>

        <table class="table">
            <thead>
            <tr>
                <th>Nome</th>
                <th>Apelido</th>
                <th>Tipo</th>
                <th>Situacao</th>
                <th>Data de Nascimento</th>
                <th>RG</th>
                <th>CPF</th>
                <th>CNPJ</th>
                <th>Responsável</th>

              <th v-show="usuario.isAdministrador">Ações</th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="pessoa in pessoas" :key="pessoa">
                <td>{{ pessoa.nome }}</td>
                <td>{{ pessoa.apelido }}</td>
                <td>{{ pessoa.tipo }}</td>
                <td>{{ pessoa.situacao }}</td>
                <td>{{ pessoa.dataNascimento }}</td>
                <td>{{ pessoa.rg || 'Não possui' }}</td>
                <td>{{ pessoa.cpf || 'Não possui' }}</td>
                <td>{{ pessoa.cnpj || 'Não possui' }}</td>
                <td>{{ pessoa.responsavelId ? pessoa.responsavelId.nome : 'Não possui' }}</td>

              <td v-show="usuario.isAdministrador">
                    <a class="btn-action" data-toggle="modal" data-target="#editarPessoa"
                       @click="atribuirPessoa(pessoa)"><i class="fas fa-pen"></i></a>
                    <a tabindex="1" class="btn-action btn-deletar" @click="deletarPessoa(pessoa.id)">
                      <i class="fas fa-minus"></i></a>
                </td>
            </tr>
            </tbody>
        </table>

        <!-- Modal Editar Item -->
        <div class="modal fade" id="editarPessoa" role="dialog">
            <div class="modal-dialog">

                <div class="modal-content">

                    <div class="modal-header" v-show="pessoa.tipo==='FISICA'">
                        <h4 v-show="pessoa.id" class="modal-title">Editar Pessoa Fisica</h4>
                        <h4 v-show="!pessoa.id" class="modal-title">Criar Pessoa Fisica</h4>
                      <button type="button" class="close" data-dismiss="modal" @click="limparPessoa">&times;
                        </button>
                    </div>

                  <div class="modal-header" v-show="pessoa.tipo==='JURIDICA'">
                    <h4  v-show="pessoa.id" class="modal-title">Editar Pessoa Juridica</h4>
                    <h4  v-show="!pessoa.id" class="modal-title">Criar Pessoa Juridica</h4>
                    <button type="button" class="close" data-dismiss="modal" @click="limparPessoa">&times;
                    </button>
                  </div>
                    <div class="modal-body">

                        <div class="form-group" v-show="pessoa.id">
                            <label for="id">ID</label>
                            <input type="text" class="form-control" id="id"
                                   name="id"  v-model="pessoa.id" disabled>
                        </div>
                        <div class="form-group">
                            <label for="nome">Nome</label>
                            <input type="text" class="form-control" id="nome"
                                   name="nome"  v-model="pessoa.nome">
                        </div>

                      <div class="form-group">
                        <label>Responsável</label>
                        <select class="form-control" v-model="selected" @change="setarResponsavel(pessoa)">
                        <option selected="true" value="">Escolha um responsável</option>
                        <option v-for="option in pessoasAcimaDeDezoito" :key="option" :value="option" >{{ option.nome }}</option>
                      </select>
                      </div>

                        <div class="form-group">
                            <label for="apelido">Apelido</label>
                            <input type="text" class="form-control" id="apelido"
                                   name="apelido"  v-model="pessoa.apelido">
                        </div>

                        <div class="form-group">
                          <label for="situacao">Situação</label>
                          <input type="text" class="form-control" id="situacao"
                                 name="situacao"  v-model="pessoa.situacao">
                        </div>

                      <div class="form-group">
                        <label for="dataNascimento">Data de Nascimento</label>
                        <input type="date" class="form-control" id="dataNascimento"
                               name="dataNascimento"  v-model="pessoa.dataNascimento">
                      </div>

                      <div class="form-group" v-if="pessoa.tipo==='FISICA'">
                        <label for="rg">RG</label>
                        <input type="text" class="form-control" id="rg"
                               name="rg"  v-model="pessoa.rg">
                      </div>

                      <div class="form-group" v-if="pessoa.tipo==='FISICA'">
                        <label for="cpf">CPF</label>
                        <input type="text" class="form-control" id="cpf"
                               name="cpf"  v-model="pessoa.cpf">
                      </div>

                      <div class="form-group" v-if="pessoa.tipo==='JURIDICA'">
                        <label for="cnpj">CNPJ</label>
                        <input type="text" class="form-control" id="cnpj"
                               name="cnpj" v-model="pessoa.cnpj">
                      </div>
                        <div>
                            <button @click="salvar()" class="btn btn-primary" data-dismiss="modal">
                                Salvar
                            </button>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal"
                                @click="this.limparPessoa">Close
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import crud from '../../js/crud';

    export default {
        name: "pessoas",

      data() {
            return {
                aux: {},
                usuario: JSON.parse(sessionStorage.getItem('usuario')),
                pessoas: [],
                pessoasFisicas: [],
                pessoasJuridicas: [],
                pessoasAcimaDeDezoito: [],
                pessoa: this.criarPessoaVazio(),
            }
        },
        created() {
            this.carregarPessoas();
            this.carregarPessoasResponsaveis()
        },
        methods: {
          carregarPessoas() {
            crud.getItens("pessoa")
                .then(response => this.pessoas = response.data)
                .catch(function (error) {
                  console.log(error.status);
                });
          },

          carregarPessoasFisicas() {
                crud.getItens("pessoa?tipo=FISICA")
                    .then(response => this.pessoas = response.data)
                    .catch(function (error) {
                        console.log(error.status);
                    });
            },

          carregarPessoasJuridicas() {
            crud.getItens("pessoa?tipo=JURIDICAS")
                .then(response => this.pessoas = response.data)
                .catch(function (error) {
                  console.log(error.status);
                });
          },

          carregarPessoasResponsaveis() {
            crud.getItens("pessoa/responsaveis/" + this.usuario.id)
                .then(response => this.pessoasAcimaDeDezoito = response.data)
                .catch(function (error) {
                  console.log(error.status);
                });
          },

            atribuirPessoa(pessoa, tipo = '') {
              this.pessoa = pessoa;
              this.aux = { ...pessoa};

              if (this.selected) {
                this.selected = pessoa.responsavelId;
                this.pessoa.responsavelId.id = this.selected;
              }

              if (tipo === 'pf') {
                this.pessoa.tipo = 'FISICA';
              } else if(tipo === 'pj') {
                this.pessoa.tipo = 'JURIDICA';
              }
            },

            deletarPessoa(id) {
                crud.deletar("pessoa", id).then(() => {
                    this.limparPessoa();
                    this.carregarPessoas();
                });
            },

            salvar() {
              if (!this.pessoa.nome && !this.pessoa.situacao) {
                console.log("entrou")
                  alert("Preencha todos os campos obrigatórios!")

                  if (this.pessoa.id) {
                    this.clonarPessoa(this.pessoa, this.aux);
                  }

                  return;

                } else if (this.pessoa.tipo === 'FISICA' && !this.pessoa.cpf) {
                  alert("A pessoa física deve conter cpf")

                  if (this.pessoa.id) {
                    this.pessoa.cpf = this.aux.cpf;
                  }

                  return;

                } else if (this.pessoa.tipo === 'FISICA' && !this.pessoa.rg) {
                  alert("A pessoa física deve conter rg")

                  if (this.pessoa.id) {
                    this.pessoa.rg = this.aux.rg;
                  }

                  return;

                } else if (this.pessoa.tipo === 'JURIDICA' && !this.pessoa.cnpj) {
                  alert("A pessoa jurídica deve conter cnpj")

                  if (this.pessoa.id) {
                    this.pessoa.cnpj = this.aux.cnpj;
                  }

                  return;
                }

                  if (this.pessoa.id) {
                    crud.updateItem('pessoa', this.pessoa)
                        .then(() => {
                            this.limparPessoa();
                        }).catch(function (error) {
                          this.pessoa = this.aux;
                          console.log(error);
                    });
                } else {
                    crud.postItem('pessoa', this.pessoa)
                        .then(() => {
                            this.limparPessoa();
                            this.carregarPessoas();
                        }).catch(function (error) {
                        console.log(error);
                    });
                }
            },

            setarResponsavel(pessoa) {
              if (this.selected === '') {
                this.selected = null;
              }

              pessoa.responsavelId = this.selected;
            },

            clonarPessoa(pessoaOriginal, cloneEstadoAnterior) {
                pessoaOriginal.id = cloneEstadoAnterior.id;
                pessoaOriginal.nome = cloneEstadoAnterior.nome;
                pessoaOriginal.responsavelId = cloneEstadoAnterior.responsavelId,
                pessoaOriginal.apelido = cloneEstadoAnterior.apelido;
                pessoaOriginal.tipo = cloneEstadoAnterior.tipo;
                pessoaOriginal.situacao = cloneEstadoAnterior.situacao;
                pessoaOriginal.dataNascimento = cloneEstadoAnterior.dataNascimento;
                pessoaOriginal.rg = cloneEstadoAnterior.rg;
                pessoaOriginal.cpf = cloneEstadoAnterior.cpf;
                pessoaOriginal.cnpj = cloneEstadoAnterior.cnpj;
            },

            filtrar(event) {
              if (event.target.value === 'todos') {
                this.carregarPessoas();
              } else if(event.target.value === 'pf') {
                this.carregarPessoasFisicas();
              } else if(event.target.value === 'pj') {
                this.carregarPessoasJuridicas();
              }
            },

            limparPessoa() {
                this.pessoa = this.criarPessoaVazio()
            },

            criarPessoaVazio() {
                return {
                    id: '',
                    tipo: '',
                    nome: '',
                    responsavelId: null,
                    apelido: '',
                    situacao: 'ATIVO',
                    dataNascimento: '',
                    rg: '',
                    cpf: '',
                    cnpj: ''
                }
            },
        }
    }

</script>

<style scoped>
    #pessoa-list {
        width: 80%;
        position: relative;
        left: 13%;
        font-size: 20px;
        margin: 50px 0;
    }

    a, #pessoa-list {
        font-size: 16px;
    }

    a {
        text-decoration: none;
        color: white;
        margin: 0 5px;
    }

    .actions {
        padding: 10px 0;
    }

    a:not([href]):not([tabindex]) {
        text-decoration: none;
    }

    @media screen and (min-width: 768px) {
      .actions {
        float: right;
      }

      #filtro {
        width: 80%;
        position: relative;
        left: 20px;
        margin: 20px 0;
      }
    }

    .actions {
      padding: 10px 0;
      text-align: right;
    }

    #filtro {
      width: 30%;
      position: relative;
      left: 20px;
      margin: 20px 0;
    }

    .actions .btn {
      color: white;
    }

    .btn-action {
      width: 40px;
      cursor: pointer;
    }

    .btn-deletar {
      color: #F44336;
    }

</style>