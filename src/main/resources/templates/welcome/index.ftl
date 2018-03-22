<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>

<div id="app">
    <input v-model="link">
    <button v-on:click="shorten">Shorten</button>
    <h1>{{ shortenedUrl }}<h1>
</div>

<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
<script>

    var app = new Vue({
        el: '#app',
        data: {
            link: '',
            shortenedUrl: ''
        },
        methods: {
            shorten: function (event) {

                console.log(event);
                console.log(this.link);

                var params = new URLSearchParams();
                params.append('link', this.link);
                console.log('params = ' + params)

                axios.post('/api/urls/shorten', params)
                        .then(function (response) {
                            console.log(response);
                            this.shortenedUrl = 'Here is the shortened url: ' + response.data.shortenedUrl;
                            console.log(shortenedUrl);
                        }.bind(this))
                        .catch(function (error) {
                            console.log(error);
                        });

                this.link = '';
            }
        }
    })
</script>
</body>
</html>