import java.util.Scanner;

public class Duke {
    /** main. */
    public static void main(String[] args) {
        System.out.println("    Hello! I'm Duke\n    What can I do for you?");
        Scanner input = new Scanner(System.in); // new input object
        String[] tasks = new String[100];
        int taskNum = 0;
        while (true) {
            String userIn = input.nextLine();
            if (userIn.equals("bye")) {
                break;
            } else if (userIn.equals("list")) {
                for (int i = 0; i < taskNum; i++) {
                    System.out.println("    " + Integer.toString(i + 1) + ". " + tasks[i]);
                }
                continue;
            }
            tasks[taskNum] = userIn;
            taskNum++;

            System.out.println("    added: " + userIn);
        }
        System.out.println("    Bye. Hope to see you again soon!");


    }
}