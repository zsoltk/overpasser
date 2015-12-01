package hu.supercluster.overpasser.library.query;

import java.util.Locale;
import java.util.Set;

class OverpassQueryBuilder {
    private static final Locale LOCALE = Locale.US;
    private StringBuilder builder;

    public OverpassQueryBuilder() {
        builder = new StringBuilder();
    }

    public OverpassQueryBuilder append(String statement) {
        builder.append(statement);

        return this;
    }

    public OverpassQueryBuilder boundingBox(double lat1, double lon1, double lat2, double lon2) {
        builder.append(
                String.format(
                        LOCALE,
                        "(%s,%s,%s,%s)",
                        lat1, lon1,
                        lat2, lon2
                )
        );

        return this;
    }

    public OverpassQueryBuilder standaloneParam(String name) {
        return paramRel(name, "", "");
    }

    public OverpassQueryBuilder clause(String name, String value) {
        return paramRel(name, ":", value);
    }

    public OverpassQueryBuilder equals(String name, String value) {
        return paramRel(name, "=", value);
    }

    public OverpassQueryBuilder multipleValues(String name, Set<String> values) {
        StringBuilder joiner = new StringBuilder();
        for (String value : values) {
            joiner.append(value);
            joiner.append("|");
        }

        joiner.setLength(joiner.length() - 1);

        return paramRel(name, "~", joiner.toString());
    }

    public OverpassQueryBuilder notEquals(String name, String value) {
        return paramRel(name, "!=", value);
    }

    public OverpassQueryBuilder regexMatches(String name, String value) {
        return paramRel(name, "~", value);
    }

    public OverpassQueryBuilder regexDoesntMatch(String name, String value) {
        return paramRel(name, "!~", value);
    }

    private OverpassQueryBuilder paramRel(String name, String rel, String value) {
        String quotedValue = value.isEmpty() ? "" : String.format("\"%s\"", value);

        builder.append(String.format(LOCALE, "[\"%s\"%s%s]", name, rel, quotedValue));

        return this;
    }

    public String build() {
        return builder.toString();
    }
}
