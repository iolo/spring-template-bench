package kr.iolo.toybox.springtemplatebench;

import com.eclipsesource.v8.V8;
import com.eclipsesource.v8.V8Array;
import com.eclipsesource.v8.V8Object;
import com.eclipsesource.v8.V8Value;
import org.springframework.beans.BeanUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;

import java.beans.PropertyDescriptor;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * TODO: more sophisticated marshaling
 *
 * @author iolo
 * @see com.eclipsesource.v8.utils.V8ObjectUtils
 */
public class V8Utils {
    private static final String[] SCRIPTS = {
            "/server-scripts/v8-polyfill.js",
            "/META-INF/resources/webjars/ejs/2.4.1/ejs-v2.4.1/ejs.min.js",
            "/META-INF/resources/webjars/react/15.2.1/react.js",
            "/META-INF/resources/webjars/react/15.2.1/react-dom-server.js",
            "/static/Comment.js",
            "/static/CommentList.js",
            "/server-scripts/react-v8.js",
            "/server-scripts/v8-ejs-render.js"
    };

    private static ThreadLocal<V8> v8Holder = new ThreadLocal<V8>() {
        @Override
        protected V8 initialValue() {
            final V8 v8 = V8.createV8Runtime();
            for (final String script : SCRIPTS) {
                try {
                    v8.executeVoidScript(FileCopyUtils.copyToString(new InputStreamReader(new ClassPathResource(script).getInputStream())));
                } catch (Throwable t) {
                    System.err.println("failed to load v8 script: " + script);
                    t.printStackTrace(System.err);
                }
            }
            return v8;
        }
    };

    public static V8 getV8Runtime() throws Exception {
        return v8Holder.get();
    }


    public static V8Object toV8Object(V8 v8, Map map) {
        final V8Object v8obj = new V8Object(v8);
        map.forEach((key, value) -> addToV8Object(v8, v8obj, (String) key, value));
        return v8obj;
    }

    public static V8Array toV8Array(V8 v8, Iterable iterable) {
        final V8Array v8array = new V8Array(v8);
        iterable.forEach((value) -> pushToV8Array(v8, v8array, value));
        return v8array;
    }

    public static void addToV8Object(V8 v8, V8Object v8obj, String key, Object value) {
        if (value == null) {
            v8obj.addNull(key);
        } else if (value instanceof String) {
            v8obj.add(key, (String) value);
        } else if (value instanceof Integer) {
            v8obj.add(key, (Integer) value);
        } else if (value instanceof Number) {
            v8obj.add(key, ((Number) value).doubleValue());
        } else if (value instanceof Boolean) {
            v8obj.add(key, (Boolean) value);
        } else if (value instanceof V8Value) {
            v8obj.add(key, (V8Value) value);
        } else {
            v8obj.add(key, (V8Value) toV8Value(v8, value));
            //v8obj.addUndefined(key);
        }
    }

    public static void pushToV8Array(V8 v8, V8Array v8array, Object value) {
        if (value == null) {
            v8array.pushNull();
        } else if (value instanceof String) {
            v8array.push((String) value);
        } else if (value instanceof Integer) {
            v8array.push((Integer) value);
        } else if (value instanceof Number) {
            v8array.push(((Number) value).doubleValue());
        } else if (value instanceof Boolean) {
            v8array.push((Boolean) value);
        } else if (value instanceof V8Value) {
            v8array.push((V8Value) value);
        } else {
            v8array.push((V8Value) toV8Value(v8, value));
            //v8array.pushUndefined();
        }
    }

    public static Object toV8Value(V8 v8, Object obj) {
        if (obj == null) {
            return obj;
        } else if (obj instanceof String) {
            return obj;
        } else if (obj instanceof Number) {
            return obj;
        } else if (obj instanceof Boolean) {
            return obj;
        } else if (obj instanceof V8Value) {
            return obj;
        } else if (obj instanceof Map) {
            return toV8Object(v8, (Map) obj);
        } else if (obj instanceof Iterable) {
            return toV8Array(v8, (Iterable) obj);
        } else {
            try {
                final V8Object v8obj = new V8Object(v8);
                for (final PropertyDescriptor pd : BeanUtils.getPropertyDescriptors(obj.getClass())) {
                    // XXX: dirty hack!
                    if ("class".equals(pd.getName()) || "annotatedInterfaces".equals(pd.getName())) {
                        continue;
                    }
                    final Method reader = pd.getReadMethod();
                    if (reader != null) {
                        addToV8Object(v8, v8obj, pd.getName(), reader.invoke(obj));
                    }
                }
                return v8obj;
            } catch (Exception e) {
                return null;
            }
            //@Autowired
            //ObjectMapper objectMapper;
            //return toV8Object(objectMapper.convertValue(obj, Map.class));
        }
    }
}
