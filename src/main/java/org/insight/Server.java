package org.insight;

import io.javalin.apibuilder.EndpointGroup;
import io.javalin.plugin.rendering.template.JavalinThymeleaf;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;


public class Server {

    public static final int DEFAULT_PORT = 7000;
    private Javalin server;

    public static void main(String[] args) {
        Server app = new Server();
        app.configureHttpServer();
        app.start();
    }
    private TemplateEngine templateEngine() {
        TemplateEngine templateEngine = new TemplateEngine();
        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
        resolver.setPrefix("/html"); // No need for a prefix if your HTML files are in the root of the resources directory
        resolver.setSuffix(".html"); // Assuming your templates have .html extension
        resolver.setTemplateMode("HTML");
        resolver.setCharacterEncoding("UTF-8");
        templateEngine.setTemplateResolver(resolver);
        templateEngine.addDialect(new LayoutDialect());
        return templateEngine;
    }



    private void configureHttpServer(){
        JavalinThymeleaf.configure(templateEngine());
        server = Javalin.create(config -> {config.addStaticFiles("/html", Location.CLASSPATH);})
                .before(ctx->{
                    ctx.header("Access-Control-Allow-Origin","*");
                });
        Routes.configure(this);
    }

    public void routes(EndpointGroup group) {
        server.routes(group);
    }
    public void start(){
        server.start( DEFAULT_PORT );
    }


}