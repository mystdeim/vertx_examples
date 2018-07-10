package mystdeim.vertx_examples.mvn_kotlin_proxy.model

import io.vertx.codegen.annotations.DataObject
import io.vertx.core.json.JsonObject

/**
 * @author Roman Novikov
 */
@DataObject
class Account {

    var id: Long = 0
    var name: String? = null

    constructor(id: Long, name: String) {
        this.id = id
        this.name = name
    }

    fun toJson(): JsonObject {
        val jo = JsonObject()
        jo.put("id", id)
        jo.put("name", name)
        return jo
    }

    constructor(jo: JsonObject) {
        this.id = jo.getLong("id")!!
        this.name = jo.getString("name")
    }
}
