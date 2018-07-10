package mystdeim.vertx_examples.mvn_kotlin_proxy

import io.vertx.core.Handler
import java.lang.System.out

import io.vertx.core.Vertx
import io.vertx.ext.bridge.PermittedOptions
import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.StaticHandler
import io.vertx.ext.web.handler.sockjs.BridgeOptions
import io.vertx.ext.web.handler.sockjs.SockJSHandler
import mystdeim.vertx_examples.mvn_kotlin_proxy.model.Account
import mystdeim.vertx_examples.mvn_kotlin_proxy.service.AccountService
import mystdeim.vertx_examples.mvn_kotlin_proxy.verticle.AccountVerticle

/**
 * Hello world!
 *
 */
object App {
    @JvmStatic
    fun main(args: Array<String>) {
        val vertx = Vertx.vertx()

        val accountService = AccountService.createProxy(vertx, AccountService.ADDRESS)
        vertx.deployVerticle(AccountVerticle::class.java.name) { handler ->
            val account = Account(1, "test")
            accountService.create(account, Handler { accountRes ->
                out.println("Account was created " + accountRes.result())
                accountService.get(account.id, Handler {
                    accountGet -> out.println("Account was got " + accountGet.result())
                })
            })
        }

        val router = Router.router(vertx)
        val opts = BridgeOptions()
                .addInboundPermitted(PermittedOptions()
                        .setAddress(AccountService.ADDRESS))
                .addOutboundPermitted(PermittedOptions()
                        .setAddress(AccountService.ADDRESS))

        val ebHandler = SockJSHandler.create(vertx).bridge(opts)
        router.route("/eventbus/*").handler(ebHandler)
        router.route().handler(StaticHandler.create())
        vertx.createHttpServer().requestHandler(router).listen(8080)
        out.println("Web-api was exposed")
    }
}
