package nl.jackevers.jwraats.contactcard;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jwraats on 19/10/15.
 */
public class PersonStorage {
    public static ArrayList<Person> ITEMS = new ArrayList<Person>();
    public static Map<String, Person> ITEM_MAP = new HashMap<String, Person>();

    public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static Person getPersonByEmail(String email){
        return ITEM_MAP.get(email);
    }

    public static void addItem(Person item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.email, item);
    }


}
