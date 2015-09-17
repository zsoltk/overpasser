package hu.supercluster.overpassapiquery.query;

abstract class AbstractOverApiSubQuery extends AbstractOverApiQuery {
    private OverApiQuery parent;

    public AbstractOverApiSubQuery(OverApiQuery parent) {
        this.parent = parent;
    }

    public final OverApiQuery end() {
        parent.onSubQueryResult(this);

        return parent;
    }
}
