package org.insight;

import io.javalin.apibuilder.EndpointGroup;
import io.javalin.plugin.rendering.template.JavalinThymeleaf;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;


public class Server {

    public static final int DEFAULT_PORT = 7000;
    private Javalin server;

    public static void main(String[] args) {
        Server app = new Server();
        app.configureHttpServer();
        app.start();
    }



    private Javalin configureHttpServer(){
        server = Javalin.create(config -> {config.addStaticFiles("/", Location.CLASSPATH);});
        Routes.configure(this);
        return server;
    }

    public void routes(EndpointGroup group) {
        server.routes(group);
    }
    public void start(){
        server.start( DEFAULT_PORT );
    }


}