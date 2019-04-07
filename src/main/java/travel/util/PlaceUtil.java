package travel.util;

import travel.obj.Place;

import java.util.*;

public class PlaceUtil {
    public static List getPlanList(Set<Place> places){
        List list = new LinkedList();

        for (Place p:places) {
            Set temp = new HashSet();
            temp.addAll(places);
            temp.remove(p);



        }



        return list;
    }


    public static double CountPlan(LinkedList<Place> finalList,Set<Place> placeSet){
        double length = 0;
        Map res = new HashMap();
        if (placeSet.size() > 2) {
            for (Place p:placeSet) {
                Set<Place> placeSet1 = new HashSet<>();
                placeSet1.addAll(placeSet);
                placeSet1.remove(p);
                 CountPlan(finalList, placeSet1);
            }
        }else {
            return length;
        }


        return length;
    }


    public static double getLength(Place p1,Place p2){
        return Math.sqrt((Double.parseDouble(p1.getLatitude()) - Double.parseDouble(p2.getLatitude()))
                + ((Double.parseDouble(p1.getLongitude()) - Double.parseDouble(p2.getLongitude()))));
    }

}
