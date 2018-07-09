package mystdeim.vertx_examples.mvn_java_proxy.model;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

/**
 * @author Roman Novikov
 */
@DataObject
public class Account {

    private long id;
    private String name;

    public Account(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public JsonObject toJson() {
        JsonObject jo = new JsonObject();
        jo.put("id", id);
        jo.put("name", name);
        return jo;
    }

    public Account(JsonObject jo) {
        this.id = jo.getLong("id");
        this.name = jo.getString("name");
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
