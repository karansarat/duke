import java.io.IOException;
import java.util.*;

/**
 * LVL 1 - enter non keyword to save task without tag
 * LVL 2 - list: List all available tasks
 * LVL 3 - done <task id>: Mark task as complete
 * LVL 4 - deadline <task name> /by <date time>, mark task with [D]
 * LVL 4 - todo <task name>, marks task with [T]
 * LVL 4 - event <event name> /at <date time>, mark task with [E]
 * LVL 5 - Validation of User Input
 */
public class Duke {
    public static final String dashLine = "\t____________________________________________________________\n";

    public static void store(Hashtable<Integer, Task> lookup, int count, Task task) throws IOException {
        lookup.put(count, task);
        System.out.println(dashLine + "\tGot it. I've added this task:\n\t\t" + task.status()
                + "\n\tNow you have " + count +  " task(s) in the list.\n" + dashLine);
        Writer saveData = new Writer(System.getProperty("user.dir") + "/log/duke.txt", true);
        saveData.write(task.status());
    }

    public static void delete(Hashtable<Integer, Task> lookup, int count) {
        System.out.println(dashLine + "\tNoted. I've removed this task:\n\t\t" + (lookup.get(count)).status()
                + "\n\tNow you have " + count +  " task(s) in the list.\n" + dashLine);
        lookup.remove(count);
    }
    // Error check list when empty list
    public static void list(Hashtable<Integer, Task> lookup, int count) {
        System.out.println(dashLine + "\tHere are the tasks in your list:\n");
        for (int i = 1; i <= count; i++) {
            Task currTask = lookup.get(i);
            System.out.println("\t" + i + ". " + currTask.status() + "\n");
        }
        System.out.println(dashLine);
    }

    public static void markTaskDone(Hashtable<Integer, Task> lookup, int id) throws IOException {
        Task doneTask = lookup.get(id);
        doneTask.markDone();
        store(lookup, id, doneTask);
        System.out.println(dashLine + "\tNice! I've marked this task as done:\n");
        System.out.println("\t" + doneTask.status() + "\n" + dashLine);
    }

    /** main. */
    public static void main(String[] args) throws IOException {
        Writer init = new Writer(System.getProperty("user.dir") + "/log/duke.txt", false);
        System.out.println(dashLine + "\tHello! I'm Duke\n\tWhat can I do for you?\n" + dashLine);
        Scanner input = new Scanner(System.in); // new input object
        Hashtable<Integer, Task> lookup = new Hashtable<Integer, Task>();
        int count = 0, id = 0;
        while (true) {
            String instr = input.nextLine();
            if (instr.equals("bye")) {
                break;
            }
            String[] keywords = instr.split(" ");
            if (instr.equals("list")) {
                list(lookup, count);
                continue;
            }
            else if (keywords[0].equals("done")) {
                id = Integer.parseInt(keywords[1]);
                markTaskDone(lookup, id);
                continue;
            } else if (keywords[0].equals("deadline")){
                Deadline newTask = new Deadline(instr);
                if (newTask.isValid) store(lookup, ++count, newTask);
            } else if (keywords[0].equals("event")) {
                Event newTask = new Event(instr);
                if (newTask.isValid) store(lookup, ++count, newTask);
            } else if (keywords[0].equals("todo")) {
                Todo newTask = new Todo(instr);
                if (newTask.isValid) store(lookup, ++count, newTask);
            } else if (keywords[0].equals("delete")) {
                delete(lookup, Integer.parseInt(keywords[1]));
            } else {
                System.out.println(dashLine + "\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" + dashLine);
            }
        }
        System.out.println(dashLine + "\tBye. Hope to see you again soon!\n" + dashLine);
        input.close();

    }
}