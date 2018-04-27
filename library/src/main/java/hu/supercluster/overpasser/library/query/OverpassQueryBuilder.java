package hu.supercluster.overpasser.library.query;

import java.util.Set;

interface OverpassQueryBuilder {
    OverpassQueryBuilder append(String statement);
    OverpassQueryBuilder boundingBox(double lat1, double lon1, double lat2, double lon2);
    OverpassQueryBuilder setting(String name, String value);
    OverpassQueryBuilder around(double radius);
    OverpassQueryBuilder around(double radius, double lat, double lon);
    OverpassQueryBuilder standaloneParam(String name);
    OverpassQueryBuilder clause(String name, String value);
    OverpassQueryBuilder equals(String name, String value);
    OverpassQueryBuilder notEquals(String name, String value);
    OverpassQueryBuilder regexMatches(String name, String value);
    OverpassQueryBuilder regexDoesntMatch(String name, String value);
    OverpassQueryBuilder multipleValues(String name, Set<String> values);
    String build();
}
