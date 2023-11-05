import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class ToDoList {

    static File file = new File("tracker.txt");
    static Tracker tracker = new Tracker(file);

    public static void main(String[] args) throws IOException {

        Scanner scan = new Scanner(System.in);
        boolean premiumPlan = false;
        String[] toDoList;
        ArrayList<String> removedTasks = new ArrayList<>();

        if (!premiumPlan) {
            System.out.println("\n\t\t\t\t\u001b[43;1m\u001b[38;5;15mIMPORTANT WARNING\u001b[0m\u001b[38;5;11m\nYou are currently using the Free Plan of ToDoList!\nYou can upgrade to Premium Plan in the upgrade menu!\u001b[0m");
            toDoList = new String[10];
        } else {
            toDoList = new String[30];
        }

        int userChoice = 0;

        do {

            int count = 0;
            for (int i = 0; i < toDoList.length; i++) {
                if (toDoList[i] == null) {
                    count++;
                }
            }

            System.out.println("\n\u001b[38;5;15mYou still have \u001b[38;5;11m" + count + "\u001b[38;5;15m free spaces on the list!\u001b[0m");

            System.out.println("\n\u001b[38;5;15m1 - Show ToDoList\u001b[0m");
            System.out.println("\u001b[38;5;15m2 - Create task\u001b[0m");
            System.out.println("\u001b[38;5;15m3 - Mark as completed\u001b[0m");
            System.out.println("\u001b[38;5;15m4 - Remove as completed\u001b[0m");
            System.out.println("\u001b[38;5;15m5 - Edit task\u001b[0m");
            System.out.println("\u001b[38;5;15m6 - Delete task\u001b[0m");
            System.out.println("\u001b[38;5;15m7 - Organize alphabetically\u001b[0m");
            System.out.println("\u001b[38;5;15m8 - Upgrade ToDoList Plan\u001b[0m");
            System.out.println("\u001b[38;5;15m9 - Delete all completed tasks\u001b[0m");
            System.out.println("\u001b[38;5;15m10 - Restore all deleted tasks\u001b[0m");
            System.out.println("\u001b[38;5;15m11 - Check tracker (history)\u001b[0m");
            System.out.println("\u001b[38;5;15m0 - Exit ToDoList\u001b[0m\n");
            System.out.print("\u001b[38;5;15mChoose a option: \u001b[0m");
            userChoice = scan.nextInt();

            scan.nextLine();

            switch (userChoice) {
                case 1:
                    showToDoList(toDoList);
                    break;
                case 2:
                    createTask(toDoList);
                    break;
                case 3:
                    markTaskAsCompleted(toDoList);
                    break;
                case 4:
                    removeTaskAsCompleted(toDoList);
                    break;
                case 5:
                    editTask(toDoList);
                    break;
                case 6:
                    deleteTask(toDoList, removedTasks);
                    break;
                case 7:
                    organizeAlphabetically(toDoList);
                    break;
                case 8:
                    premiumPlan = upgradeToDoListPlan(toDoList, premiumPlan);
                    if (premiumPlan) {
                        String[] tempToDoList = new String[30];

                        for (int i = 0; i < toDoList.length; i++) {
                            if (i < tempToDoList.length) {
                                tempToDoList[i] = toDoList[i];
                            } else {
                                break;
                            }
                        }

                        toDoList = tempToDoList;
                    }
                    break;
                case 9:
                    removeAllCompletedTasks(toDoList);
                    break;
                case 10:
                    restoreAllDeletedTasks(toDoList, removedTasks);
                    break;
                case 11:
                    tracker.readTracker();
                    break;
                case 0:
                    System.out.println("\n\u001b[38;5;9mClosing ToDoList program...\u001b[0m");
                    new FileWriter(file,false).close();
                    break;
                default:
                    System.out.println("\n\u001b[38;5;9mInvalid option!\u001b[0m");
                    break;
            }

        } while (userChoice != 0);
    }

    public static void showToDoList(String[] toDoList) {

        int count = 0;
        int countCompleted = 0;
        for (int i = 0; i < toDoList.length; i++) {
            if (toDoList[i] != null) {
                count++;
            }
        }

        if (count > 0) {
            System.out.println("\n\t\t\u001b[38;5;15mToDoList\u001b[0m");
            System.out.println("\u001b[38;5;8m------------------------\u001b[0m");
            for (int i = 0; i < toDoList.length; i++) {
                if (toDoList[i] != null) {
                    if (toDoList[i].contains(" ✅")) {
                        countCompleted++;
                        System.out.println("\u001b[38;5;7m" + i + ". \u001b[38;5;40m" + toDoList[i] + "\u001b[0m");
                    } else {
                        System.out.println("\u001b[38;5;7m" + i + ". \u001b[38;5;1m" + toDoList[i] + "\u001b[0m");
                    }
                }
            }
            System.out.println("\u001b[38;5;8m------------------------\u001b[0m");
        } else {
            System.out.println("\n\u001b[38;5;9mThe ToDoList is empty! You should create a task first.\u001b[0m");
        }

        if(count > 0) {

            DecimalFormat decimal = new DecimalFormat("0.00");

            int countUncompleted = count - countCompleted;
            double percentage = (double) countCompleted / (countCompleted + count) * 100;
            String decimalPercentage = decimal.format(percentage);

            System.out.println("\nUncompleted tasks: " + countUncompleted);
            System.out.println("Completed tasks: " + countCompleted);
            System.out.println("\nPercentage of completed tasks: " + decimalPercentage+"%");
        }
    }

    public static void createTask(String[] toDoList) throws IOException {
        Scanner scan = new Scanner(System.in);

        System.out.print("\n\u001b[38;5;15mCreate task: \u001b[0m");
        String userNewTask = scan.nextLine().trim();

        if (!userNewTask.isEmpty()) {
            boolean added = false;

            for (int i = 0; i < toDoList.length; i++) {
                if (toDoList[i] == null) {
                    toDoList[i] = userNewTask;
                    System.out.println("\n\u001b[38;5;10mThe task '\u001b[38;5;15m" + userNewTask + "\u001b[38;5;10m' was created!\u001b[0m");
                    tracker.writeOnTracker("Task: " + "'"+ userNewTask +"'" + " has been created on " + LocalDate.now() + " at "+ LocalTime.now()+"\n");
                    added = true;
                    break;
                }
            }

            if (!added) {
                System.out.println("\n\n\u001b[38;5;9mThe list is full! You don't have more space.\u001b[0m");
            }
        } else {
            System.out.println("\n\n\u001b[38;5;9mTask name cannot be empty.\u001b[0m");
        }
    }

    public static void markTaskAsCompleted(String[] toDoList) throws IOException {
        Scanner scan = new Scanner(System.in);

        int count = 0;
        for (int i = 0; i < toDoList.length; i++) {
            if (toDoList[i] != null) {
                System.out.println(i + " - " + toDoList[i]);
                count++;
            }
        }

        if (count != 0) {
            System.out.print("\n\u001b[38;5;15mChoose a task to mark as completed: \u001b[0m");
            int userChoice = scan.nextInt();

            if (userChoice >= 0 && userChoice < toDoList.length && toDoList[userChoice] != null) {
                if (toDoList[userChoice].contains(" ✅")) {
                    System.out.println("\n\u001b[38;5;9mThat task is already marked as completed!\u001b[0m");
                } else {
                    toDoList[userChoice] = toDoList[userChoice].concat(" ✅");

                    String completedTask = toDoList[userChoice];
                    for (int i = userChoice; i > 0; i--) {
                        toDoList[i] = toDoList[i - 1];
                    }
                    tracker.writeOnTracker("Task '"+ toDoList[userChoice] +"' has been marked as completed on "+ LocalDate.now() + " at " + LocalTime.now()+"\n");
                    toDoList[0] = completedTask;

                    System.out.println("\n\u001b[38;5;10mTask successfully marked as completed and moved to the top!\u001b[0m");
                }
            } else {
                System.out.println("\n\u001b[38;5;9mInvalid task option!\u001b[0m");
            }
        } else {
            System.out.println("\n\u001b[38;5;9mYou don't have tasks!\u001b[0m");
        }
    }


    public static void removeTaskAsCompleted(String[] toDoList) {
        Scanner scan = new Scanner(System.in);

        int existsCompletedTasks = 0;
        for (int i = 0; i < toDoList.length; i++) {
            if (toDoList[i] != null && toDoList[i].contains(" ✅")) {
                System.out.println(i + " - " + toDoList[i]);
                existsCompletedTasks++;
            }
        }

        if (existsCompletedTasks > 0) {

            System.out.print("\n\u001b[38;5;15mChoose a task to remove as completed: \u001b[0m");
            int userChoiceOfTaskToRemoveAsCompleted = scan.nextInt();

            if (toDoList[userChoiceOfTaskToRemoveAsCompleted] != null) {
                toDoList[userChoiceOfTaskToRemoveAsCompleted] = toDoList[userChoiceOfTaskToRemoveAsCompleted].replace(" ✅", "");
            } else {
                System.out.println("\n\u001b[38;5;9mInvalid task option!\u001b[0m");
            }
        } else {
            System.out.println("\n\u001b[38;5;9mYou don't have completed tasks!\u001b[0m");
        }
    }

    public static void removeAllCompletedTasks(String[] toDoList) throws IOException {

        boolean deletedOne = false;
        for (int i = 0; i < toDoList.length; i++) {
            if (toDoList[i] != null && toDoList[i].contains(" ✅")) {
                tracker.writeOnTracker("Completed task: " + "'"+ toDoList[i] +"'" + " has been deleted on " + LocalDate.now() + " at "+ LocalTime.now()+"\n");
                toDoList[i] = null;
                deletedOne = true;
            }
        }

        if(!deletedOne) {
            System.out.println("\n\u001b[38;5;9mYou don't have completed task!\u001b[0m");
        } else {
            System.out.println("\n\u001b[38;5;10mTasks deleted.\u001b[0m");
        }
    }

    public static void editTask(String[] toDoList) throws IOException {
        Scanner scan = new Scanner(System.in);

        int count = 0;
        for (int i = 0; i < toDoList.length; i++) {
            if (toDoList[i] != null) {
                System.out.println(i + " - " + toDoList[i]);
                count++;
            }
        }
        if (count != 0) {
            System.out.print("\n\u001b[38;5;15mChoose a task to edit: \u001b[0m");
            int userChoice = scan.nextInt();

            scan.nextLine();

            if (toDoList[userChoice] != null) {

                if (toDoList[userChoice].contains(" ✅")) {
                    toDoList[userChoice] = toDoList[userChoice].replace(" ✅", "");
                    System.out.println("\n\u001b[38;5;15mOld: " + toDoList[userChoice] + "\u001b[0m");
                    System.out.print("\u001b[38;5;15mNew: \u001b[0m");
                    String userEditTask = scan.nextLine();

                    tracker.writeOnTracker("The completed task " + "'"+ toDoList[userChoice] +"'" + " has been edited to " + "'" + userEditTask + "' on" + LocalDate.now() + " at "+ LocalTime.now()+"\n");
                    System.out.println("\n\u001b[38;5;10mThe task '\u001b[38;5;15m" + toDoList[userChoice] + "\u001b[38;5;10m' was changed to '\u001b[38;5;15m" + userEditTask + "\u001b[38;5;10m'!");
                    userEditTask = userEditTask.concat(" ✅");

                    toDoList[userChoice] = userEditTask;
                } else {
                    System.out.println("\n\u001b[38;5;15mOld: " + toDoList[userChoice] + "\u001b[0m");
                    System.out.print("\u001b[38;5;15mNew: \u001b[0m");
                    String userEditTask = scan.nextLine();
                    tracker.writeOnTracker("The uncompleted task " + "'"+ toDoList[userChoice] +"'" + " has been edited to " + "'" + userEditTask + "' on" + LocalDate.now() + " at "+ LocalTime.now()+"\n");

                    System.out.println("\n\u001b[38;5;10mThe task '\u001b[38;5;15m" + toDoList[userChoice] + "\u001b[38;5;10m' was changed to '\u001b[38;5;15m" + userEditTask + "\u001b[38;5;10m'!");
                    toDoList[userChoice] = userEditTask;
                }
            } else {
                System.out.println("\n\u001b[38;5;9mInvalid task option!\u001b[0m");
            }
        } else {
            System.out.println("\n\u001b[38;5;9mYou don't have tasks to edit!\u001b[0m");
        }
    }

    public static void deleteTask(String[] toDoList, ArrayList<String> removedTasks) {
        Scanner scan = new Scanner(System.in);

        int existedTasks = 0;
        for (int i = 0; i < toDoList.length; i++) {
            if (toDoList[i] != null) {
                System.out.println(i + " - " + toDoList[i]);
                existedTasks++;
            }
        }

        if (existedTasks > 0) {
            System.out.print("\n\u001b[38;5;15mChoose a task to delete: \u001b[0m");
            int userChoiceOfTaskToDelete = scan.nextInt();

            if (toDoList[userChoiceOfTaskToDelete] != null) {
                System.out.println("\u001b[38;5;10mThe task '\u001b[38;5;15m" + toDoList[userChoiceOfTaskToDelete] + "\u001b[38;5;10m' was successfully deleted!\u001b[0m");
                removedTasks.add(toDoList[userChoiceOfTaskToDelete]);
                toDoList[userChoiceOfTaskToDelete] = null;
            } else {
                System.out.println("\u001b[38;5;9mInvalid task option!\u001b[0m");
            }
        } else {
            System.out.println("\u001b[38;5;9mYou don't have tasks to delete!\u001b[0m");
        }
    }

    public static void restoreAllDeletedTasks(String[] toDoList, ArrayList<String> removedTasks) {
        boolean added = false;
        for (String el : removedTasks) {

                for (int i = 0; i < toDoList.length; i++) {
                    if (toDoList[i] == null) {
                        toDoList[i] = el;
                        removedTasks.remove(el);
                        added = true;
                        break;
                    }
                }
        }
        if(added) {
            System.out.println("\u001b[38;5;10mAll tasks restored.\u001b[0m");
        } else {
            System.out.println("\u001b[38;5;9mNothing to restore.\u001b[0m");
        }
    }

    public static void organizeAlphabetically(String[] toDoList) {
        int count = 0;
        for (int i = 0; i < toDoList.length; i++) {
            if (toDoList[i] != null) {
                count++;
            }
        }
        Arrays.sort(toDoList, 0, count);
    }

    public static boolean upgradeToDoListPlan(String[] toDoList, boolean premium) {
        Scanner scan = new Scanner(System.in);
        if (!premium) {
            System.out.println("\n\u001b[38;5;15mDo you want to buy Premium Plan? (yes or no)\u001b[0m");
            System.out.print("\u001b[38;5;15m> \u001b[0m");
            String userUpgradeOption = scan.next();

            switch (userUpgradeOption) {
                case "yes":
                    premium = true;
                    System.out.println("\n\u001b[38;5;10mCurrently plan setted to Premium! Thank you!\u001b[0m");
                    break;
                default:
                    premium = false;
                    System.out.println("\n\u001b[38;5;12mMaybe next time then...\u001b[0m");
                    break;
            }

            return premium;
        } else {
            System.out.println("\n\u001b[38;5;11mYour plan is already setted to Premium! You don't need to buy it again.\u001b[0m");
        }
        return premium;
    }

}