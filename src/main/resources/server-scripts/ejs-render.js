var templates = {};

function render(template, model, url) {
    var compiledTemplate;
    if (templates[url] === undefined) {
        compiledTemplate = ejs.compile(template);
        templates[url] = compiledTemplate;
    }
    else {
        compiledTemplate = templates[url];
    }
    return compiledTemplate(toJsonObject(model));
}

function toJsonObject(javaObj) {
    var jsonObj = {};
    for (var k in javaObj) {
        // Convert Iterable like List to real JSON array
        if (javaObj[k] instanceof Java.type("java.lang.Iterable")) {
            jsonObj[k] = Java.from(javaObj[k]);
        } else {
            jsonObj[k] = javaObj[k];
        }
    }
    return jsonObj;
}