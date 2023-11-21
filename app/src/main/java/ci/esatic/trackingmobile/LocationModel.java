package ci.esatic.trackingmobile;

import com.google.gson.annotations.SerializedName;

public class LocationModel {
/*
    @SerializedName("latitude")
*/
    private Float latitude;

/*
    @SerializedName("longitude")
*/
    private Float longitude;

    public LocationModel(Float latitude, Float longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}

