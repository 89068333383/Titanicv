package com.example.Passenger.models;

public enum Pclass {
    First("first"),
    Second("second"),
    Third("Third");

    private final String title;

    Pclass(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "Pclass{" +
                "title='" + title + '\'' +
                '}';
    }

    public static Pclass setTitle(int id) {
        switch (id) {
            case 1:
                return First;
            case 2:
                return Second;
            case 3:
                return Third;
            default:
                return null;
        }
    }
}
