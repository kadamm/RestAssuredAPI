package api.utilities;

import java.util.ResourceBundle;

public class ReadPropertyFile {
    public static ResourceBundle resourceBundle;

    static{
        resourceBundle = ResourceBundle.getBundle("config");
    }

    public static String getPropertyValue(String key) {
        return resourceBundle.getString(key);
    }
}
