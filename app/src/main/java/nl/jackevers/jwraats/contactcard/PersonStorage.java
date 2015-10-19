package nl.jackevers.jwraats.contactcard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jwraats on 19/10/15.
 */
public class PersonStorage {
    public static ArrayList<Person> ITEMS = new ArrayList<Person>();
    public static Map<String, Person> ITEM_MAP = new HashMap<String, Person>();

    public static Person getPersonByEmail(String email){
        return ITEM_MAP.get(email);
    }

    public static void addItem(Person item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.email, item);
    }


}
