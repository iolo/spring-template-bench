<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>${title}</title>
</head>
<body>
<div id="main">${reactHtml}</div>
<script src="/webjars/jquery/3.1.0/dist/jquery.min.js"></script>
<script src="/webjars/react/15.2.1/react.min.js"></script>
<script src="/webjars/react/15.2.1/react-dom.min.js"></script>
<script src="/Comment.js"></script>
<script src="/CommentList.js"></script>
<script src="/app.js"></script>
<#--FIXME: browserify 돌리려면 require(ES6 import)가 필요한데... nashorn은 require를 지원하지 않음.-->
<#--<script src="/bundle.js"></script>-->
</body>
</html>