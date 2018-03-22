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
    <#if url?? >
    <#--Not null-->
        <div id="link">${url.originalUrl}</div>
    <#else >
    <#--Null-->
    Link not found
    <div id="link"></div>
    </#if>
</div>

<script>
    var url = document.querySelector('#link').innerText;

    console.log("URL = " + url);

    if (url.length > 0) {
        window.open("http://" + url, '_self');
    }
</script>
</body>
</html>