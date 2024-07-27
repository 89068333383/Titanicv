package com.example.Passenger.models;

public class FindStat {
    public int fare;
    public int survived;
    public int siblings;

    public int getFare(Integer statFare) {
        return fare;
    }

    public void setFare(int fare) {
        this.fare = fare;
    }

    public int getSurvived() {
        return survived;
    }

    public void setSurvived(int survived) {
        this.survived = survived;
    }

    public int getSiblings() {
        return siblings;
    }

    public void setSiblings(int siblings) {
        this.siblings = siblings;
    }

    @Override
    public String toString() {
        return "FindStat{" +
                "fare=" + fare +
                ", survived=" + survived +
                ", siblings=" + siblings +
                '}';
    }
}
