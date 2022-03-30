package com.teamphoenix.amarflat.Model;

public class favorites {
    String favorite_id;
    String user_id;
    String property_id;

    public favorites(String favorite_id, String user_id, String property_id) {
        this.favorite_id = favorite_id;
        this.user_id = user_id;
        this.property_id = property_id;
    }

    public String getFavorite_id() {
        return favorite_id;
    }

    public void setFavorite_id(String favorite_id) {
        this.favorite_id = favorite_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getProperty_id() {
        return property_id;
    }

    public void setProperty_id(String property_id) {
        this.property_id = property_id;
    }
}
