package dataClass;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import org.json.JSONObject;
import org.json.JSONTokener;

public class User {
    private int id;
    private String email;
    private String name;
    private String password;
    private ArrayList<String> list;
    private String[] allTasks;

    public User() {

    }

    public User(String email, String name, String password) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public User(String email, String name, String password, ArrayList<String> list, String[] allTasks) {
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

    public ArrayList<String> getList() {
        return list;
    }

    public void setList(ArrayList<String> list) {
        this.list = list;
    }

    public String[] getAllTasks() {
        return allTasks;
    }

    public void setAllTasks(String[] allTasks) {
        this.allTasks = allTasks;
    }

    @Override
    public String toString() {
        return "User name: " + name + "\n"
                + "Email: " + email;
    }
}
