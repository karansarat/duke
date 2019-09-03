import java.util.Scanner;


public class Duke {
    public static final String dashLine = "    ____________________________________________________________\n";
    /** main. */
    public static void main(String[] args) {
        System.out.println(dashLine + "    Hello! I'm Duke\n    What can I do for you?\n" + dashLine);
        Scanner input = new Scanner(System.in); // new input object
        while (true) {
            String userIn = input.nextLine();
            if (userIn.equals("bye")) {
                break;
            }
            System.out.println(dashLine + "    " + userIn + "\n" + dashLine);
        }
        System.out.println(dashLine + "    Bye. Hope to see you again soon!\n" + dashLine);
    }
}