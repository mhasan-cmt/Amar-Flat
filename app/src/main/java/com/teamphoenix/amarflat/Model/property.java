package com.teamphoenix.amarflat.Model;

public class property {
    String property_id;
    String title;
    String location;
    String area_size;
    String bedrooms;
    String bathroom;
    String description;
    String apartment_type;
    String purpose;
    String floor_plan;
    String contact_number;

    public property(String property_id, String title, String location, String area_size, String bedrooms, String bathroom, String description, String apartment_type, String purpose, String floor_plan, String contact_number) {
        this.property_id = property_id;
        this.title = title;
        this.location = location;
        this.area_size = area_size;
        this.bedrooms = bedrooms;
        this.bathroom = bathroom;
        this.description = description;
        this.apartment_type = apartment_type;
        this.purpose = purpose;
        this.floor_plan = floor_plan;
        this.contact_number = contact_number;
    }

    public String getProperty_id() {
        return property_id;
    }

    public void setProperty_id(String property_id) {
        this.property_id = property_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getArea_size() {
        return area_size;
    }

    public void setArea_size(String area_size) {
        this.area_size = area_size;
    }

    public String getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(String bedrooms) {
        this.bedrooms = bedrooms;
    }

    public String getBathroom() {
        return bathroom;
    }

    public void setBathroom(String bathroom) {
        this.bathroom = bathroom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getApartment_type() {
        return apartment_type;
    }

    public void setApartment_type(String apartment_type) {
        this.apartment_type = apartment_type;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getFloor_plan() {
        return floor_plan;
    }

    public void setFloor_plan(String floor_plan) {
        this.floor_plan = floor_plan;
    }

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }
}
