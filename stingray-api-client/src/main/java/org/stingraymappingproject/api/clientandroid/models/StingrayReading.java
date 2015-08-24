package org.stingraymappingproject.api.clientandroid.models;

/**
 * Created by Marvin Arnold on 24/08/15.
 */
public class StingrayReading {
    String id;
    String observed_at;


    public String getId() {
        return id;
    }

    public String getObservedAt() {
        return observed_at;
    }

    public String getLocation() {
        return location;
    }

    public String getThreatLevel() {
        return threat_level;
    }

    public String getUpdatedAt() {
        return updated_at;
    }

    public String getCreatedAt() {
        return created_at;
    }

    public String getVersion() {
        return version;
    }


    String location;
    String threat_level;
    String updated_at;
    String created_at;
    String version;

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    String latitude;
    String longitude;
}
