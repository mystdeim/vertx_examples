package mystdeim.vertx_examples.mvn_java_proxy.service.impl;

import java.util.HashMap;
import java.util.Map;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import mystdeim.vertx_examples.mvn_java_proxy.model.Account;
import mystdeim.vertx_examples.mvn_java_proxy.service.AccountService;

/**
 * @author Roman Novikov
 */
public class AccountServiceImpl implements AccountService {

    public AccountServiceImpl(Vertx vertx) {
        accounts = new HashMap<>();
    }

    Map<Long, Account> accounts;

    public void create(Account account, Handler<AsyncResult<Long>> handler) {
        accounts.put(account.getId(), account);
        handler.handle(Future.succeededFuture(account.getId()));
    }

    public void get(long id, Handler<AsyncResult<Account>> handler) {
        handler.handle(Future.succeededFuture(accounts.get(id)));
    }
}
