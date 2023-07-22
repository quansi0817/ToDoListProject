package user;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import org.json.JSONObject;
import org.json.JSONTokener;

import list.List;

public class User {
    private int id;
    private String email;
    private String name;
    private String password;
    private List[] list;
    private String[] allTasks;

    public User() {

    }

    public User(int id, String email, String name, String password) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public User(int id, String email, String name, String password, List[] list, String[] allTasks) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
        this.list = list;
        this.allTasks = allTasks;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List[] getList() {
        return list;
    }

    public void setList(List[] list) {
        this.list = list;
    }

    public String[] getAllTasks() {
        return allTasks;
    }

    public void setAllTasks(String[] allTasks) {
        this.allTasks = allTasks;
    }

    public static void register() {
        Scanner input = new Scanner(System.in);
        try {
            String filePath = "src/data/user.json";

            // Read the existing JSON data from the file into a String
            String usersData = new String(Files.readAllBytes(Paths.get(filePath)));

            // Parse the JSON data into a JSONObject
            JSONObject userJsonObject = new JSONObject(new JSONTokener(usersData));

            // get the last ID and the new ID
            String lastId = "";
            Iterator<String> keys = userJsonObject.keys();
            while (keys.hasNext()) {
                String key = keys.next();
                System.out.println(key);
                lastId = key;
            }
            int lastIntId = Integer.parseInt(lastId);

            // Get the input from user:
            System.out.println("Enter your name: ");
            String name = input.nextLine();
            System.out.println("Enter your password");
            String password = input.nextLine();
            System.out.println("Enter your email:");
            String email = input.nextLine();
            int newId = lastIntId + 1;
            // create new user
            User newUser = new User(newId, email, name, password);

            // Store the input data into one
            JSONObject user = new JSONObject();
            user.put("name", name);
            user.put("password", password);
            user.put("email", email);
            System.out.println(userJsonObject);

            // Add new data to this ID JSONObject
            userJsonObject.put(newId + "", user);

            // Write the updated JSONObject back to the file
            try (FileWriter fileWriter = new FileWriter(filePath)) {
                fileWriter.write(userJsonObject.toString());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
