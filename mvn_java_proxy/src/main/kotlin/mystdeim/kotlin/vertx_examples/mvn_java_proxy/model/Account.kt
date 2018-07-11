package mystdeim.kotlin.vertx_examples.mvn_java_proxy.model

import mystdeim.vertx_examples.mvn_java_proxy.model.Account

/**
 * A function providing a DSL for building [mystdeim.vertx_examples.mvn_java_proxy.model.Account] objects.
 *
 *
 * @param id 
 * @param name 
 *
 * <p/>
 * NOTE: This function has been automatically generated from the [mystdeim.vertx_examples.mvn_java_proxy.model.Account original] using Vert.x codegen.
 */
fun Account(
  id: Long? = null,
  name: String? = null): Account = mystdeim.vertx_examples.mvn_java_proxy.model.Account(io.vertx.core.json.JsonObject()).apply {

  if (id != null) {
    this.setId(id)
  }
  if (name != null) {
    this.setName(name)
  }
}

