package org.stingraymappingproject.api.clientandroid.models;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Date;

/**
 * Created by Marvin Arnold on 24/08/15.
 */
public class StingrayReading {

    public String getVersion() {
        return version;
    }

    int threatLevel; // required
    Date observedAt; // required
    double latitude; // required
    double longitude; // required

    public String getUniqueToken() {
        return uniqueToken;
    }

    String uniqueToken; // required
    String version; // required
    String location; // optional
    boolean isSelfGenerated = false;

    public StingrayReading(int threatLevel, Date observedAt, double latitude, double longitude, String uniqueToken, String version) {
        this.threatLevel = threatLevel;
        this.observedAt = observedAt;
        this.latitude = latitude;
        this.longitude = longitude;
        this.version = version;

        if(uniqueToken == null) uniqueToken = genRandomToken();
        this.uniqueToken = uniqueToken;
    }

    public String genRandomToken() {
        return new SessionIdentifierGenerator().nextSessionId();
    }

    public final class SessionIdentifierGenerator {
        private SecureRandom random = new SecureRandom();

        public String nextSessionId() {
            return new BigInteger(130, random).toString(32);
        }
    }
}
