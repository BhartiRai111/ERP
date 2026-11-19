package com.dataman.base.enums;

/** Enum with a numeric/short code and a display value (used to build SQL CASE expressions). */
public interface CodeValueEnum {

    int getCode();

    String getValue();
}
