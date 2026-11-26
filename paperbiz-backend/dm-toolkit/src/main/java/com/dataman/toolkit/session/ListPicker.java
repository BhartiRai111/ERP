package com.dataman.toolkit.session;

import java.util.List;
import java.util.function.Function;

/** The legacy "choose one" rule: the requested code if it is in the list, otherwise the only entry, otherwise none. */
final class ListPicker {

    private ListPicker() {
    }

    static <T> T pick(List<T> items, String requestedCode, Function<T, String> code) {
        if (requestedCode != null && !requestedCode.isBlank()) {
            for (T t : items) {
                if (requestedCode.equalsIgnoreCase(code.apply(t))) {
                    return t;
                }
            }
        }
        return items.size() == 1 ? items.get(0) : null;
    }

    /** SiteList / CostCenterList text of UserSite: codes wrapped in pipes, e.g. {@code |1|,|2|}. */
    static List<String> codes(String stored) {
        if (stored == null || stored.isBlank()) {
            return List.of();
        }
        return java.util.Arrays.stream(stored.split(","))
                .map(s -> s.replace("|", "").trim())
                .filter(s -> !s.isEmpty())
                .toList();
    }
}
