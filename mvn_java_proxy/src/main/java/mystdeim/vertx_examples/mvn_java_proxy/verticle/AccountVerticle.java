package mystdeim.vertx_examples.mvn_java_proxy.verticle;

import static java.lang.System.out;

import io.vertx.core.AbstractVerticle;
import io.vertx.serviceproxy.ServiceBinder;
import mystdeim.vertx_examples.mvn_java_proxy.service.AccountService;

/**
 * @author Roman Novikov
 */
public class AccountVerticle extends AbstractVerticle {

    @Override
    public void start() {
        AccountService service = AccountService.createService(vertx);
        new ServiceBinder(vertx)
                .setAddress(AccountService.ADDRESS)
                .register(AccountService.class, service);
        out.println("AccountVerticle deployed!");
    }
}
