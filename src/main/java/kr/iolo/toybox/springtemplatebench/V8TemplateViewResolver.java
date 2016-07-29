package kr.iolo.toybox.springtemplatebench;

import org.springframework.web.servlet.view.UrlBasedViewResolver;

/**
 * @author iolo
 * @see org.springframework.web.servlet.view.script.ScriptTemplateViewResolver
 */
public class V8TemplateViewResolver extends UrlBasedViewResolver {

    public V8TemplateViewResolver() {
        this.setViewClass(this.requiredViewClass());
    }

    public V8TemplateViewResolver(String prefix, String suffix) {
        this();
        this.setPrefix(prefix);
        this.setSuffix(suffix);
    }

    protected Class<?> requiredViewClass() {
        return V8TemplateView.class;
    }

}
