import java.util.*;

public class Task {
    public static final String dashLine = "\t____________________________________________________________\n";
    protected String str, datetime, label;
    protected boolean isDone, isValid;

    public Task(String description) {
        this.str = description;
        this.isDone = false;
        this.isValid = true;
    }

    public String status() {
        //return (isDone ? "[\u2714] " : "[\u2718] "); //return tick or X symbols + taskname
        return (isDone ? "[tick] " : "[\u2718] ") + this.str; //return tick or X symbols + taskname
    }

    public int searchForKey(String[] arr, String key) {
        for (int i = 0; i < arr.length; ++i) {
            if (arr[i].equals(key)) return i;
        }
        this.isValid = false; // check if correct keyword /by or /at was used
        return -1;
    }

    public void extractDate(String description, String key) {
        String[] instr = this.str.split(" ");
        int id = searchForKey(instr, key);
        String[] temp1 = Arrays.copyOfRange(instr, 1, id);
        this.str = String.join(" ", temp1);
        String[] temp2 = Arrays.copyOfRange(instr, id + 1, instr.length);
        this.datetime = String.join(" ", temp2);
        if (this.str.length() == 1 || this.datetime.length() == 1) throw new IllegalArgumentException();
    }

    public void markDone() {
        this.isDone = true;
    }

}

class Deadline extends Task {

    public Deadline(String description) {
            super(description);
        try {
            extractDate(description, "/by");
        } catch (IllegalArgumentException e) {
            System.out.println(dashLine + "\t☹ OOPS!!! The arguments for deadline are invalid.\n" +
                    "\tdeadline <task name> /by <date time>\n" + dashLine);
            this.isValid = false;
        }
    }

    public String status() {
        return "[D]" + super.status() + " (by: " + datetime + ")";
    }
}

class Event extends Task {

    public Event(String description) {
        super(description);
        try {
            extractDate(description, "/at");
        } catch (IllegalArgumentException e) {
            System.out.println(dashLine + "\t☹ OOPS!!! The arguments for event are invalid.\n" +
                    "\tevent <event name> /at <date time>\n" + dashLine);
            this.isValid = false;
        }
    }

    public String status() {
        return "[E]" + super.status() + " (at: " + datetime + ")";
    }
}

class Todo extends Task {

    public Todo(String description) {
        super(description);
        this.str =  this.str.replaceFirst("todo ", "");
    }

    public String status() {
        return "[T]" + super.status();
    }
}
