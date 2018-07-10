package mystdeim.vertx_examples.mvn_kotlin_proxy.verticle

import java.lang.System.out

import io.vertx.core.AbstractVerticle
import io.vertx.serviceproxy.ServiceBinder
import mystdeim.vertx_examples.mvn_kotlin_proxy.service.AccountService

/**
 * @author Roman Novikov
 */
class AccountVerticle : AbstractVerticle() {

    override fun start() {
        val service = AccountService.createService(vertx)
        ServiceBinder(vertx)
                .setAddress(AccountService.ADDRESS)
                .register(AccountService::class.java, service)
        out.println("AccountVerticle deployed!")
    }
}
