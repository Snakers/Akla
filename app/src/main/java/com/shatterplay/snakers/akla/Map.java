package com.shatterplay.snakers.akla;

/**
 * Created by snakers on 10/21/2017.
 */

class Map {
private String name;
   private double rate;
    private double lat;
  private   double lng;

    public Map(String name, double rate, double lat, double lng) {
        this.name = name;
        this.rate = rate;
        this.lat = lat;
        this.lng = lng;
    }

    public String getName() {

        return name;
    }

    public double getRate() {
        return rate;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

}
