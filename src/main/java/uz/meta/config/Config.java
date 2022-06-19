package uz.meta.config;

import io.github.cdimascio.dotenv.Dotenv;

public class Config {
    static final Dotenv dotenv = Dotenv
            .configure()
            .directory("src/main/resources/")
            .filename(".tgenv")
            .load();


    public static String getenv(String key) {
        return dotenv.get(key, key);
    }
}
