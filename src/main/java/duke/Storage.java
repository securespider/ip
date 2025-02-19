package duke;

import duke.exception.FileException;
import duke.task.Task;
import duke.task.TaskList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


/**
 * Represents an abstraction that helps in dealing with files
 */
public class Storage {

    BufferedReader content;
    FileReader fileReader;
    File file;
    String filepath;

    /**
     * A constructor to specify the path
     * @param filepath the path to save the file and load the file from
     * @throws FileException If the path input is invalid
     */
    public Storage(String filepath) throws FileException {
        try {
            this.filepath = filepath;
            this.file = new File(filepath);
            if (this.file.createNewFile()) {
                System.out.println("Creating File...");
            }
            this.fileReader = new FileReader(this.file);
        } catch (IOException ioException) {
            throw new FileException();
        }
    }

    /**
     * Loads tasks from the file.
     *
     * @return reader object for the file
     */
    public BufferedReader load() {
        assert this.fileReader != null && this.filepath != null && this.file != null;
        if (this.content != null) {
            return content;
        }
        this.content = new BufferedReader(this.fileReader);
        return this.content;
    }

    /**
     * Updates the file and stores the tasklist into the file
     * @param tasks The tasks to be written in the file
     * @throws FileException If the file cannot be created or written in
     */
    public void store(TaskList tasks) throws FileException {
        try {
            //noinspection ResultOfMethodCallIgnored
            this.file.createNewFile();
            FileWriter writer = new FileWriter(this.filepath);
            for (Task task: tasks) {
                writer.write(task.toString() + "\n");
            }
            writer.close();
        } catch (IOException ioException) {
            throw new FileException();
        }

    }
}
