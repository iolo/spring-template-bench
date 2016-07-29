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
    return compiledTemplate(model);
}
