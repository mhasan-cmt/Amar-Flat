package com.teamphoenix.amarflat.Model;

import java.io.Serializable;

public class property implements Serializable {
    String property_id;
    String title;
    String location;
    String area_size;
    String bedrooms;
    String bathroom;
    String description;
    String property_type;
    String purpose;
    String total_price;
    String email;
    String contact_number;
    String user_id;
    String time;

    public property(String property_id, String title, String location, String area_size, String bedrooms, String bathroom, String description, String property_type, String purpose, String total_price, String email, String contact_number, String user_id, String time) {
        this.property_id = property_id;
        this.title = title;
        this.location = location;
        this.area_size = area_size;
        this.bedrooms = bedrooms;
        this.bathroom = bathroom;
        this.description = description;
        this.property_type = property_type;
        this.purpose = purpose;
        this.total_price = total_price;
        this.email = email;
        this.contact_number = contact_number;
        this.user_id = user_id;
        this.time = time;
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

    public String getProperty_type() {
        return property_type;
    }

    public void setProperty_type(String property_type) {
        this.property_type = property_type;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getTotal_price() {
        return total_price;
    }

    public void setTotal_price(String total_price) {
        this.total_price = total_price;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
