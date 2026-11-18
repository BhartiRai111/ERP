package com.dataman.base.paging;

/** One column condition. {@code operand} joins it to the previous condition (ignored for the first). */
public record Filter(String column, Operator operator, String value, Operand operand) {

    public Filter(String column, Operator operator, String value) {
        this(column, operator, value, Operand.AND);
    }
}
