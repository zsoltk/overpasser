package hu.supercluster.overpasser.library.query;

import java.util.Locale;
import java.util.Set;

class OverpassQueryBuilderImpl implements OverpassQueryBuilder {
    private static final Locale LOCALE = Locale.US;
    private StringBuilder builder;

    public OverpassQueryBuilderImpl() {
        builder = new StringBuilder();
    }

    @Override
    public OverpassQueryBuilder append(String statement) {
        builder.append(statement);

        return this;
    }

    private OverpassQueryBuilder paramRel(String name, String rel, String value) {
        return paramRel(name, rel, value, true);
    }

    private OverpassQueryBuilder paramRel(String name, String rel, String value, boolean quoteName) {
        String quotedValue = value.isEmpty() ? "" : String.format("\"%s\"", value);

        String pattern = quoteName ? "[\"%s\"%s%s]" : "[%s%s%s]";

        return append(
                String.format(
                        LOCALE,
                        pattern,
                        name, rel, quotedValue
                )
        );
    }

    @Override
    public OverpassQueryBuilder boundingBox(double lat1, double lon1, double lat2, double lon2) {
        return append(
                String.format(
                        LOCALE,
                        "(%s,%s,%s,%s)",
                        lat1, lon1, lat2, lon2
                )
        );
    }

    @Override
    public OverpassQueryBuilder around(double radius) {
        return append(
                String.format(
                        LOCALE,
                        "(around:%s)",
                        radius
                )
        );
    }

    @Override
    public OverpassQueryBuilder around(double radius, double lat, double lon) {
        return append(
                String.format(
                        LOCALE,
                        "(around:%s,%s,%s)",
                        radius, lat, lon
                )
        );
    }

    @Override
    public OverpassQueryBuilder setting(String name, String value) {
        return paramRel(name, ":", value, false);
    }

    @Override
    public OverpassQueryBuilder standaloneParam(String name) {
        return paramRel(name, "", "");
    }

    @Override
    public OverpassQueryBuilder clause(String name, String value) {
        return paramRel(name, ":", value);
    }

    @Override
    public OverpassQueryBuilder equals(String name, String value) {
        return paramRel(name, "=", value);
    }

    @Override
    public OverpassQueryBuilder notEquals(String name, String value) {
        return paramRel(name, "!=", value);
    }

    @Override
    public OverpassQueryBuilder regexMatches(String name, String value) {
        return paramRel(name, "~", value);
    }

    @Override
    public OverpassQueryBuilder regexDoesntMatch(String name, String value) {
        return paramRel(name, "!~", value);
    }

    @Override
    public OverpassQueryBuilder multipleValues(String name, Set<String> values) {
        StringBuilder joiner = new StringBuilder();
        for (String value : values) {
            joiner.append(value);
            joiner.append("|");
        }

        joiner.setLength(joiner.length() - 1);

        return paramRel(name, "~", joiner.toString());
    }

    @Override
    public String build() {
        return builder.toString();
    }
}
