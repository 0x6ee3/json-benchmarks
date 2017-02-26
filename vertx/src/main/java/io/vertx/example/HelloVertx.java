package io.vertx.example;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;


public class HelloVertx {
    public static void main(String[] args) {
        HttpServer server = Vertx.vertx().createHttpServer();
        Router router = Router.router(Vertx.vertx());

        router.route("/hello").handler(context -> {
            context.response().putHeader("Content-Type", "text/plain");
            context.response().end("Hello World!");
        });

        router.route("/json").produces("application/json").handler(context -> {
            HelloMessage msg = new HelloMessage();
            msg.message = "Hello World!";

            context.response()
                .putHeader("Content-Type", "application/json")
                .end(Json.encode(msg));
        });

        server.requestHandler(router::accept).listen(3090);
    }

    public static class HelloMessage {
        public String message;
    }
}

