import java.util.*;


public class Search {
    public Iterator<Integer> matches;

    public static boolean containsIgnoreCase(String str, String subString) {
        return str.toLowerCase().contains(subString.toLowerCase());
    }

    public Search(Hashtable<Integer, Task> tasks, String substr) {
        int count = 0;
        Set<Integer> found = new HashSet();
        Set<Integer> keys = tasks.keySet();
        Iterator<Integer> it = keys.iterator();
        while (it.hasNext()) {
            Integer id = it.next();
            if (containsIgnoreCase(tasks.get(id).str, substr)) {
                found.add(id);
                count++;
            }
        }
        if (count == 0) throw new NoSuchElementException();
        matches = found.iterator();
    }
}
