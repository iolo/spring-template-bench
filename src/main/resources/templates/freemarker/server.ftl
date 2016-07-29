<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>${title}</title>
</head>
<body>
<ul>
    <#list comments as it>
        <li>
            <h5>${it.author}</h5>
            <p>${it.content}</p>
        </li>
    </#list>
</ul>
</body>
</html>