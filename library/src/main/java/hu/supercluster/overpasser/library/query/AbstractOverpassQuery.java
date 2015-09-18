package hu.supercluster.overpasser.library.query;

abstract class AbstractOverpassQuery {
    protected OverpassQueryBuilder builder;

    AbstractOverpassQuery() {
        builder = new OverpassQueryBuilder();
    }

    public void onSubQueryResult(AbstractOverpassSubQuery subQuery) {
        builder.append(subQuery.build());
    }

    protected abstract String build();
}
