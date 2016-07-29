spring-template-bench
=====================

spring template benchmark with script template and react server-side-rendering et all

getting started:

```console
$ mvn spring-boot:run -Drun.profiles=<profile>
```

profiles:

- [thymeleaf]
- [freemarker]
- [velocity]
- [jsp]
- [handlebars] (with [ScriptTemplateView] with [nashorn] engine)
- [ejs] (with [ScriptTemplateView] using [nashorn] engine)
- [v8ejs] (with custom template view using [v8] only)
- ...

endpoints:

- server-side template on http://localhost:8080/
- [react] *server-side* rendering with [nashorn] on http://localhost:8080/react/nashorn
- [react] *server-side* rendering with [j2v8] on http://localhost:8080/react/v8
- [react] *better isomorphic* rendering(`v8ejs` profile only) with [j2v8] on http://localhost:8080/react/v8ejs
- [react] *client-side* rendering on http://localhost:8080/react.html
- ...

todos:

- more sophisticated [bench.sh](bench.sh) scripts
- more template engines
- ...

see also:

- https://jira.spring.io/browse/SPR-13795
- https://jira.spring.io/browse/SPR-13508
- https://speakerdeck.com/sdeleuze/isomorphic-templating-with-spring-boot-nashorn-and-react

may the **SOURCE** be with you...

[thymeleaf]:http://www.thymeleaf.org
[freemarker]:http://freemarker.org
[velocity]:http://velocity.apache.org
[jsp]:http://www.oracle.com/technetwork/java/javaee/jsp/index.html
[handlebars]:http://handlebarsjs.com
[ejs]:http://ejs.co
[ScriptTemplateView]:http://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/web/servlet/view/script/ScriptTemplateView.html
[nashorn]:http://openjdk.java.net/projects/nashorn/
[j2v8]:https://github.com/eclipsesource/J2V8
[react]:https://facebook.github.io/react/
[jruby]:http://jruby.org
[jython]:http://www.jython.org

