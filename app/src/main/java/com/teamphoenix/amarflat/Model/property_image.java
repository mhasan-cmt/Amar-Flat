package com.teamphoenix.amarflat.Model;

public class property_image {
    String property_image_id;
    String property_id;
    String image;

    public property_image(String property_image_id, String property_id, String image) {
        this.property_image_id = property_image_id;
        this.property_id = property_id;
        this.image = image;
    }

    public String getProperty_image_id() {
        return property_image_id;
    }

    public void setProperty_image_id(String property_image_id) {
        this.property_image_id = property_image_id;
    }

    public String getProperty_id() {
        return property_id;
    }

    public void setProperty_id(String property_id) {
        this.property_id = property_id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
