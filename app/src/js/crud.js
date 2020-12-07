import axios from 'axios'

var API_URL = "http://localhost:8081/api/";

    function criarConfig(url, metodo, data) {
        let usuario = JSON.parse(sessionStorage.getItem('usuario'));

        let requisicao = {
            method: metodo ? metodo : 'get',
            url: url,
            crossDomain: true,
            headers: {
                'login': usuario.login,
                'senha': usuario.senha
            }
        };

        if (data) {
            requisicao.data = data;
        }

        return requisicao;
    }

    async function buscarUsuario(usuario) {
        return axios.put(API_URL + 'usuario/logar', usuario);
    }

    async function getItens(tipo, url) {
        let config;

        if (url) {
            config = criarConfig(url);
        } else {
            config = criarConfig(API_URL + tipo);
        }

        return axios(config);
    }

    async function getItem(tipo, id) {
        let config = criarConfig(API_URL + tipo + '/' + id);

        return axios(config);
    }

    async function postItem(tipo, item) {
        if (!tipo || !item) return;

        let config = criarConfig(API_URL + tipo, 'post', item);

        return axios(config);
    }

    async function deletar(tipo, id) {
        let config = criarConfig(API_URL + tipo + '/' + id, 'delete');

        return axios(config);
    }

    async function updateItem(tipo, item) {
        let config = criarConfig(API_URL + tipo + "/" + item.id, 'put', item);

        return axios(config);
    }

    export default {buscarUsuario, getItem, getItens, updateItem, postItem, deletar}