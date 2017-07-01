package io.vertx.example;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;

public class HelloVertx extends AbstractVerticle {

    public static class HelloMessage {
        public String message;
    }

    private Router buildRouter(Vertx vertx) {
        Router router = Router.router(vertx);

        router.route("/json").produces("application/json").handler(context -> {
            HelloMessage msg = new HelloMessage();
            msg.message = "Hello World!";

            context.response()
                .putHeader("Server", "vertx")
                .putHeader("Content-Type", "application/json")
                .end(Json.encode(msg));
        });

        router.route("/").handler(context -> {
            context.response()
                .putHeader("Server", "vertx")
                .putHeader("Content-Type", "text/plain")
                .end("Hello World!");
        });

        return router;
    }

    @Override
    public void start(Future<Void> fut) {
        vertx
            .createHttpServer()
            .requestHandler(buildRouter(vertx)::accept)
            .listen(config().getInteger("http.port", 8080));
    }
}

