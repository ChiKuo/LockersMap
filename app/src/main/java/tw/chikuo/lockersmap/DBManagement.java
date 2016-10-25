package tw.chikuo.lockersmap;

import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import tw.chikuo.lockersmap.object.Locker;
import tw.chikuo.lockersmap.object.Stop;

/**
 * Created by Chi on 2016/10/9.
 */
public class DBManagement {

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference locationsRef = database.getReference("Locations");
    private DatabaseReference stopsRef = database.getReference("Stop");

    public static void addNewStop() {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference stopsRef = database.getReference("Stop");

        // TODO new data

        List<String> tagList = new ArrayList<>();
        tagList.add("");
        tagList.add("");

        Stop stop = new Stop();
        stop.setName("");
        stop.setDescription("");
        stop.setLowest_price("");
        stop.setNote("");
        stop.setPhoto("");
        stop.setTime("");
        stop.setTagList(tagList);
        stopsRef.child("key").setValue(stop);
    }

    public static void addLocker() {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference lockerRef = database.getReference("Locker");

        for (int i = 0; i < 17; i++){
            Locker locker = new Locker();
            locker.setSize("");
            locker.setCounts("");
            locker.setLocationId("mrt_");
            locker.setPrices("元/小時");
            lockerRef.push().setValue(locker);
        }
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
