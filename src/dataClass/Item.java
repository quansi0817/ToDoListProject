package dataClass;


public class Item {
    private String id;
    private String description;
    private boolean isCompleted;
       private String title;

    public Item(String id, String title,String description) {
        this.id = id;
        this.description = description;
        this.isCompleted = false;
         this.title = title;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    @Override
    public String toString() {
        return title + " (" + description + ")";
    }
}
