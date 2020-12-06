<template>
  <div id="todo">
    <div class="main">
      <div class="col-md-6 col-sm-12">
        <div class="login-form">
          <form>
            <div class="form-group">
              <label>Nome do Usuário</label>
              <input type="text" class="form-control" placeholder="Ex.: Agostinho Carrara">
            </div>
            <div class="form-group">
              <label>Senha</label>
              <input type="password" class="form-control" placeholder="A senha é sempre: 123">
            </div>
            <button type="submit" @click="logar" class="btn btn-black">Login</button>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>

export default {
  name: "Login",
  data() {
    return {}

  },
  methods() {

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