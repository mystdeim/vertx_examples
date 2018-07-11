package mystdeim.vertx_examples.mvn_java_proxy;

import static java.lang.System.out;

import io.vertx.core.Vertx;
import io.vertx.ext.bridge.PermittedOptions;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;
import io.vertx.ext.web.handler.sockjs.BridgeOptions;
import io.vertx.ext.web.handler.sockjs.SockJSHandler;
import io.vertx.kotlin.coroutines.CoroutineVerticle;
import mystdeim.vertx_examples.mvn_java_proxy.model.Account;
import mystdeim.vertx_examples.mvn_java_proxy.service.AccountService;
import mystdeim.vertx_examples.mvn_java_proxy.verticle.AccountVerticle;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Vertx vertx = Vertx.vertx();

        AccountService accountService = AccountService.createProxy(vertx, AccountService.ADDRESS);
        vertx.deployVerticle(AccountVerticle.class.getName(), handler -> {
            Account account = new Account(1, "test");
            accountService.create(account, accountRes -> {
                out.println("Account was created " + accountRes.result());
                accountService.get(account.getId(), accountGet -> {
                    out.println("Account was got " + accountGet.result());
                });
            });
        });

        Router router = Router.router(vertx);
        BridgeOptions opts = new BridgeOptions()
                .addInboundPermitted(new PermittedOptions()
                        .setAddress(AccountService.ADDRESS))
                .addOutboundPermitted(new PermittedOptions()
                        .setAddress(AccountService.ADDRESS));

        SockJSHandler ebHandler = SockJSHandler.create(vertx).bridge(opts);
        router.route("/eventbus/*").handler(ebHandler);
        router.route().handler(StaticHandler.create());
        vertx.createHttpServer().requestHandler(router).listen(8080);
        out.println("Web-api was exposed");

        // Test
        CoroutineVerticle verticle;
    }
}
