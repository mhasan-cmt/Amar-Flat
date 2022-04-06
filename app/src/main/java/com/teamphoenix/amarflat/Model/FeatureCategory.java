package com.teamphoenix.amarflat.Model;

public class FeatureCategory {
    String featureCategoryId;
    String featureCategoryName;

    public FeatureCategory(String featureCategoryId, String featureCategoryName) {
        this.featureCategoryId = featureCategoryId;
        this.featureCategoryName = featureCategoryName;
    }

    public String getFeatureCategoryId() {
        return featureCategoryId;
    }

    public void setFeatureCategoryId(String featureCategoryId) {
        this.featureCategoryId = featureCategoryId;
    }

    public String getFeatureCategoryName() {
        return featureCategoryName;
    }

    public void setFeatureCategoryName(String featureCategoryName) {
        this.featureCategoryName = featureCategoryName;
    }
}
