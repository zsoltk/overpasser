# overpasser
[![Build Status](https://travis-ci.org/zsoltk/overpasser.svg?branch=master)](https://travis-ci.org/zsoltk/overpasser)

Fluid Java interface to [OpenStreetMap](https://www.openstreetmap.org/) data through querying the [Overpass API](http://wiki.openstreetmap.org/wiki/Overpass_API). No more query string forging by hand!

### Example code
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
        .filterQuery()
            .node()
            .amenity("parking")
            .tagNot("access", "private")
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

### Language support
* Settings
  * [output format](http://wiki.openstreetmap.org/wiki/Overpass_API/Overpass_QL#Output_Format_.28out.29)
  * [timeout](http://wiki.openstreetmap.org/wiki/Overpass_API/Overpass_QL#timeout)
  * [output parameters](http://wiki.openstreetmap.org/wiki/Overpass_API/Overpass_QL#Print_.28out.29) - verbosity, modificators, sort order
* Filter queries
  * selecting nodes, ways, relations, areas
  * setting a [bounding box](http://wiki.openstreetmap.org/wiki/Overpass_API/Overpass_QL#Global_bounding_box_.28bbox.29) globally
  * setting a [bounding box](http://wiki.openstreetmap.org/wiki/Overpass_API/Overpass_QL#Bounding_box) for a filter query
* [Tag filter expressions](http://wiki.openstreetmap.org/wiki/Overpass_API/Overpass_QL#Filters)
    * check tag exists 
    * exact matching by tag value (and negation)
    * regex matching by tag value (and negation)
    * multiple tag values
  
### Modules
* [library](https://github.com/zsoltk/overpasser/tree/master/library): core functionality in a standalone module 
  * only Java dependencies
  * its purpose is to generate the string you can query the overpass-api server with
* [library-retrofit-adapter](https://github.com/zsoltk/overpasser/tree/master/library-retrofit-adapter): [Retrofit](http://square.github.io/retrofit/) adapter towards the overpass-api server
  * it will handle sending your generated query to the server and parsing the results
  * the results are returned in a [simple POJO class](https://github.com/zsoltk/overpasser/blob/master/library-retrofit-adapter/src/main/java/hu/supercluster/overpasser/adapter/OverpassQueryResult.java)
  * for further info regarding network communication and error handling, please refer to [Retrofit](http://square.github.io/retrofit/) documentation
  * uses Retrofit 2.0.0-beta4
* [library-retrofit-legacy-adapter](https://github.com/zsoltk/overpasser/tree/master/library-retrofit-legacy-adapter): for Retrofit 1.9
* [sample](https://github.com/zsoltk/overpasser/tree/master/sample): an Android sample application using both of the above modules to query for and display the public parking lots within the boundaries of the map

![](http://imgur.com/A4TGjjx.png)

### Download

The library and the retrofit adapter are available on Maven Central:

Library:
```groovy
dependencies {
    compile 'hu.supercluster:overpasser:0.2.0'
}
```
  
Retrofit adapter (for version 2.0-beta4):
```groovy
dependencies {
    compile 'hu.supercluster:overpasser-retrofit-adapter:0.2.0'
}
```

Retrofit legacy adapter (for version 1.9):
```groovy
dependencies {
    compile 'hu.supercluster:overpasser-retrofit-legacy-adapter:0.2.0'
}
```

**Please note**: This is not a production ready library. Use it at your own risk.

### Contributing

Contributions are welcome! Please check the [Contribution Guidelines](CONTRIBUTING.md).

### License

    Copyright 2015 Zsolt Kocsi

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
