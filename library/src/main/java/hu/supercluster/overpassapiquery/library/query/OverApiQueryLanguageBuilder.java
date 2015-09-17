package hu.supercluster.overpassapiquery.library.query;

import java.util.Set;

class OverApiQueryLanguageBuilder {
    private StringBuilder builder;

    public OverApiQueryLanguageBuilder() {
        builder = new StringBuilder();
    }

    public OverApiQueryLanguageBuilder append(String statement) {
        builder.append(statement);

        return this;
    }

    public OverApiQueryLanguageBuilder boundingBox(double lat1, double lon1, double lat2, double lon2) {
        builder.append(String.format("(%s,%s,%s,%s)",
                        lat1, lon1,
                        lat2, lon2
                )
        );

        return this;
    }

    public OverApiQueryLanguageBuilder standaloneParam(String name) {
        return paramRel(name, "", "");
    }

    public OverApiQueryLanguageBuilder clause(String name, String value) {
        return paramRel(name, ":", value);
    }

    public OverApiQueryLanguageBuilder equals(String name, String value) {
        return paramRel(name, "=", value);
    }

    public OverApiQueryLanguageBuilder multipleValues(String name, Set<String> values) {
        StringBuilder joiner = new StringBuilder();
        for (String value : values) {
            joiner.append(value);
            joiner.append("|");
        }

        joiner.setLength(joiner.length() - 1);

        return paramRel(name, "~", joiner.toString());
    }

    public OverApiQueryLanguageBuilder notEquals(String name, String value) {
        return paramRel(name, "!=", value);
    }

    public OverApiQueryLanguageBuilder regexMatches(String name, String value) {
        return paramRel(name, "~", value);
    }

    public OverApiQueryLanguageBuilder regexDoesntMatch(String name, String value) {
        return paramRel(name, "!~", value);
    }

    private OverApiQueryLanguageBuilder paramRel(String name, String rel, String value) {
        String quotedValue = value.isEmpty() ? "" : String.format("\"%s\"", value);

        builder.append(String.format("[\"%s\"%s%s]", name, rel, quotedValue));

        return this;
    }

    public String build() {
        return builder.toString();
    }
}
