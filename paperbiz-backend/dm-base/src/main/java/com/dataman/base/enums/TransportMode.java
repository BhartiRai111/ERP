package com.dataman.base.enums;

import com.dataman.base.util.Strings;

public enum TransportMode {

    BY_ROAD(1, "By Road", "Road"), BY_RAIL(2, "By Rail", "Rail"), BY_AIR(3, "By Air", "Air"), BY_SHIP(4, "By Ship", "Ship");

    private final int id;
    private final String value;
    private final String shortName;

    public int getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public String getShortName() {
        return shortName;
    }

    TransportMode(int id, String text, String shortNm) {
        this.id = id;
        this.value = text;
        this.shortName = shortNm;
    }

    public static TransportMode getViaKey(int id) {
        TransportMode algo = null;
        if (id == BY_AIR.getId()) {
            algo = BY_AIR;
        } else if (id == BY_ROAD.getId()) {
            algo = BY_ROAD;
        } else if (id == BY_RAIL.getId()) {
            algo = BY_RAIL;
        } else if (id == BY_SHIP.getId()) {
            algo = BY_SHIP;
        }
        return algo;
    }

    public static TransportMode getViaText(String text) {
        TransportMode algo = null;
        if (text.equalsIgnoreCase(BY_AIR.getValue())) {
            algo = BY_AIR;
        } else if (text.equalsIgnoreCase(BY_ROAD.getValue())) {
            algo = BY_ROAD;
        } else if (text.equalsIgnoreCase(BY_RAIL.getValue())) {
            algo = BY_RAIL;
        } else if (text.equalsIgnoreCase(BY_SHIP.getValue())) {
            algo = BY_SHIP;
        }
        return algo;
    }

    public static TransportMode getViaShortName(String shortNm) {
        TransportMode algo = null;
        if (shortNm.equalsIgnoreCase(BY_AIR.getShortName())) {
            algo = BY_AIR;
        } else if (shortNm.equalsIgnoreCase(BY_ROAD.getShortName())) {
            algo = BY_ROAD;
        } else if (shortNm.equalsIgnoreCase(BY_RAIL.getShortName())) {
            algo = BY_RAIL;
        } else if (shortNm.equalsIgnoreCase(BY_SHIP.getShortName())) {
            algo = BY_SHIP;
        }
        return algo;
    }
}
