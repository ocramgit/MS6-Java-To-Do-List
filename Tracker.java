import java.io.*;

public class Tracker {

    private File file;
    private FileWriter fileWriter;

    public Tracker(File file) {
        this.file = file;
    }

    public void writeOnTracker(String whatToWrite) throws IOException {
        fileWriter = new FileWriter(file, true);
        fileWriter.write(whatToWrite);
        fileWriter.flush();
        fileWriter.close();
    }

    public void readTracker() throws IOException {
        BufferedReader read = new BufferedReader(new FileReader(file.getAbsoluteFile()));

        boolean isEmpty = true;
        String line;

        while ((line = read.readLine()) != null) {
            System.out.println(line);
            isEmpty = false;
        }

        read.close();

        if (isEmpty) System.out.println("\u001b[38;5;9mYou don't have any track.\u001b[0m");
    }

    public void clearTracker() throws IOException {
        BufferedReader read = new BufferedReader(new FileReader(file.getAbsoluteFile()));

        boolean isEmpty = true;
        String line;

        while ((line = read.readLine()) != null) {
            System.out.println(line);
            isEmpty = false;
        }

        read.close();

        if (isEmpty) {
            System.out.println("\u001b[38;5;9mYou don't have any track.\u001b[0m");
        }
    }
}
