import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Produtos from '../views/GerenciadorProdutos/Produtos'
import Pessoas from "../views/GerenciadorPessoas/Pessoas";
import Login from "../views/Login/Login";

Vue.use(VueRouter)

const routes = [
    {
        path: '/Home',
        name: 'Home',
        component: Home
    },
    {
        path: '/Administrador/Produtos',
        name: 'Produtos',
        component: Produtos
    },
    {
        path: '/Administrador/Pessoas',
        name: 'Pessoas',
        component: Pessoas
    },
    {
        path: '/',
        name: 'Login',
        component: Login
    }
]

const router = new VueRouter({
    routes
})

export default router