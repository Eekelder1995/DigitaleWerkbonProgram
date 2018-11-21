package main;

import java.io.Serializable;

public class Equipment implements Serializable {

    public String id;
    public String type;

    public Equipment(){
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
