package com.teamphoenix.amarflat.Model;

public class type_residential {
    String type_id;
    String title;

    public type_residential(String type_id, String title) {
        this.type_id = type_id;
        this.title = title;
    }

    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
