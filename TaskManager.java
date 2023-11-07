import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskManager {

    private Scanner scan;
    private boolean premiumPlan;
    private String[] toDoList;
    private String[] notes;
    private ArrayList<String> removedTasks;
    private Messages message;
    private Task task;
    private File file;

    public TaskManager() {
        scan = new Scanner(System.in);
        premiumPlan = false;
        removedTasks = new ArrayList<>();
        message = new Messages();
        notes = new String[99];
        file = new File("tracker.txt");
    }

    public void menu() throws IOException {

        if (!premiumPlan) {
            message.getFreeUpgradeMessage();
            toDoList = new String[10];
            task = new Task(file, message);
        } else {
            toDoList = new String[30];
        }

        boolean programIsRunning = true;

        do {
            int count = 0;
            for (int i = 0; i < toDoList.length; i++) {
                if (toDoList[i] == null) {
                    count++;
                }
            }

            message.getFreeSpaces(count);

            message.showMenuMessage();

            switch (scan.next()) {
                case "1":
                    task.showToDoList(toDoList, notes);
                    break;
                case "2":
                    task.createTask(toDoList);
                    break;
                case "3":
                    task.markTaskAsCompleted(toDoList, notes);
                    break;
                case "4":
                    task.removeTaskAsCompleted(toDoList);
                    break;
                case "5":
                    task.editTask(toDoList, notes);
                    break;
                case "6":
                    task.deleteTask(toDoList, removedTasks, notes);
                    break;
                case "7":
                    task.organizeAlphabetically(toDoList);
                    break;
                case "8":
                    premiumPlan = task.upgradeToDoListPlan(toDoList, premiumPlan);
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
                case "9":
                    task.removeAllCompletedTasks(toDoList);
                    break;
                case "10":
                    task.restoreAllDeletedTasks(toDoList, removedTasks);
                    break;
                case "11":
                    task.readTracker();
                    break;
                case "12":
                    task.addNote(toDoList, notes);
                    break;
                case "0":
                    message.getCloseProgramMessage();
                    programIsRunning = false;
                    break;
                default:
                    message.getInvalidOption();
                    break;
            }

        } while (programIsRunning);
    }
}