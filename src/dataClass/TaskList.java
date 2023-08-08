package dataClass;

import java.util.ArrayList;

public class TaskList {
    private String id;
    private String title;
    private boolean isEditing;
    private ArrayList<Item> items;

    public TaskList() {
    }

    public TaskList(String title) {
        this.title = title;
        this.isEditing = true;
    }

    public TaskList(String id, String title) {
        this.id = id;
        this.title = title;
        this.isEditing = true;
        this.items = new ArrayList<>();
    }

    public TaskList(String id, String title, boolean isEditing) {
        this.id = id;
        this.title = title;
        this.isEditing = isEditing;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isEditing() {
        return isEditing;
    }

    public void setIsEditing(boolean isEditing) {
        this.isEditing = isEditing;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public static String createList(String title) {

        TaskList newList = new TaskList("", title);
        return newList.getId();
    }
}
