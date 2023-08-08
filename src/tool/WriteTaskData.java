package tool;

import java.util.*;
import java.io.*;
import dataClass.Item;
import dataClass.TaskList;


public class WriteTaskData {

    private static File taskFile = new File("src/data/task.csv");

    public static void deleteTask(String userName,String listTitle, String itemId) throws IOException {
        ArrayList<String> tasks = new ArrayList<>();
        Scanner input = new Scanner(taskFile);
        while (input.hasNext()) {
            String taskStr = input.nextLine();
            tasks.add(taskStr);
        }
        input.close();

        PrintWriter output = new PrintWriter(new FileWriter(taskFile));
        PrintWriter appendOutput = new PrintWriter(new FileWriter(taskFile, true));

        for (Iterator<String> it = tasks.iterator(); it.hasNext();) {
            String taskData = it.next();
            String[] taskDataArray = taskData.split(",");
            if (taskDataArray[0].equals(userName) && taskDataArray[1].equals(listTitle) && taskDataArray[2].equals(itemId)) {
                it.remove();
            } else {
                appendOutput.println(taskData);
            }
        }
        output.close();
        appendOutput.close();
    }

    public static void editTask(String userName,String listTitle, Item oldItem, Item newItem) throws IOException {
        deleteTask(userName,listTitle, oldItem.getId());
        addTask(userName,listTitle, newItem);
    }

    public static void addTask(String userName,String listTitle, Item item) throws IOException {
        PrintWriter appendOutput = new PrintWriter(new FileWriter(taskFile, true));
        appendOutput.println(userName+ "," + listTitle + "," + item.getId() + "," + item.getTitle() + "," + item.getDescription());
        appendOutput.close();
    }
}

