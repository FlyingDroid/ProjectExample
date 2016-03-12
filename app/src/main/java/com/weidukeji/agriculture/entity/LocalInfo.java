package com.weidukeji.agriculture.entity;

/**
 * Created by admin on 2015/1/17.
 */
public class LocalInfo {
    private int _id;
    private String locProvience;
    private String locCity;
    private String longitude;
    private String latitude;

    public String getSpell() {
        return spell;
    }

    public void setSpell(String spell) {
        this.spell = spell;
    }

    private String spell;

    public LocalInfo() {
    }

    public LocalInfo(int _id, String locProvience, String locCity, String longitude, String latitude, String spell) {
        this._id = _id;
        this.locProvience = locProvience;
        this.locCity = locCity;
        this.longitude = longitude;
        this.latitude = latitude;
        this.spell = spell;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getLocProvience() {
        return locProvience;
    }

    public void setLocProvience(String locProvience) {
        this.locProvience = locProvience;
    }

    public String getLocCity() {
        return locCity;
    }

    public void setLocCity(String locCity) {
        this.locCity = locCity;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "LocalInfo{" +
                "_id=" + _id +
                ", locProvience='" + locProvience + '\'' +
                ", locCity='" + locCity + '\'' +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                ", spell='" + spell + '\'' +
                '}';
    }
}
