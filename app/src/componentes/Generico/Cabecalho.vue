<template>
    <div>
        <header class="top-header clearfix">
            <div class="maxwidth">
                <h1 title="Arduino Store">{{titulo}}</h1>

                <nav class="top-nav">
                    <ul>
                        <li>
                            <router-link to="/">Home</router-link>
                        </li>
                        <li>
                            <router-link to="/Administrador/Produtos">Gerenciar Produtos</router-link>
                        </li>
                        <li>
                            <router-link to="/Administrador/Pessoas">Gerenciar Pessoas</router-link>
                        </li>
                    </ul>
                </nav>
            </div>
        </header>
    </div>
</template>

<script>
    export default {
        props: ['titulo', 'listas'],
        name: "Cabecalho",
    }
</script>

<style scoped>
    header {
        margin: 0;
        background-color: #25303E;
    }

    h1 {
        font-family: sans-serif;
        color: #FFFFF3;
        font-weight: lighter;
        cursor: default;
        margin-top: 20px;
        font-size: 40px;
    }

    li {
        font-size: 18px;
    }

    .clearfix::after,
    .row::after {
        content: ' ';
        display: block;
        clear: both;
    }

    .maxwidth {
        max-width: 1124px;
        margin-left: auto;
        margin-right: auto;
    }

    .top-header {
        text-align: center;
        padding: 1px;
    }

    .top-nav {
        background: #303D4C;
        text-align: center;
    }

    .top-nav ul {
        margin: 0;
        padding: 1em 0;
        list-style: none;
    }

    .top-nav a {
        color: white;
        text-decoration: none;
        padding: 1em;
        display: block;
    }

    @media screen and (min-width: 48.75em) {
        .top-nav {
            background: transparent;
            float: right;
            margin-top: 28px;
        }

        .top-nav ul {
            margin: 0;
            padding: 0;
        }

        .top-nav li {
            display: inline-block;
        }

        .top-nav a {
            color: #FFFFF3;
            font-weight: bolder;
        }

        .top-nav a:hover {
            filter: opacity(0.7);
        }
    }
</style>