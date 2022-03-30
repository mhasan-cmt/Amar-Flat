package com.teamphoenix.amarflat.Model;

public class property_feature {
    String property_feature_id;
    String feature_id;
    String property_id;

    public property_feature(String property_feature_id, String feature_id, String property_id) {
        this.property_feature_id = property_feature_id;
        this.feature_id = feature_id;
        this.property_id = property_id;
    }

    public String getProperty_feature_id() {
        return property_feature_id;
    }

    public void setProperty_feature_id(String property_feature_id) {
        this.property_feature_id = property_feature_id;
    }

    public String getFeature_id() {
        return feature_id;
    }

    public void setFeature_id(String feature_id) {
        this.feature_id = feature_id;
    }

    public String getProperty_id() {
        return property_id;
    }

    public void setProperty_id(String property_id) {
        this.property_id = property_id;
    }
}
