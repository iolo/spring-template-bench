package kr.iolo.toybox.springtemplatebench;

import com.eclipsesource.v8.V8;
import com.eclipsesource.v8.V8Array;
import com.eclipsesource.v8.V8Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractUrlBasedView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStreamReader;
import java.util.Map;

/**
 * TODO: make configurable!
 *
 * @author iolo
 * @see org.springframework.web.servlet.view.script.ScriptTemplateView
 * @see org.springframework.web.servlet.view.script.ScriptTemplateConfig
 * @see org.springframework.web.servlet.view.script.ScriptTemplateConfigurer
 */
public class V8TemplateView extends AbstractUrlBasedView {

    private static final String[] SCRIPTS = {
            "/server-scripts/v8-polyfill.js",
            "/META-INF/resources/webjars/ejs/2.4.1/ejs-v2.4.1/ejs.min.js",
            "/META-INF/resources/webjars/react/15.2.1/react.min.js",
            "/META-INF/resources/webjars/react/15.2.1/react-dom-server.min.js",
            "/static/Comment.js",
            "/static/CommentList.js",
            "/server-scripts/v8-ejs-render.js"
    };

    public V8TemplateView() {
        this.setContentType(null);
    }

    public V8TemplateView(String url) {
        super(url);
        this.setContentType(null);
    }

    @Override
    protected void renderMergedOutputModel(
            Map<String, Object> map,
            HttpServletRequest req,
            HttpServletResponse res) throws Exception {
        //final NodeJS nodeJS = NodeJS.createNodeJS();
        final V8 v8 = V8.createV8Runtime();
        for (final String script : SCRIPTS) {
            v8.executeVoidScript(FileCopyUtils.copyToString(new InputStreamReader(new ClassPathResource(script).getInputStream())));
        }
        final String url = getUrl();
        final String template = FileCopyUtils.copyToString(new InputStreamReader(getApplicationContext().getResource(url).getInputStream()));
        final V8Value arg = V8Utils.toV8Object(v8, map);
        final V8Array args = new V8Array(v8)
                .push(template)
                .push(arg)
                .push(url);
        res.getWriter().write(v8.executeStringFunction("render", args));
    }

}
