<template>
  <div id="todo">
    <div class="main">
      <div class="col-md-6 col-sm-12">
        <div class="login-form">
          <form>
            <div class="form-group">
              <label>Nome do Usuário</label>
              <input type="text" id="login" class="form-control" placeholder="Ex.: Agostinho Carrara">
            </div>
            <div class="form-group">
              <label>Senha</label>
              <input type="password" id="senha" class="form-control" placeholder="A senha é sempre: 123">
            </div>
            <button @click="logar()" class="btn btn-black">Login</button>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import crud from "../../js/crud";
import router from "@/router";

export default {
  name: "Login",

  created() {
    this.isLogado()
  },

  methods: {
    logar() {
      let login = document.getElementById("login").value;
      let senha = document.getElementById("senha").value;

      let usuario = {
        login: login,
        senha: senha
      }

      crud.buscarUsuario(usuario)
          .then(function (response) {
            setTimeout(function ()  {
              sessionStorage.setItem('usuario', JSON.stringify(response.data))
              sessionStorage.setItem('isLogado', true)
              router.push('/home');
            }, 500)
          })
          .catch(function () {
            alert("O nome do usuário ou a senha está incorreta.")
            return;
          });
    },

    isLogado() {
      this.isLogado = !!sessionStorage.getItem('usuario');

      if (this.isLogado) {
        router.push('/home');
      }
    }
  }
}
</script>

<style scoped>
    body {
      font-family: "Lato", sans-serif;
    }

    .main {
      padding: 0px 10px;
    }

    @media screen and (max-width: 450px) {
      .login-form {
        margin-top: 10%;
        margin-bottom: 10%;
      }
    }

    @media screen and (min-width: 768px) {
      #todo {
        position: relative;
        left: -50px;
        top: -110px;
      }

      .main {
        margin-left: 40%;
      }

      .login-form {
        margin-top: 80%;
      }
    }

    .login-main-text h2 {
      font-weight: 300;
    }

    .btn-black {
      background-color: #000 !important;
      color: #fff;
    }
</style>