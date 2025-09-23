package edu.ccrm.config;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class AppConfig {
    private static AppConfig instance;
    private final Properties properties;

    private AppConfig() {
        properties = new Properties();
        try (FileReader reader = new FileReader("config.properties")) {
            properties.load(reader);
        } catch (IOException e) {
            // Use default values if config file is not found
            properties.setProperty("data.folder", "data");
            properties.setProperty("backup.folder", "backups");
            properties.setProperty("max.credits", "20");
        }
    }

    public static synchronized AppConfig getInstance() {
        if (instance == null) {
            instance = new AppConfig();
        }
        return instance;
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public int getIntProperty(String key) {
        return Integer.parseInt(properties.getProperty(key));
    }
}
