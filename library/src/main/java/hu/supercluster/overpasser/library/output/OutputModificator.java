package hu.supercluster.overpasser.library.output;

/**
 * Represents the possible output modificators for derived information
 *
 * @see <a href="http://wiki.openstreetmap.org/wiki/Overpass_API/Overpass_QL#Output_Format_.28out.29">
 *               http://wiki.openstreetmap.org/wiki/Overpass_API/Overpass_QL#Output_Format_.28out.29</a>
 */
public enum OutputModificator {
    /**
     * Adds the bounding box of each element to the element.
     * For nodes this is equivalent to "geom".
     * For ways it is the enclosing bounding box of all nodes.
     * For relations it is the enclosing bounding box of all node and way members,
     * relations as members have no effect.
     */
    BB,

    /**
     * This adds the center of the above mentioned bounding box to ways and relations.
     * Note: The center point is not guaranteed to lie inside the polygon
     */
    CENTER,

    /**
     * Add the full geometry to each object.
     * This adds coordinates to each node, to each node member of a way or relation,
     * and it adds a sequence of "nd" members with coordinates to all relations.
     */
    GEOM,
}
