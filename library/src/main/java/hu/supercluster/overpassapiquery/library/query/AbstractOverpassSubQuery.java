package hu.supercluster.overpassapiquery.library.query;

abstract class AbstractOverpassSubQuery extends AbstractOverpassQuery {
    private OverpassQuery parent;

    public AbstractOverpassSubQuery(OverpassQuery parent) {
        this.parent = parent;
    }

    public final OverpassQuery end() {
        parent.onSubQueryResult(this);

        return parent;
    }
}
