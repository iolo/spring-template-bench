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
        return (String) getScriptEngine().eval("renderToString(" + objectMapper.writeValueAsString(comments) + ")");
        //return (String) ((Invocable) engine).invokeFunction("renderToString", comments);
    }

    //---------------------------------------------------------

    private ScriptEngine scriptEngine = null;

    private ScriptEngine getScriptEngine() {
        if (scriptEngine == null) {
            synchronized (this) {
                if (scriptEngine == null) {
                    scriptEngine = new ScriptEngineManager().getEngineByName("nashorn");
                    for (final String script : SCRIPTS) {
                        try {
                            scriptEngine.eval(new InputStreamReader(new ClassPathResource(script).getInputStream()));
                        } catch (Throwable t) {
                            System.err.println("failed to load nashorn script: " + script);
                            t.printStackTrace();
                            //System.exit(2);
                        }
                    }
                }
            }
        }
        return scriptEngine;
    }
}
