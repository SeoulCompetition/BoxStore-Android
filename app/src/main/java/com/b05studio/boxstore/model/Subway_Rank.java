package com.b05studio.boxstore.model;

/**
 * Created by seungwoo on 2017-09-28.
 */

public class Subway_Rank {
    private int subway_rank_variance;
    private String subway_station;
    private double subway_volume;

    public int getSubway_rank_variance() {
        return subway_rank_variance;
    }

    public void setSubway_rank_variance(int subway_rank_variance) {
        this.subway_rank_variance = subway_rank_variance;
    }

    public String getSubway_station() {
        return subway_station;
    }

    public void setSubway_station(String subway_station) {
        this.subway_station = subway_station;
    }

    public double getSubway_volume() {
        return subway_volume;
    }

    public void setSubway_volume(double subway_volume) {
        this.subway_volume = subway_volume;
    }
}
