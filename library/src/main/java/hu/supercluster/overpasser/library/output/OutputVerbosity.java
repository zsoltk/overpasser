package hu.supercluster.overpasser.library.output;

/**
 * Represents the possible degrees of verbosity of the output.
 *
 * @see <a href="http://wiki.openstreetmap.org/wiki/Overpass_API/Overpass_QL#Print_.28out.29">
 *               http://wiki.openstreetmap.org/wiki/Overpass_API/Overpass_QL#Print_.28out.29</a>
 */
public enum OutputVerbosity {
    /**
     * Print only the ids of the elements
     */
    IDS,

    /**
     * Print also the information necessary for geometry.
     * These are also coordinates for nodes and way and relation member ids for ways and relations.
     */
    SKEL,

    /**
     * Print all information necessary to use the data.
     * These are also tags for all elements and the roles for relation members.
     */
    BODY,

    /**
     * Print only ids and tags for each element and not coordinates or members.
     */
    TAGS,

    /**
     * Print everything known about the elements.
     * This includes additionally to body for all elements the version, changeset id,
     * timestamp and the user data of the user that last touched the object.
     */
    META,
}
