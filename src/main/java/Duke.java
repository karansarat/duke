import java.util.*;

public class Duke {
    public static final String dashLine = "    ____________________________________________________________\n";

    public static void store(Hashtable lookup, int count, Task task) {
        lookup.put(count, task);
        System.out.println(dashLine + "\tadded: " + task.str + "\n" + dashLine);
    }

    public static void list(Hashtable<Integer, Task> lookup, int count) {
        System.out.println(dashLine);
        for (int i = 1; i <= count; i++) {
            Task currTask = lookup.get(i);
            System.out.println("\t" + i + ". " + currTask.status() + currTask.str);
        }
        System.out.println(dashLine);
    }

    public static void markTaskDone(Hashtable<Integer, Task> lookup, int id) {
        Task doneTask = lookup.get(id);
        doneTask.markDone();
        System.out.println(dashLine + "\tNice! I've marked this task as done:\n");
        System.out.println("\t" + doneTask.status() + doneTask.str + "\n" + dashLine);
    }

    /** main. */
    public static void main(String[] args) {
        System.out.println(dashLine + "\tHello! I'm Duke\n\tWhat can I do for you?\n" + dashLine);
        Scanner input = new Scanner(System.in); // new input object
        Hashtable<Integer, Task> lookup = new Hashtable<Integer, Task>();
        int count = 0, id = 0;
        while (true) {
            Task newTask = new Task(input.nextLine());
            if (newTask.str.equals("bye")) {
                break;
            }
            if (newTask.str.equals("list")) {
                list(lookup, count);
            }
            else if (newTask.str.split(" ")[0].equals("done")) {
                id = Integer.parseInt(newTask.str.split(" ")[1]);
                markTaskDone(lookup, id);
            } else {
                store(lookup, ++count, newTask);
            }
        }
        System.out.println(dashLine + "\tBye. Hope to see you again soon!\n" + dashLine);
        input.close();
    }
}