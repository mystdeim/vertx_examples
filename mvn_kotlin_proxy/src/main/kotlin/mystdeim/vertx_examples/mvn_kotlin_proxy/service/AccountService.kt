package mystdeim.vertx_examples.mvn_kotlin_proxy.service

import io.vertx.codegen.annotations.GenIgnore
import io.vertx.codegen.annotations.ProxyGen
import io.vertx.codegen.annotations.VertxGen
import io.vertx.core.AsyncResult
import io.vertx.core.Handler
import io.vertx.core.Vertx
import mystdeim.vertx_examples.mvn_kotlin_proxy.model.Account
import mystdeim.vertx_examples.mvn_kotlin_proxy.service.impl.AccountServiceImpl

/**
 * @author Roman Novikov
 */
@ProxyGen
@VertxGen
interface AccountService {

    fun create(account: Account, handler: Handler<AsyncResult<Long>>)
    operator fun get(id: Long, handler: Handler<AsyncResult<Account>>)

    @GenIgnore
    companion object {

        val ADDRESS = "account"

        fun createService(vertx: Vertx): AccountService {
            return AccountServiceImpl(vertx)
        }

        fun createProxy(vertx: Vertx, address: String): AccountService {
            return AccountServiceVertxEBProxy(vertx, address)
        }
    }
}