import javax.swing.*;
import java.util.*;

public class Task {
    protected String str, datetime, label;
    protected boolean isDone;

    public Task(String description) {
        this.str = description;
        this.isDone = false;
    }

    public String status() {
        //return (isDone ? "[\u2714] " : "[\u2718] "); //return tick or X symbols + taskname
        return (isDone ? "[tick] " : "[cross] ") + this.str; //return tick or X symbols + taskname
    }

    public int searchForKey(String[] arr, String key) {
        for (int i = 0; i < arr.length; ++i) {
            if (arr[i].equals(key)) return i;
        }
        return -1;
    }

    public void markDone() {
        this.isDone = true;
    }

}

class Deadline extends Task {

    protected String datetime;

    public void extractDate(String description, String key) {
        String[] instr = description.split(" ");
        int id = searchForKey(instr, key);
        String[] temp1 = Arrays.copyOfRange(instr, 1, id);
        this.str = String.join(" ", temp1);
        String[] temp2 = Arrays.copyOfRange(instr, id + 1, instr.length);
        this.datetime = String.join(" ", temp2);
    }

    public Deadline(String description) {
        super(description);
        extractDate(description, "/by");
    }

    public String status() {
        return "[D]" + super.status() + " (by: " + datetime + ")";
    }
}

class Event extends Task {

    protected String datetime;

    public void extractDate(String description, String key) {
        String[] instr = description.split(" ");
        int id = searchForKey(instr, key);
        String[] temp1 = Arrays.copyOfRange(instr, 1, id);
        this.str = String.join(" ", temp1);
        String[] temp2 = Arrays.copyOfRange(instr, id + 1, instr.length);
        this.datetime = String.join(" ", temp2);
    }

    public Event(String description) {
        super(description);
        extractDate(description, "/at");
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
