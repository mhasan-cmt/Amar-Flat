package com.teamphoenix.amarflat.Model;

public class FeatureNameValue {
    String featureName;
    String featureValue;

    public FeatureNameValue(String featureName, String featureValue) {
        this.featureName = featureName;
        this.featureValue = featureValue;
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public String getFeatureValue() {
        return featureValue;
    }

    public void setFeatureValue(String featureValue) {
        this.featureValue = featureValue;
    }
}
