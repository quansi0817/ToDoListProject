import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import org.json.JSONObject;
import org.json.JSONTokener;

import user.User;

public class App {
    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 2; i++) {
            User.register();
        }

        String filePath = "src/data/user.json";

        // Read the existing JSON data from the file into a String
        String usersData = new String(Files.readAllBytes(Paths.get(filePath)));

        // Parse the JSON data into a JSONObject
        JSONObject userJsonObject = new JSONObject(new JSONTokener(usersData));
        Iterator<String> keys = userJsonObject.keys();

        while (keys.hasNext()) {
            String key = keys.next();
            System.out.println(key);
        }
    }
}
