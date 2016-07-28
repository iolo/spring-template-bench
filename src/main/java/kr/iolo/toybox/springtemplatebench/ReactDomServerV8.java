package kr.iolo.toybox.springtemplatebench;

import com.eclipsesource.v8.V8;
import com.eclipsesource.v8.V8Array;
import com.eclipsesource.v8.V8Object;
import com.eclipsesource.v8.utils.V8ObjectUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author iolo
 */
@Component
public class ReactDomServerV8 {

    private static final String[] SCRIPTS = {
            "/META-INF/resources/webjars/react/15.2.1/react.js",
            "/META-INF/resources/webjars/react/15.2.1/react-dom-server.js",
            "/static/Comment.js",
            "/static/CommentList.js",
            "/server-scripts/react-v8.js"
    };

    public String render(List<Comment> comments) throws Exception {
        final V8 v8 = V8.createV8Runtime();
        final V8Array arg = V8ObjectUtils.toV8Array(v8, comments.stream()
                .map((it) -> new V8Object(v8)
                        .add("id", it.getId())
                        .add("author", it.getAuthor())
                        .add("content", it.getContent())
                )
                .collect(Collectors.toList()));
        final V8Array args = new V8Array(v8)
                .push(arg);
        for (final String script : SCRIPTS) {
            v8.executeVoidScript(FileCopyUtils.copyToString(new InputStreamReader(new ClassPathResource(script).getInputStream())));
        }
        return v8.executeStringFunction("renderToString", args);
    }
}
