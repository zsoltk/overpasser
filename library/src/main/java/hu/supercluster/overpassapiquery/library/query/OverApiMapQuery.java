package hu.supercluster.overpassapiquery.library.query;

import java.util.Set;

public class OverApiMapQuery extends AbstractOverApiSubQuery {
    private boolean separateNext;

    public OverApiMapQuery(OverApiQuery parent) {
        super(parent);
        builder = new OverApiQueryLanguageBuilder();
        builder.append("; (");
    }

    public OverApiMapQuery prepareNext() {
        separateNext = true;

        return this;
    }

    private void applySeparator() {
        if (separateNext) {
            builder.append("; ");
        }

        separateNext = false;
    }

    public OverApiMapQuery nodes() {
        applySeparator();
        builder.append("node");

        return this;
    }

    public OverApiMapQuery amenity(String amenity) {
        builder.equals("amenity", amenity);

        return this;
    }

    public OverApiMapQuery amenities(Set<String> amenities) {
        builder.multipleValues("amenity", amenities);

        return this;
    }

    public OverApiMapQuery equals(String name, String value) {
        builder.equals(name, value);

        return this;
    }

    public OverApiMapQuery multipleValues(String name, Set<String> values) {
        builder.multipleValues(name, values);

        return this;
    }

    public OverApiMapQuery notEquals(String name, String value) {
        builder.notEquals(name, value);

        return this;
    }

    public OverApiMapQuery regexDoesntMatch(String name, String value) {
        builder.regexDoesntMatch(name, value);

        return this;
    }

    public OverApiMapQuery regexMatches(String name, String value) {
        builder.regexMatches(name, value);

        return this;
    }

    public OverApiMapQuery standaloneParam(String name) {
        builder.standaloneParam(name);

        return this;
    }

    public OverApiMapQuery boundingBox(double lat1, double lon1, double lat2, double lon2) {
        builder.boundingBox(lat1, lon1, lat2, lon2);

        return this;
    }

    @Override
    public String build() {
        builder.append(";<;)");

        return builder.build();
    }

}
