package com.app.model.preference;

public record Preference(int id, String name) {

    public static Preference parse(String line) {
        var splitted = line.split(" ");
        return new Preference(Integer.parseInt(splitted[0]), splitted[1]);
    }
}
