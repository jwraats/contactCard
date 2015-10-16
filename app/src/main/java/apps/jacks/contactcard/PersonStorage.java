package apps.jacks.contactcard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import apps.jacks.contactcard.Person;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p/>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class PersonStorage {

    /**
     * An array of sample (dummy) items.
     */
    public static List<Person> ITEMS = new ArrayList<Person>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static Map<String, Person> ITEM_MAP = new HashMap<String, Person>();

    static {
        // Add 3 sample items.
        addItem(new Person("jw.raats@sadasdsad.com", true, "Jan Willem", "Raats", ""));
        addItem(new Person("fabienne@sadasdsad.com", false, "Fabienne", "", ""));
        addItem(new Person("jack.evers@sadasdsad.com", true, "Jack", "Evers", ""));

    }

    public static Person getPersonByEmail(String email){
        return ITEM_MAP.get(email);
    }

    private static void addItem(Person item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.getEmail(), item);
    }
}
