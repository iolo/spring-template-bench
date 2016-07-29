package kr.iolo.toybox.springtemplatebench;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;
import org.springframework.boot.autoconfigure.velocity.VelocityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.script.ScriptTemplateConfigurer;
import org.springframework.web.servlet.view.script.ScriptTemplateViewResolver;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {
        ThymeleafAutoConfiguration.class,
        FreeMarkerAutoConfiguration.class,
        VelocityAutoConfiguration.class
})
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Configuration
    @Profile("thymeleaf")
    @Import(ThymeleafAutoConfiguration.class)
    public static class ThymeleafConfig {
    }

    @Configuration
    @Profile("freemarker")
    @Import(FreeMarkerAutoConfiguration.class)
    public static class FreemarkerConfig {
    }

    @Configuration
    @Profile("velocity")
    @Import(VelocityAutoConfiguration.class)
    public static class VelocityConfig {
    }

    @Configuration
    @Profile("jsp")
    public static class JspConfig {
    }

    @Configuration
    @Profile("handlebars")
    public static class HandlebarsConfig {
        @Bean
        public ScriptTemplateConfigurer scriptTemplateConfigurer() {
            ScriptTemplateConfigurer bean = new ScriptTemplateConfigurer();
            bean.setEngineName("nashorn");
            bean.setScripts(
                    "/server-scripts/nashorn-polyfill.js",
                    "/META-INF/resources/webjars/handlebars/4.0.5/handlebars.min.js",
                    "/server-scripts/nashorn-handlebars-render.js"
            );
            bean.setRenderFunction("render");
            bean.setSharedEngine(false);
            return bean;
        }

        @Bean
        public ViewResolver viewResolver() {
            ScriptTemplateViewResolver bean = new ScriptTemplateViewResolver();
            bean.setPrefix("classpath:/templates/handlebars/");
            bean.setSuffix(".hbs");
            return bean;
        }
    }

    @Configuration
    @Profile("ejs")
    public static class EjsConfig {

        @Bean
        public ScriptTemplateConfigurer scriptTemplateConfigurer() {
            ScriptTemplateConfigurer bean = new ScriptTemplateConfigurer();
            bean.setEngineName("nashorn");
            bean.setScripts(
                    "/server-scripts/nashorn-polyfill.js",
                    "/META-INF/resources/webjars/ejs/2.4.1/ejs-v2.4.1/ejs.min.js",
                    "/server-scripts/nashorn-ejs-render.js"
            );
            bean.setRenderFunction("render");
            bean.setSharedEngine(false);
            return bean;
        }

        @Bean
        public ViewResolver viewResolver() {
            ScriptTemplateViewResolver bean = new ScriptTemplateViewResolver();
            bean.setPrefix("classpath:/templates/ejs/");
            bean.setSuffix(".ejs");
            return bean;
        }
    }

    @Configuration
    @Profile("v8ejs")
    public static class V8EjsConfig {

        @Bean
        public ViewResolver viewResolver() {
            V8TemplateViewResolver bean = new V8TemplateViewResolver();
            bean.setPrefix("classpath:/templates/ejs/");
            bean.setSuffix(".ejs");
            return bean;
        }
    }

}
