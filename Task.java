import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Task {

    private Scanner scan;
    private Tracker tracker;
    private Messages message;

    public Task(File file, Messages message) {
        scan = new Scanner(System.in);
        tracker = new Tracker(file);
        this.message = message;
    }

    public void showToDoList(String[] toDoList, String[] notes) {

        int count = 0;
        int countCompleted = 0;
        for (int i = 0; i < toDoList.length; i++) {
            if (toDoList[i] != null) {
                count++;
            }
        }

        if (count > 0) {
            message.getToDoListHeadline();
            for (int i = 0; i < toDoList.length; i++) {
                if (toDoList[i] != null) {
                    if (toDoList[i].contains(" ✅")) {
                        countCompleted++;
                        System.out.print("\u001b[38;5;7m" + i + ". \u001b[38;5;40m" + toDoList[i] + "\u001b[0m");
                        if (notes[i] != null) {
                            System.out.print(" | Note: " + notes[i] + "\n");
                        }
                    } else {
                        System.out.print("\u001b[38;5;7m" + i + ". \u001b[38;5;1m" + toDoList[i] + "\u001b[0m");
                        if (notes[i] != null) {
                            System.out.print(" | Note: " + notes[i] + "\n");
                        } else {
                            System.out.print("\n");
                        }
                    }
                }
            }
            System.out.println("\u001b[38;5;8m------------------------\u001b[0m");
        } else {
            message.getToDoListEmpty();
        }

        if (count > 0) {

            DecimalFormat decimal = new DecimalFormat("0.00");

            int countUncompleted = count - countCompleted;
            double percentage = (double) countCompleted / (countCompleted + count) * 100;
            String decimalPercentage = decimal.format(percentage);

            message.getUncompletedAndCompletedMessage(countUncompleted, countCompleted, decimalPercentage);
        }
    }

    public void createTask(String[] toDoList) throws IOException {

        message.getCreateTaskMessage();
        String userNewTask = scan.nextLine().trim() + "\u001b[0m | Created on " + LocalDate.now();

        if (!userNewTask.isEmpty()) {
            boolean added = false;

            for (int i = 0; i < toDoList.length; i++) {
                if (toDoList[i] == null) {
                    toDoList[i] = userNewTask;
                    message.getCreatedTaskMessage(userNewTask);
                    tracker.writeOnTracker("Task: " + "'" + userNewTask + "'" + " has been created on " + LocalDate.now() + " at " + LocalTime.now() + "\n");
                    added = true;
                    break;
                }
            }

            if (!added) {
                message.getListFullMessage();
            }
        } else {
            message.getEmptyTask();
        }
    }

    public void markTaskAsCompleted(String[] toDoList, String[] notes) throws IOException {
        Scanner scan = new Scanner(System.in);

        showToDoList(toDoList, notes);

        if (toDoList.length != 0) {
            message.getTaskToCompleteMessage();
            int userChoice = scan.nextInt();

            if (userChoice >= 0 && userChoice < toDoList.length && toDoList[userChoice] != null) {
                if (toDoList[userChoice].contains(" ✅")) {
                    message.getTaskAlreadyCompletedMessage();
                } else {
                    toDoList[userChoice] = toDoList[userChoice].concat(" ✅\n");

                    String completedTask = toDoList[userChoice];
                    for (int i = userChoice; i > 0; i--) {
                        toDoList[i] = toDoList[i - 1];
                    }
                    tracker.writeOnTracker("Task '" + toDoList[userChoice] + "' has been marked as completed on " + LocalDate.now() + " at " + LocalTime.now() + "\n");
                    toDoList[0] = completedTask;

                    message.getTaskCompletedMessage();
                }
            } else {
                message.getInvalidOption();
            }
        } else {
           message.getDontHaveTasksMessage();
        }
    }

    public void removeTaskAsCompleted(String[] toDoList) {
        Scanner scan = new Scanner(System.in);

        int existsCompletedTasks = 0;
        for (int i = 0; i < toDoList.length; i++) {
            if (toDoList[i] != null && toDoList[i].contains(" ✅")) {
                System.out.println(i + " - " + toDoList[i]);
                existsCompletedTasks++;
            }
        }

        if (existsCompletedTasks > 0) {

            message.getChooseTaskToRemoveAsCompletedMessage();
            int userChoiceOfTaskToRemoveAsCompleted = scan.nextInt();

            if (toDoList[userChoiceOfTaskToRemoveAsCompleted] != null) {
                toDoList[userChoiceOfTaskToRemoveAsCompleted] = toDoList[userChoiceOfTaskToRemoveAsCompleted].replace(" ✅", "");
            } else {
                message.getInvalidOption();
            }
        } else {
            message.getDontHaveAnyCompletedTask();
        }
    }

    public void editTask(String[] toDoList, String[] notes) throws IOException {
        Scanner scan = new Scanner(System.in);

        showToDoList(toDoList, notes);
        if (toDoList.length != 0) {
            message.getTaskToEditMessage();
            int userChoice = scan.nextInt();

            scan.nextLine();

            if (toDoList[userChoice] != null) {

                if (toDoList[userChoice].contains(" ✅")) {
                    toDoList[userChoice] = toDoList[userChoice].replace(" ✅", "");
                    System.out.println("\n\u001b[38;5;15mOld: " + toDoList[userChoice] + "\u001b[0m");
                    message.getNewMessage();
                    String userEditTask = scan.nextLine() + " ✅\u001b[0m | Modified on "+LocalDate.now();

                    tracker.writeOnTracker("The completed task " + "'"+ toDoList[userChoice] +"'" + " has been edited to " + "'" + userEditTask + "' on" + LocalDate.now() + " at "+ LocalTime.now()+"\n");
                    System.out.println("\n\u001b[38;5;10mThe task '\u001b[38;5;15m" + toDoList[userChoice] + "\u001b[38;5;10m' was changed to '\u001b[38;5;15m" + userEditTask + "\u001b[38;5;10m'!");

                    toDoList[userChoice] = userEditTask;
                } else {
                    System.out.println("\n\u001b[38;5;15mOld: " + toDoList[userChoice] + "\u001b[0m");
                    message.getNewMessage();
                    String userEditTask = scan.nextLine() + "\u001b[0m | Modified on "+ LocalDate.now();
                    tracker.writeOnTracker("The uncompleted task " + "'"+ toDoList[userChoice] +"'" + " has been edited to " + "'" + userEditTask + "' on" + LocalDate.now() + " at "+ LocalTime.now()+"\n");

                    System.out.println("\n\u001b[38;5;10mThe task '\u001b[38;5;15m" + toDoList[userChoice] + "\u001b[38;5;10m' was changed to '\u001b[38;5;15m" + userEditTask + "\u001b[38;5;10m'!");
                    toDoList[userChoice] = userEditTask;
                }
            } else {
                message.getInvalidOption();
            }
        } else {
            message.getDontHaveAnyTaskToEditMessage();
        }
    }

    public void deleteTask(String[] toDoList, ArrayList<String> removedTasks, String[] notes) {
        Scanner scan = new Scanner(System.in);

        int existedTasks = 0;
        for (int i = 0; i < toDoList.length; i++) {
            if (toDoList[i] != null) {
                System.out.println(i + " - " + toDoList[i]);
                existedTasks++;
            }
        }

        if (existedTasks > 0) {
            message.getMessageToDeleteMessage();
            int userChoiceOfTaskToDelete = scan.nextInt();

            if (toDoList[userChoiceOfTaskToDelete] != null) {
                notes[userChoiceOfTaskToDelete] = null;
                System.out.println("\u001b[38;5;10mThe task '\u001b[38;5;15m" + toDoList[userChoiceOfTaskToDelete] + "\u001b[38;5;10m' was successfully deleted!\u001b[0m");
                removedTasks.add(toDoList[userChoiceOfTaskToDelete]);
                toDoList[userChoiceOfTaskToDelete] = null;
            } else {
                message.getInvalidOption();
            }
        } else {
            message.getDontHaveAnyTaskToDeleteMessage();
        }
    }

    public void addNote(String [] toDoList, String[] notes) throws IOException {

        Scanner sc = new Scanner(System.in);

        showToDoList(toDoList, notes);

        message.getAddNoteTaskMessage();
        int userChoice = sc.nextInt();

        if(notes[userChoice] == null) {
            message.getAddNoteMessage();
            sc.nextLine();
            notes[userChoice] = sc.nextLine();
            tracker.writeOnTracker("A note " + "'"+ notes[userChoice] +"'" + " has been added to task " + "'" + toDoList[userChoice] + "' on " + LocalDate.now() + " at "+ LocalTime.now()+"\n");
        } else {
            System.out.println("\u001b[38;5;9mTask don't exist or already have a note.\u001b[0m");
        }
    }

    public void restoreAllDeletedTasks(String[] toDoList, ArrayList<String> removedTasks) {
        boolean added = false;
        for (int j = 0; j < removedTasks.size(); j++) {
            String el = removedTasks.get(j);
            for (int i = 0; i < toDoList.length; i++) {
                if (toDoList[i] == null) {
                    toDoList[i] = el;
                    removedTasks.remove(j);
                    added = true;
                    j--;
                    break;
                }
            }
        }
        if (added) {
            System.out.println("\u001b[38;5;10mAll tasks restored.\u001b[0m");
        } else {
            System.out.println("\u001b[38;5;9mNothing to restore.\u001b[0m");
        }
    }


    public void organizeAlphabetically(String[] toDoList) {
        int count = 0;
        for (int i = 0; i < toDoList.length; i++) {
            if (toDoList[i] != null) {
                count++;
            }
        }
        Arrays.sort(toDoList, 0, count);
    }

    public boolean upgradeToDoListPlan(String[] toDoList, boolean premium) {
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

    public void readTracker() throws IOException {
        tracker.readTracker();
    }

    public void removeAllCompletedTasks(String[] toDoList) throws IOException {

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

}
