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
 * LVL 6 - Delete <id>
 * LVL 7 - Saves task list to file
 * LVL 8 - Date and Time is now recognised
 * LVL 9 - find <id> searches for matches for string in task names
 */
public class Duke {
    public static Hashtable<Integer, Task> lookup;
    public static Search google;
    public static final String dashLine = "\t____________________________________________________________\n";

    public static void search(String substr) {
        int i = 0;


        try {
            google = new Search(lookup, substr);
        }
        catch (NoSuchElementException e) {
            System.out.println(dashLine + "\tSorry, no matches for '" + substr + "' found.\n" + dashLine);
            return;
        }
        System.out.println(dashLine + "\tHere are the matching tasks in your list:\n");
        while (google.matches.hasNext()) {
            System.out.println("\t" + ++i + ". " + lookup.get(google.matches.next()).status() + "\n" + dashLine);
        }
    }

    public static void store(int count, Task task) throws IOException {
        lookup.put(count, task);
        System.out.println(dashLine + "\tGot it. I've added this task:\n\t\t" + task.status()
                + "\n\tNow you have " + count +  " task(s) in the list.\n" + dashLine);
        Writer saveData = new Writer(System.getProperty("user.dir") + "/log/duke.txt", true);
        saveData.write(task.status());
    }


    public static void delete(int id, int count) {
        System.out.println(dashLine + "\tNoted. I've removed this task:\n\t\t" + (lookup.get(id)).status()
                + "\n\tNow you have " + count +  " task(s) in the list.\n" + dashLine);
        lookup.remove(id);
    }
    // Error check list when empty list
    public static void list(int count) {
        System.out.println(dashLine + "\tHere are the tasks in your list:\n");
        for (int i = 1; i <= count; i++) {
            Task currTask = lookup.get(i);
            System.out.println("\t" + i + ". " + currTask.status() + "\n");
        }
        System.out.println(dashLine);
    }

    public static void markTaskDone(int id) throws IOException {
        Task doneTask = lookup.get(id);
        doneTask.markDone();
        store(id, doneTask);
        System.out.println(dashLine + "\tNice! I've marked this task as done:\n");
        System.out.println("\t" + doneTask.status() + "\n" + dashLine);
    }

    /** main. */
    public static void main(String[] args) throws IOException {
        Writer init = new Writer(System.getProperty("user.dir") + "/log/duke.txt", false);
        System.out.println(dashLine + "\tHello! I'm Duke\n\tWhat can I do for you?\n" + dashLine);
        Scanner input = new Scanner(System.in); // new input object
        lookup = new Hashtable<Integer, Task>();
        int count = 0, id = 0;
        while (true) {
            String instr = input.nextLine();
            if (instr.equals("bye")) {
                break;
            }
            String[] keywords = instr.split(" ");
            if (instr.equals("list")) {
                list(count);
            }
            else if (keywords[0].equals("done")) {
                id = Integer.parseInt(keywords[1]);
                markTaskDone(id);
            } else if (keywords[0].equals("deadline")){
                Deadline newTask = new Deadline(instr);
                if (newTask.isValid) store(++count, newTask);
            } else if (keywords[0].equals("event")) {
                Event newTask = new Event(instr);
                if (newTask.isValid) store(++count, newTask);
            } else if (keywords[0].equals("todo")) {
                Todo newTask = new Todo(instr);
                if (newTask.isValid) store(++count, newTask);
            } else if (keywords[0].equals("delete")) { //Error check delete out of bounds
                delete(Integer.parseInt(keywords[1]), --count);
            } else if (keywords[0].equals("find")) {
                search(instr.replaceFirst("find ", ""));
            } else {
                System.out.println(dashLine + "\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" + dashLine);
            }
        }
        System.out.println(dashLine + "\tBye. Hope to see you again soon!\n" + dashLine);
        input.close();

    }
}