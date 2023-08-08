package tool;

import java.util.*;
import java.io.*;
import dataClass.TaskList;
import controller.App;

public class WriteData {

    public static void writeListDataToFile() throws IOException {
        File listFile = new File("src/data/list.csv");
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
}
