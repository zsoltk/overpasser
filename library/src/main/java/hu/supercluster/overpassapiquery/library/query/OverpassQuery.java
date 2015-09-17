package hu.supercluster.overpassapiquery.library.query;

import hu.supercluster.overpassapiquery.library.output.OutputFormat;
import hu.supercluster.overpassapiquery.library.output.OutputModificator;
import hu.supercluster.overpassapiquery.library.output.OutputOrder;
import hu.supercluster.overpassapiquery.library.output.OutputVerbosity;

import static hu.supercluster.overpassapiquery.library.output.OutputModificator.*;
import static hu.supercluster.overpassapiquery.library.output.OutputOrder.*;
import static hu.supercluster.overpassapiquery.library.output.OutputVerbosity.*;

public class OverpassQuery extends AbstractOverpassQuery {
    public OverpassQuery() {
        super();
    }

    public OverpassQuery format(OutputFormat outputFormat) {
        builder.clause("out", outputFormat.toString().toLowerCase());

        return this;
    }

    public OverpassQuery timeout(int timeout) {
        builder.clause("timeout", "" + timeout);

        return this;
    }

    public OverpassMapQuery mapQuery() {
        return new OverpassMapQuery(this);
    }

    public OverpassQuery output(int limit) {
        return output(BODY, CENTER, QT, limit);
    }

    public OverpassQuery output(OutputVerbosity verbosity, OutputModificator modificator, OutputOrder order, int limit) {
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
