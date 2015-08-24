package org.stingraymappingproject.api.clientandroid.models;

/**
 * Created by Marvin Arnold on 24/08/15.
 */
public class Factoid {
    public String getFact() {
        return fact;
    }
    String fact;

    public Factoid(String fact) {
        this.fact = fact;
    }
}
