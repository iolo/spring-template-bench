package kr.iolo.toybox.springtemplatebench;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.InputStreamReader;
import java.util.List;

/**
 * @author iolo
 */
@Component
public class ReactDomServerNashorn {

    private static final String[] SCRIPTS = {
            "/META-INF/resources/webjars/react/15.2.1/react.js",
            "/META-INF/resources/webjars/react/15.2.1/react-dom-server.js",
            "/static/Comment.js",
            "/static/CommentList.js",
            "/server-scripts/react-nashorn.js"
    };

    @Autowired
    ObjectMapper objectMapper;

    public String render(List<Comment> comments) throws Exception {
        final ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        for (final String script : SCRIPTS) {
            engine.eval(new InputStreamReader(new ClassPathResource(script).getInputStream()));
        }
        return (String) engine.eval("renderToString(" + objectMapper.writeValueAsString(comments) + ")");
        //return (String) ((Invocable) engine).invokeFunction("renderToString", comments);
    }
}
