package com.paperbiz.selftoolkit;

import java.util.Optional;

/** Nature of a measuring unit (legacy STEnum.UnitNature); the id is stored in {@code unitMast.nature}. */
public enum UnitNature {
    MASS(1, "Mass", "KG"), LENGTH(2, "Length", "M"), OTHER(3, "Other", "O"), VOLUME(4, "Volume", "V"), AREA(5, "Area", "A");

    private final int id;
    private final String value;
    private final String shortName;

    UnitNature(int id, String value, String shortName) {
        this.id = id;
        this.value = value;
        this.shortName = shortName;
    }

    public int getId() { return id; }
    public String getValue() { return value; }
    public String getShortName() { return shortName; }

    public static Optional<UnitNature> viaKey(int id) {
        for (UnitNature n : values()) {
            if (n.id == id) {
                return Optional.of(n);
            }
        }
        return Optional.empty();
    }
}
