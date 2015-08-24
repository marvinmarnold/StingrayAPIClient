package org.stingraymappingproject.api.clientandroid.models;

/**
 * Created by Marvin Arnold on 24/08/15.
 */
public class StingrayReading {
    String id;
    String observed_at;

    public StingrayReading(String lat) {
        this.lat = lat;
    }

    public String getId() {
        return id;
    }

    public String getObserved_at() {
        return observed_at;
    }

    public String getLocation() {
        return location;
    }

    public String getThreat_level() {
        return threat_level;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getVersion() {
        return version;
    }

    public String getLat() {
        return lat;
    }

    String location;
    String threat_level;
    String updated_at;
    String created_at;
    String version;
    String lat;
}
