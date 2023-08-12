package tool;

import java.io.*;
import java.util.*;
import dataClass.Item;

public class WriteTaskData {

    private static final File taskFile = new File("src/data/task.csv");

    public static void deleteTask(String userName, String listTitle, String itemId) throws IOException {
        List<String> tasks = new ArrayList<>();

        try (Scanner input = new Scanner(taskFile)) {
            while (input.hasNext()) {
                String taskStr = input.nextLine();
                tasks.add(taskStr);
            }
        } catch (FileNotFoundException e) {
            throw new IOException("Error reading task file.", e);
        }

        try (PrintWriter output = new PrintWriter(new FileWriter(taskFile))) {
            for (String taskData : tasks) {
                String[] taskDataArray = taskData.split(",");
                if (!(taskDataArray[0].equals(userName) && taskDataArray[1].equals(listTitle) && taskDataArray[2].equals(itemId))) {
                    output.println(taskData);
                }
            }
        } catch (IOException e) {
            throw new IOException("Error writing to task file.", e);
        }
    }

    public static void editTask(String userName, String listTitle, Item oldItem, Item newItem) throws IOException {
        deleteTask(userName, listTitle, oldItem.getId());
        addTask(userName, listTitle, newItem);
    }

    public static void addTask(String userName, String listTitle, Item item) throws IOException {
    try (PrintWriter appendOutput = new PrintWriter(new FileWriter(taskFile, true))) {
        appendOutput.println(userName + "," + listTitle + "," + item.getId() + "," + item.getTitle() + "," + item.getDescription());
    } catch (IOException e) {
        throw new IOException("Error appending to task file.", e);
    }
}

}


