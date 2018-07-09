package mystdeim.vertx_examples.mvn_java_proxy.service;

import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import mystdeim.vertx_examples.mvn_java_proxy.model.Account;
import mystdeim.vertx_examples.mvn_java_proxy.service.impl.AccountServiceImpl;

/**
 * @author Roman Novikov
 */
@ProxyGen
@VertxGen
public interface AccountService {

    void create(Account account, Handler<AsyncResult<Long>> handler);
    void get(long id, Handler<AsyncResult<Account>> handler);

    String ADDRESS = "account";

    static AccountService createService(Vertx vertx) {
        return new AccountServiceImpl(vertx);
    }

    static AccountService createProxy(Vertx vertx, String address) {
        return new AccountServiceVertxEBProxy(vertx, address);
    }
}
