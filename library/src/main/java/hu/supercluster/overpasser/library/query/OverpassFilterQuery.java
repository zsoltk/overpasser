package hu.supercluster.overpasser.library.query;

import java.util.Set;

/**
 * Represents a sub-query to apply filters for type elements (node, way, relation, area),
 * tag values, bounding boxes, etc.
 *
 * @see <a href="http://wiki.openstreetmap.org/wiki/Overpass_API/Overpass_QL#Filters">
 *                http://wiki.openstreetmap.org/wiki/Overpass_API/Overpass_QL#Filters</a>
 */
public class OverpassFilterQuery extends AbstractOverpassSubQuery {
    private boolean separateNext;

    public OverpassFilterQuery(OverpassQuery parent) {
        super(parent);
        builder = new OverpassQueryBuilder();
        builder.append("; (");
    }

    /**
     * When supplying multiple type elements in a query, use this method to automatically
     * separate them with a ";" character added in-between.
     *
     * @return the current query object
     */
    public OverpassFilterQuery prepareNext() {
        separateNext = true;

        return this;
    }

    private void applySeparator() {
        if (separateNext) {
            builder.append("; ");
        }

        separateNext = false;
    }

    /**
     * Appends the string <i>node</i> to the current query.
     * 
     * @return the current query object
     */
    public OverpassFilterQuery node() {
        applySeparator();
        builder.append("node");

        return this;
    }

    /**
     * A convenience method filtering the output for a single amenity.
     * It's equivalent to calling {@link #equals(String, String)} with ("amenity", amenity)
     *
     * @see #amenities(Set)
     *
     * @param amenity the filter value
     *
     * @return the current query object
     */
    public OverpassFilterQuery amenity(String amenity) {
        builder.equals("amenity", amenity);

        return this;
    }

    /**
     * A convenience method filtering the output for multiple amenities.
     * It's equivalent to calling {@link #multipleValues(String, Set)} with ("amenity", amenities)
     * 
     * @param amenities the filter values
     *
     * @return the current query object
     */
    public OverpassFilterQuery amenities(Set<String> amenities) {
        builder.multipleValues("amenity", amenities);

        return this;
    }

    /**
     * Adds a <i>["name"=value]</i> filter tag to the current query.
     * 
     * @param name the filter name
     * @param value the filter value
     *
     * @return the current query object
     */
    public OverpassFilterQuery equals(String name, String value) {
        builder.equals(name, value);

        return this;
    }

    /**
     * Adds a <i>["name"~{value1}|{value2}|{value3}|...|{valueN}] filter tag to the current query
     * to add a filter matching for any of the given values.
     * 
     * @param name the filter name
     * @param values the filter value
     *
     * @return the current query object
     */
    public OverpassFilterQuery multipleValues(String name, Set<String> values) {
        builder.multipleValues(name, values);

        return this;
    }

    /**
     * Adds a <i>["name"!=value]</i> filter tag to the current query.
     * 
     * @param name the filter name
     * @param value the filter value
     *
     * @return the current query object
     */
    public OverpassFilterQuery notEquals(String name, String value) {
        builder.notEquals(name, value);

        return this;
    }

    /**
     * Adds a <i>["name"!~value]</i> filter tag to the current query.
     * 
     * @param name the filter name
     * @param value the filter value
     *
     * @return the current query object
     */
    public OverpassFilterQuery regexDoesntMatch(String name, String value) {
        builder.regexDoesntMatch(name, value);

        return this;
    }

    /**
     * Adds a <i>["name"~value]</i> filter tag to the current query.
     * 
     * @param name the filter name
     * @param value the filter value
     *
     * @return the current query object
     */
    public OverpassFilterQuery regexMatches(String name, String value) {
        builder.regexMatches(name, value);

        return this;
    }

    /**
     * Adds a <i>["name"] filter tag to the current query.
     * 
     * @param name the filter name
     *
     * @return the current query object
     */
    public OverpassFilterQuery standaloneParam(String name) {
        builder.standaloneParam(name);

        return this;
    }

    /**
     * Adds a (lat1,lon1,lat2,lon2) filter tag to the current query.
     * 
     * @param lat1 latitude of the northwest point of the bounding box
     * @param lon1 longitude of the northwest point of the bounding box
     * @param lat2 latitude of the southeast point of the bounding box
     * @param lon2 longitude of the southeast point of the bounding box
     *
     * @return the current query object
     */
    public OverpassFilterQuery boundingBox(double lat1, double lon1, double lat2, double lon2) {
        builder.boundingBox(lat1, lon1, lat2, lon2);

        return this;
    }

    /**
     * Closes the current query with the characters ";<;)" and returns the output as a string.
     *
     * @return the query as string
     */
    @Override
    public String build() {
        builder.append(";<;)");

        return builder.build();
    }

}
