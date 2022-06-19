package uz.meta.property;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Property {
    static Properties properties = new Properties();

    static {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader("src/main/resources/application.properties");
            properties.load(fileReader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key, key);
    }
}
