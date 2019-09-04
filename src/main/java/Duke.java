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

    public static void store(Hashtable lookup, int count, Task task) {
        lookup.put(count, task);
        System.out.println(dashLine + "\tGot it. I've added this task:\n\t\t" + task.status()
                + "\n\tNow you have " + count +  " task(s) in the list.\n" + dashLine);
    }

    public static void list(Hashtable<Integer, Task> lookup, int count) {
        System.out.println(dashLine + "\tHere are the tasks in your list:\n");
        for (int i = 1; i <= count; i++) {
            Task currTask = lookup.get(i);
            System.out.println("\t" + i + ". " + currTask.status() + "\n");
        }
        System.out.println(dashLine);
    }

    public static void markTaskDone(Hashtable<Integer, Task> lookup, int id) {
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
            } else {
                System.out.println(dashLine + "\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" + dashLine);
            }
        }
        System.out.println(dashLine + "\tBye. Hope to see you again soon!\n" + dashLine);
        input.close();

    }
}