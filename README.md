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

result on my laptop:

- MacBook Pro (Retina, 15-inch, Mid 2014)
- 2.5 GHz Intel Core i7, : 16GB 1600 MHz DDR3, 500GB Apple SSD

|              |/             |/react/nashorn|/react/v8     |/react/v8ejs  |
|:-------------|-------------:|-------------:|-------------:|-------------:|
| ejs          |        84.40 |        30.10 |        86.39 |            - |   
| freemarker   |       216.40 |        24.44 |        82.59 |            - |   
| handlebars   |        26.59 |        38.56 |        95.62 |            - |   
| jsp          |       117.05 |        24.88 |        93.10 |            - |   
| thymeleaf    |       138.60 |        25.21 |        89.73 |            - |   
| velocity     |       248.15 |        24.91 |        83.98 |            - |   
| v8ejs        |       102.65 |        25.37 |       207.70 |       280.90 |

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

