package hu.supercluster.overpasser.library.query;

import java.util.Set;

public class OverpassMapQuery extends AbstractOverpassSubQuery {
    private boolean separateNext;

    public OverpassMapQuery(OverpassQuery parent) {
        super(parent);
        builder = new OverpassQueryBuilder();
        builder.append("; (");
    }

    public OverpassMapQuery prepareNext() {
        separateNext = true;

        return this;
    }

    private void applySeparator() {
        if (separateNext) {
            builder.append("; ");
        }

        separateNext = false;
    }

    public OverpassMapQuery nodes() {
        applySeparator();
        builder.append("node");

        return this;
    }

    public OverpassMapQuery amenity(String amenity) {
        builder.equals("amenity", amenity);

        return this;
    }

    public OverpassMapQuery amenities(Set<String> amenities) {
        builder.multipleValues("amenity", amenities);

        return this;
    }

    public OverpassMapQuery equals(String name, String value) {
        builder.equals(name, value);

        return this;
    }

    public OverpassMapQuery multipleValues(String name, Set<String> values) {
        builder.multipleValues(name, values);

        return this;
    }

    public OverpassMapQuery notEquals(String name, String value) {
        builder.notEquals(name, value);

        return this;
    }

    public OverpassMapQuery regexDoesntMatch(String name, String value) {
        builder.regexDoesntMatch(name, value);

        return this;
    }

    public OverpassMapQuery regexMatches(String name, String value) {
        builder.regexMatches(name, value);

        return this;
    }

    public OverpassMapQuery standaloneParam(String name) {
        builder.standaloneParam(name);

        return this;
    }

    public OverpassMapQuery boundingBox(double lat1, double lon1, double lat2, double lon2) {
        builder.boundingBox(lat1, lon1, lat2, lon2);

        return this;
    }

    @Override
    public String build() {
        builder.append(";<;)");

        return builder.build();
    }

}
