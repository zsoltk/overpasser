package hu.supercluster.overpassapiquery.query;

import hu.supercluster.overpassapiquery.outputparam.OverPassOutputFormat;
import hu.supercluster.overpassapiquery.outputparam.OverPassOutputModificator;
import hu.supercluster.overpassapiquery.outputparam.OverPassOutputOrder;
import hu.supercluster.overpassapiquery.outputparam.OverPassOutputVerbosity;

import static hu.supercluster.overpassapiquery.outputparam.OverPassOutputModificator.*;
import static hu.supercluster.overpassapiquery.outputparam.OverPassOutputOrder.*;
import static hu.supercluster.overpassapiquery.outputparam.OverPassOutputVerbosity.*;

public class OverApiQuery extends AbstractOverApiQuery {

    public OverApiQuery() {
        super();
    }

    public OverApiQuery format(OverPassOutputFormat outputFormat) {
        builder.clause("out", outputFormat.toString().toLowerCase());

        return this;
    }

    public OverApiQuery timeout(int timeout) {
        builder.clause("timeout", "" + timeout);

        return this;
    }

    public OverApiMapQuery mapQuery() {
        return new OverApiMapQuery(this);
    }

    public OverApiQuery output(int limit) {
        return output(BODY, CENTER, QT, limit);
    }

    public OverApiQuery output(OverPassOutputVerbosity verbosity, OverPassOutputModificator modificator, OverPassOutputOrder order, int limit) {
        builder.append(String.format("; out %s %s %s %s",
                        verbosity.toString().toLowerCase(),
                        modificator.toString().toLowerCase(),
                        order.toString().toLowerCase(),
                        limit
                )
        );

        return this;
    }

    @Override
    public String build() {
        builder.append(";");

        return builder.build();
    }
}