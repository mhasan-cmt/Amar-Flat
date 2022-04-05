package com.teamphoenix.amarflat.util;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;

public class MapUtil {
    public static Circle drawCircleOnMap(GoogleMap map, LatLng center, Integer radius, Integer strokeColor, Integer fillColor) {
        Circle circle = map.addCircle(new CircleOptions()
                .center(center)
                .radius(radius)
                .strokeWidth(0)
                .strokeColor(strokeColor)
                .fillColor(fillColor));
        return circle;
    }
}
