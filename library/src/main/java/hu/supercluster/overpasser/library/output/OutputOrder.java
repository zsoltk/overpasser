package hu.supercluster.overpasser.library.output;

/**
 * Represents the possible degrees of verbosity of the output.
 *
 * @see <a href="http://wiki.openstreetmap.org/wiki/Overpass_API/Overpass_QL#Print_.28out.29">
 *               http://wiki.openstreetmap.org/wiki/Overpass_API/Overpass_QL#Print_.28out.29</a>
 */
public enum OutputOrder {
    /**
     * Sort the output by object id
     */
    ASC,

    /**
     * Sort by quadtile index;
     * this is roughly geographical and significantly faster than order by ids.
     */
    QT,
}
