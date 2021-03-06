package mystdeim.vertx_examples.mvn_kotlin_proxy

import io.vertx.core.Vertx
import io.vertx.ext.bridge.PermittedOptions
import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.StaticHandler
import io.vertx.ext.web.handler.sockjs.BridgeOptions
import io.vertx.ext.web.handler.sockjs.SockJSHandler
import io.vertx.kotlin.coroutines.awaitResult
import io.vertx.kotlin.coroutines.dispatcher
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import mystdeim.kotlin.vertx_examples.mvn_kotlin_proxy.service.createAwait
import mystdeim.kotlin.vertx_examples.mvn_kotlin_proxy.service.getAwait
import mystdeim.vertx_examples.mvn_kotlin_proxy.model.Account
import mystdeim.vertx_examples.mvn_kotlin_proxy.service.AccountService
import mystdeim.vertx_examples.mvn_kotlin_proxy.verticle.AccountVerticle


fun main(args: Array<String>) {
    val vertx = Vertx.vertx()
    val account = Account(1, "test")
    val accountService = AccountService.createProxy(vertx, AccountService.ADDRESS)

    GlobalScope.launch(vertx.dispatcher()) {
        val id = awaitResult<String> {
            vertx.deployVerticle(AccountVerticle::class.java.name, it)
        }
        println("Verticle was deployed with ID=${id}")

        accountService.createAwait(account)
        val accountRes = accountService.getAwait(account.id)
        println("Account2 was got ${accountRes}")
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
    println("Web-api was exposed")
}
