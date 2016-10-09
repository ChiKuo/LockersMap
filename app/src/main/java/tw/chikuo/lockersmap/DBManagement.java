package tw.chikuo.lockersmap;

import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chi on 2016/10/9.
 */
public class DBManagement {

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference locationsRef = database.getReference("Locations");
    private DatabaseReference stopsRef = database.getReference("Stops");

    public static void addNewStop() {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference stopsRef = database.getReference("Stops");

        // TODO new data

        List<String> tagList = new ArrayList<>();
        tagList.add("");
        tagList.add("");

        Stops stops = new Stops();
        stops.setName("");
        stops.setDescription("");
        stops.setLowest_price("");
        stops.setNote("");
        stops.setPhoto("");
        stops.setTime("");
        stops.setTagList(tagList);
        stopsRef.child("key").setValue(stops);
    }

    public static void addNewLocations() {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference locationsRef = database.getReference("Locations");

        GeoFire geoFire = new GeoFire(locationsRef);
        geoFire.setLocation("mrt_1",new GeoLocation(25.167817, 121.445560));
        geoFire.setLocation("mrt_2",new GeoLocation(25.046492, 121.517290));
        geoFire.setLocation("mrt_3",new GeoLocation(25.049258, 121.519521));
        geoFire.setLocation("mrt_4",new GeoLocation(25.049468, 121.510335));
        geoFire.setLocation("mrt_5",new GeoLocation(25.034053, 121.528934));
        geoFire.setLocation("mrt_6",new GeoLocation(25.042174, 121.508293));
        geoFire.setLocation("mrt_7",new GeoLocation(25.033058, 121.563186));
        geoFire.setLocation("mrt_8",new GeoLocation(25.055791, 121.484725));
    }

}
