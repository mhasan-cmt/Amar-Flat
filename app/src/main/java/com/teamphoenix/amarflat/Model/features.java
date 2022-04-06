package com.teamphoenix.amarflat.Model;

public class features {
    String feature_id;
    String feature_name;
    String feature_icon;
    String feature_category_id;

    public features(String feature_id, String feature_name, String feature_icon, String feature_category_id) {
        this.feature_id = feature_id;
        this.feature_name = feature_name;
        this.feature_icon = feature_icon;
        this.feature_category_id = feature_category_id;
    }

    public String getFeature_id() {
        return feature_id;
    }

    public void setFeature_id(String feature_id) {
        this.feature_id = feature_id;
    }

    public String getFeature_name() {
        return feature_name;
    }

    public void setFeature_name(String feature_name) {
        this.feature_name = feature_name;
    }

    public String getFeature_icon() {
        return feature_icon;
    }

    public void setFeature_icon(String feature_icon) {
        this.feature_icon = feature_icon;
    }

    public String getFeature_category_id() {
        return feature_category_id;
    }

    public void setFeature_category_id(String feature_category_id) {
        this.feature_category_id = feature_category_id;
    }
}
