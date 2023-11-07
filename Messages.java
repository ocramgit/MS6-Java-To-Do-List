import java.time.LocalDate;

public class Messages {
    public void showMenuMessage() {
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
        System.out.println("\u001b[38;5;15m12 - Add task note\u001b[0m");
        System.out.println("\u001b[38;5;15m0 - Exit ToDoList\u001b[0m\n");
        System.out.print("\u001b[38;5;15mChoose a option: \u001b[0m");
    }

    public void getFreeUpgradeMessage() {
        System.out.println("\n\t\t\t\t\u001b[43;1m\u001b[38;5;15mIMPORTANT WARNING\u001b[0m\u001b[38;5;11m\nYou are currently using the Free Plan of ToDoList!\nYou can upgrade to Premium Plan in the upgrade menu!\u001b[0m");
    }

    public void getFreeSpaces(int count) {
        System.out.println("\n\u001b[38;5;15mYou still have \u001b[38;5;11m" + count + "\u001b[38;5;15m free spaces on the list!\u001b[0m");
    }

    public void getCloseProgramMessage() {
        System.out.println("\n\u001b[38;5;9mClosing ToDoList program...\u001b[0m");
    }

    public void getInvalidOption() {
        System.out.println("\n\u001b[38;5;9mInvalid option!\u001b[0m");
    }

    public void getToDoListHeadline() {
        System.out.println("\n\t\t\u001b[38;5;15mToDoList\u001b[0m");
        System.out.println("\u001b[38;5;8m------------------------\u001b[0m");
    }

    public void getToDoListEmpty() {
        System.out.println("\n\u001b[38;5;9mThe ToDoList is empty! You should create a task first.\u001b[0m");
    }

    public void getUncompletedAndCompletedMessage(int countUncompleted, int countCompleted, String decimalPercentage) {
        System.out.println("\nUncompleted tasks: " + countUncompleted);
        System.out.println("Completed tasks: " + countCompleted);
        System.out.println("\nPercentage of completed tasks: " + decimalPercentage + "%");
    }

    public void getCreateTaskMessage() {
        System.out.print("\n\u001b[38;5;15mCreate task: \u001b[0m");
    }

    public void getCreatedTaskMessage(String userNewTask) {
        System.out.println("\n\u001b[38;5;10mThe task '\u001b[38;5;15m" + userNewTask + "\u001b[38;5;10m' was created!\u001b[0m");
    }

    public void getListFullMessage() {
        System.out.println("\n\n\u001b[38;5;9mThe list is full! You don't have more space.\u001b[0m");
    }

    public void getEmptyTask() {
        System.out.println("\n\n\u001b[38;5;9mTask name cannot be empty.\u001b[0m");
    }

    public void getTaskToCompleteMessage() {
        System.out.print("\n\u001b[38;5;15mChoose a task to mark as completed: \u001b[0m");
    }

    public void getTaskAlreadyCompletedMessage() {
        System.out.println("\n\u001b[38;5;9mThat task is already marked as completed!\u001b[0m");
    }

    public void getTaskCompletedMessage() {
        System.out.println("\n\u001b[38;5;10mTask successfully marked as completed and moved to the top!\u001b[0m");
    }

    public void getDontHaveTasksMessage() {
        System.out.println("\n\u001b[38;5;9mYou don't have any task.\u001b[0m");
    }

    public void getChooseTaskToRemoveAsCompletedMessage() {
        System.out.print("\n\u001b[38;5;15mChoose a task to remove as completed: \u001b[0m");
    }

    public void getDontHaveAnyCompletedTask() {
        System.out.println("\n\u001b[38;5;9mYou don't have completed tasks!\u001b[0m");
    }

    public void getTaskToEditMessage() {
        System.out.print("\n\u001b[38;5;15mChoose a task to edit: \u001b[0m");
    }

    public void getNewMessage() {
        System.out.print("\u001b[38;5;15mNew: \u001b[0m");
    }

    public void getDontHaveAnyTaskToEditMessage() {
        System.out.println("\n\u001b[38;5;9mYou don't have any task to edit!\u001b[0m");
    }

    public void getMessageToDeleteMessage() {
        System.out.print("\n\u001b[38;5;15mChoose a task to delete: \u001b[0m");
    }

    public void getDontHaveAnyTaskToDeleteMessage() {
        System.out.println("\u001b[38;5;9mYou don't have tasks to delete!\u001b[0m");
    }

    public void getAddNoteTaskMessage() {
        System.out.print("\n\u001b[38;5;15mChoose a task to add a note: \u001b[0m");
    }

    public void getAddNoteMessage() {
        System.out.print("\n\u001b[38;5;15mAdd an note to this message: \u001b[0m");
    }
}
