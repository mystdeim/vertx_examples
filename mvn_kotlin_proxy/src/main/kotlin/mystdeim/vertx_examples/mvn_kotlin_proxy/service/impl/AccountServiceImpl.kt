package mystdeim.vertx_examples.mvn_kotlin_proxy.service.impl

import java.util.HashMap

import io.vertx.core.AsyncResult
import io.vertx.core.Future
import io.vertx.core.Handler
import io.vertx.core.Vertx
import mystdeim.vertx_examples.mvn_kotlin_proxy.model.Account
import mystdeim.vertx_examples.mvn_kotlin_proxy.service.AccountService

/**
 * @author Roman Novikov
 */
class AccountServiceImpl(vertx: Vertx) : AccountService {

    internal var accounts: MutableMap<Long, Account>

    init {
        accounts = HashMap()
    }

    override fun create(account: Account, handler: Handler<AsyncResult<Long>>) {
        accounts[account.id] = account
        handler.handle(Future.succeededFuture(account.id))
    }

    override fun get(id: Long, handler: Handler<AsyncResult<Account>>) {
        handler.handle(Future.succeededFuture(accounts[id]))
    }
}
