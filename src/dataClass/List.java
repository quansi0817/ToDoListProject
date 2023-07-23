package dataClass;

public class List {
    private String id;
    private String title;
    private boolean isEditing;

    public List() {
    }

    public List(String id, String title) {
        this.id = id;
        this.title = title;
        this.isEditing = true;
    }

    public List(String id, String title, boolean isEditing) {
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

    public static String createList(String title) {

        List newList = new List("", title);
        return newList.getId();
    }
}
