package tool;

import java.util.*;
import java.io.*;
import dataClass.TaskList;
import controller.App;

public class WriteData {
    private static File listFile = new File("src/data/list.csv");

    public static void deleteList(String deletedList) throws IOException {
        ArrayList<String> usersList = new ArrayList<>();

        // Scanner
        Scanner input = new Scanner(listFile);
        while (input.hasNext()) {
            String targetedStr = input.nextLine();
            usersList.add(targetedStr);
        }

        PrintWriter output = new PrintWriter(new FileWriter(listFile));
        PrintWriter appendOutput = new PrintWriter(new FileWriter(listFile, true));
        output.print("");
        for (String oneUserList : usersList) {
            String[] oneUserListArray = oneUserList.split(",");
            if (oneUserListArray[0].equals(App.getUser().getName())) {
                usersList.remove(oneUserList);
                break;
            }
        }

        for (String item : usersList) {
            appendOutput.println(item);
        }
        ArrayList<TaskList> lists = App.getUser().getList();
        for (TaskList list : lists) {
            if (list.getTitle().equals(deletedList)) {
                lists.remove(list);
                break;
            }
        }

        appendOutput.print(App.getUser().getName() + ",");
        for (TaskList oneList : App.getUser().getList()) {
            appendOutput.print(oneList.getTitle() + ",");
        }
        appendOutput.println();
        input.close();
        output.close();
        appendOutput.close();
    }

    public static void writeListDataToFile() throws IOException {
        ArrayList<String> usersList = new ArrayList<>();

        // Scanner
        Scanner input = new Scanner(listFile);
        while (input.hasNext()) {
            String targetedStr = input.nextLine();
            usersList.add(targetedStr);
        }

        PrintWriter output = new PrintWriter(new FileWriter(listFile));
        PrintWriter appendOutput = new PrintWriter(new FileWriter(listFile, true));
        output.print("");
        for (String oneUserList : usersList) {
            String[] oneUserListArray = oneUserList.split(",");
            if (oneUserListArray[0].equals(App.getUser().getName())) {
                usersList.remove(oneUserList);
                break;
            }
        }

        for (String item : usersList) {
            appendOutput.println(item);
        }

        appendOutput.print(App.getUser().getName() + ",");
        for (TaskList oneList : App.getUser().getList()) {
            appendOutput.print(oneList.getTitle() + ",");
        }
        appendOutput.println();
        input.close();
        output.close();
        appendOutput.close();
    }

    public static int findIndex(String[] arr, String targetedStr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(targetedStr))
                return i;
        }
        return -1;
    }
}
