package hu.supercluster.overpasser.library.output;

/**
 * Represents the possible output formats for the query result.
 * Formats "custom" and "popup" are not supported.
 *
 * @see <a href="http://wiki.openstreetmap.org/wiki/Overpass_API/Overpass_QL#Output_Format_.28out.29">
 *               http://wiki.openstreetmap.org/wiki/Overpass_API/Overpass_QL#Output_Format_.28out.29</a>
 */
public enum OutputFormat {
    /**
     * Request the result in JSON format
     */
    JSON,

    /**
     * Request the result in XML format
     */
    XML,

    /**
     * Request the result in CSV format
     */
    CSV,
}
