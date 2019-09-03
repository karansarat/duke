import java.util.*;

/**
 * LVL 1 - enter non keyword to save task without tag
 * LVL 2 - list: List all available tasks
 * LVL 3 - done <task id>: Mark task as complete
 * LVL 4 - deadline <task name> /by <date time>, mark task with [D]
 * LVL 4 - todo <task name>, marks task with [T]
 * LVL 4 - event <event name> /at <date time>, mark task with [E]
 */
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
            System.out.println("\t" + i + ". " + currTask.status());
        }
        System.out.println(dashLine);
    }

    public static void markTaskDone(Hashtable<Integer, Task> lookup, int id) {
        Task doneTask = lookup.get(id);
        doneTask.markDone();
        System.out.println(dashLine + "\tNice! I've marked this task as done:\n");
        System.out.println("\t" + doneTask.status() + dashLine);
    }

    public static void storeDeadline(Hashtable<Integer, Task> lookup, int id,) {
        Task doneTask = lookup.get(id);
        doneTask.markDone();
        System.out.println(dashLine + "\tNice! I've marked this task as done:\n");
        System.out.println("\t" + doneTask.status() + dashLine);
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
            String keywords[] = newTask.str.split(" ");
            if (newTask.str.equals("list")) {
                list(lookup, count);
            }
            else if (keywords[0].equals("done")) {
                id = Integer.parseInt(keywords[1]);
                markTaskDone(lookup, id);
            } else {
                store(lookup, ++count, newTask);
            }
        }
        System.out.println(dashLine + "\tBye. Hope to see you again soon!\n" + dashLine);
        input.close();

    }
}