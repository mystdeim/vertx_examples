package mystdeim.vertx_examples.mvn_kotlin_proxy.verticle

import java.lang.System.out

import io.vertx.core.AbstractVerticle
import io.vertx.serviceproxy.ServiceBinder
import mystdeim.vertx_examples.mvn_kotlin_proxy.service.AccountService
import mystdeim.vertx_examples.mvn_kotlin_proxy.service.AccountServiceFactory

/**
 * @author Roman Novikov
 */
class AccountVerticle : AbstractVerticle() {

    override fun start() {
        val service = AccountServiceFactory.createService(vertx)
        ServiceBinder(vertx)
                .setAddress(AccountServiceFactory.ADDRESS)
                .register(AccountService::class.java, service)
        out.println("AccountVerticle deployed!")
    }
}
