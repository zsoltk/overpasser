package hu.supercluster.overpassapiquery.adapter;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class OverpassQueryResult {
    @SerializedName("elements")
    public List<Element> elements = new ArrayList<>();

    public static class Element {
        @SerializedName("type")
        public String type;

        @SerializedName("id")
        public long id;

        @SerializedName("lat")
        public double lat;

        @SerializedName("lon")
        public double lon;

        @SerializedName("tags")
        public Tags tags = new Tags();

        public static class Tags {
            @SerializedName("type")
            public String type;

            @SerializedName("amenity")
            public String amenity;

            @SerializedName("name")
            public String name;

            @SerializedName("phone")
            public String phone;

            @SerializedName("contact:email")
            public String contactEmail;

            @SerializedName("website")
            public String website;

            @SerializedName("addr:city")
            public String addressCity;

            @SerializedName("addr:postcode")
            public String addressPostCode;

            @SerializedName("addr:street")
            public String addressStreet;

            @SerializedName("addr:housenumber")
            public String addressHouseNumber;

            @SerializedName("wheelchair")
            public String wheelchair;

            @SerializedName("wheelchair:description")
            public String wheelchairDescription;

            @SerializedName("opening_hours")
            public String openingHours;

            @SerializedName("internet_access")
            public String internetAccess;

            @SerializedName("fee")
            public String fee;

            @SerializedName("operator")
            public String operator;

        }
    }
}

