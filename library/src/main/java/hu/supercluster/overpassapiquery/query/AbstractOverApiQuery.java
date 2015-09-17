package hu.supercluster.overpassapiquery.query;

abstract class AbstractOverApiQuery {
    protected OverApiQueryLanguageBuilder builder;

    AbstractOverApiQuery() {
        builder = new OverApiQueryLanguageBuilder();
    }

    public void onSubQueryResult(AbstractOverApiSubQuery subQuery) {
        builder.append(subQuery.build());
    }

    protected abstract String build();
}
