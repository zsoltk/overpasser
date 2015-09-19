# overpasser
Fluid Java interface to [OpenStreetMap](https://www.openstreetmap.org/) data through querying the [Overpass API](http://wiki.openstreetmap.org/wiki/Overpass_API). No more query string forging by hand!

## Example code
An Overpass query like this:
```
["out":"json"]["timeout":"30"];
(
    node
        ["amenity"="parking"]
        ["access"!="private"]
        (47.48047027491862,19.039797484874725,47.51331674014172,19.07404761761427);
        <;
);
out body center qt 100;
```

...can be expressed like:
```java
String query = new OverpassQuery()
        .format(JSON)
        .timeout(30)
        .mapQuery()
            .nodes()
            .amenity("parking")
            .notEquals("access", "private")
            .boundingBox(
                47.48047027491862, 19.039797484874725,
                47.51331674014172, 19.07404761761427
            )
        .end()
        .output(OutputVerbosity.BODY, OutputModificator.CENTER, OutputOrder.QT, 100)
        .build()
;
```

...or you can just use ```.output(100)``` at the end to use the defaults.

## Language support
* Settings
  * [output format](http://wiki.openstreetmap.org/wiki/Overpass_API/Overpass_QL#Output_Format_.28out.29) - currently JSON only
  * [timeout](http://wiki.openstreetmap.org/wiki/Overpass_API/Overpass_QL#timeout)
  * [output parameters](http://wiki.openstreetmap.org/wiki/Overpass_API/Overpass_QL#Print_.28out.29) - verbosity, modificators, sort order
* [Filters](http://wiki.openstreetmap.org/wiki/Overpass_API/Overpass_QL#Filters)
  * standalone filter 
  * exact filter matching by value (and negation)
  * regex filter matching by value (and negation)
  * multiple values
* Map queries
  * selecting nodes
  * setting [bounding box](http://wiki.openstreetmap.org/wiki/Overpass_API/Overpass_QL#Bounding_box)
  
## Modules
* [library](https://github.com/zsoltk/overpasser/tree/master/library) - Core functionality in a standalone module: only Java dependencies
* [library-retrofit-adapter](https://github.com/zsoltk/overpasser/tree/master/library-retrofit-adapter) - [Retrofit](http://square.github.io/retrofit/) adapter towards the overpass-api server
* [sample](https://github.com/zsoltk/overpasser/tree/master/sample) - An Android sample using both of the above

![](http://imgur.com/A4TGjjx.png)
  
