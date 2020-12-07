<template>
  <div id="app">
    <Cabecalho titulo="Painel do Usuário" v-show="isLogado"/>
    <h1 id="loginTitulo" v-show="!isLogado">Faça o seu login</h1>
      <router-view></router-view>
    <Rodape />
  </div>
</template>

<script>

import Cabecalho from "./componentes/Generico/Cabecalho";
import Rodape from "./componentes/Generico/Rodape";
import router from "@/router";

import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap-vue/dist/bootstrap-vue.css';

export default {
  name: 'App',

  components: {
    Cabecalho,
    Rodape
  },

  created() {
    this.redirecionarUsuarioSeNaoEstiverLogado();
  },

  computed: {
    isLogado: function () {
      return !!sessionStorage.getItem('isLogado');
    },
  },

  methods: {
    redirecionarUsuarioSeNaoEstiverLogado() {
      if(!sessionStorage.getItem('isLogado')) {
          router.push('/');
      }
    }
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
}

#loginTitulo {
  text-align: center;
  margin-top: 4%;
}
</style>
