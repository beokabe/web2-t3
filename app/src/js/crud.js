import axios from 'axios'

var API_URL = "http://localhost:8081/api/";

    function criarConfig(url, metodo, data) {
        let requisicao = {
            method: metodo ? metodo : 'get',
            url: url,
            crossDomain: true,
            headers: {
                'login': 'beatriz',
                'senha': '123'
            }
        };

        if (data) {
            requisicao.data = data;
        }
        return requisicao;
    }

    async function buscarUsuario(id) {
        var config = criarConfig(API_URL + 'usuario/' + id);

        return axios(config);
    }

    async function getItens(tipo, url) {
        var config;

        if (url) {
            config = criarConfig(url);
        } else {
            config = criarConfig(API_URL + tipo);
        }

        return axios(config);
    }

    async function getItem(tipo, id) {
        var config = criarConfig(API_URL + tipo + '/' + id);

        return axios(config);
    }

    async function postItem(tipo, item) {
        if (!tipo || !item) return;

        var config = criarConfig(API_URL + tipo, 'post', item);

        return axios(config);
    }

    async function deletar(tipo, id) {
        var config = criarConfig(API_URL + tipo + '/' + id, 'delete');

        return axios(config);
    }

    async function updateItem(tipo, item) {
        var config = criarConfig(API_URL + tipo + "/" + item.id, 'put', item);

        return axios(config);
    }

    export default {buscarUsuario, getItem, getItens, updateItem, postItem, deletar}