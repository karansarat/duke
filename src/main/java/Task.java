public class Task {
    protected String str;
    protected boolean isDone;

    public Task(String description) {
        this.str = description;
        this.isDone = false;
    }

    public String status() {
        //return (isDone ? "[\u2714] " : "[\u2718] "); //return tick or X symbols
        return (isDone ? "[tick] " : "[cross] "); //return tick or X symbols
    }

    protected void markDone() {
        this.isDone = true;
    }

}
