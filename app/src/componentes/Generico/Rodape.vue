<template>
    <div>
        <footer class="footer">
            <ul class="footer-menushare">
                <li><a href="https://github.com/beokabe/" target="_blank">Meu Github</a></li>
            </ul>

            <p>Copyright Beatriz Carolina Okabe</p>
            <p>Para mais informações sobre o trabalho, acesse o <a href="https://ava.ufms.br/mod/assign/view.php?id=164108" target="_blank"><u>ava</u></a>.
            </p>
        </footer>
    </div>
</template>

<script>
    export default {
        name: "Rodape"
    }
</script>

<style scoped>
    * {
        font-size: 18px;
    }

    .footer {
        background-color: #25303e;
        color: #83969f;
        text-align: center;
        padding-bottom: 2em;
    }

    .footer-menushare {
        background-color: #303d4c;
        padding: .5em;
    }

    .footer-menushare li {
        display: inline-block;
        margin: 0 1em;
    }

    .footer-menushare a {
        font-size: 1.1em;
        text-decoration: none;
        color: white;
    }
</style>