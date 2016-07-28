spring-template-bench
=====================

to run:

```console
mvn spring-boot:run -Drun.profiles=<profile> &
```

profiles:

- thymeleaf
- velocity
- handlebars
- ejs
- ...

endpoints:

- server-side template on http://localhost:8080/
- react server-side rendering with nashorn on http://localhost:8080/react/nashorn
- react server-side rendering with j2v8 on http://localhost:8080/react/v8
- react client-side rendering on http://localhost:8080/react.html
- ...

see [bench.sh](bench.sh)

may the **SOURCE** be with you...
