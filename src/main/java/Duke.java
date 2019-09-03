import java.util.*;


public class Duke {
    public static final String dashLine = "    ____________________________________________________________\n";

    public static void store(Hashtable lookup, int count, String userIn) {
        lookup.put(count, userIn);
        System.out.println(dashLine + "\tadded: " + userIn + "\n" + dashLine);
    }

    public static void list(Hashtable lookup, int count) {
        System.out.println(dashLine);
        for (int i = 1; i <= count; i++) {
            System.out.println("\t" + i + ". " + lookup.get(i));
        }
        System.out.println(dashLine);
    }
    /** main. */
    public static void main(String[] args) {
        System.out.println(dashLine + "\tHello! I'm Duke\n\tWhat can I do for you?\n" + dashLine);
        Scanner input = new Scanner(System.in); // new input object
        Hashtable<Integer, String> lookup = new Hashtable<Integer, String>();
        int count = 0;
        while (true) {
            String userIn = input.nextLine();
            if (userIn.equals("bye")) {
                break;
            }
            store(lookup, ++count, userIn);

            if (userIn.equals("list")) {
                list(lookup, count);
            }
        }
        System.out.println(dashLine + "\tBye. Hope to see you again soon!\n" + dashLine);
    }
}