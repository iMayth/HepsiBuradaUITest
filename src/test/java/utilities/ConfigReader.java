package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * reads the properties file configuration.properties
 */
public class ConfigReader {
    private Properties properties = new Properties();
    private static ConfigReader configReader;

    private ConfigReader() {
        try {
            // Load properties file
            FileInputStream fis = new FileInputStream("src/test/resources/configuration.properties");
            properties.load(fis);
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load configuration.properties file.");
        }
    }

    public static ConfigReader getInstance() {
        if (configReader == null) {
            configReader = new ConfigReader();
        }
        return configReader;
    }

    // Get the current environment (prod/stg)
    public String getEnv() {
        return System.getProperty("env", properties.getProperty("env"));
    }

    // Get the current browser (chrome/firefox)
    public String getBrowser() {
        return System.getProperty("browser", properties.getProperty("browser"));
    }
    public String getHeadless() {
        return System.getProperty("headless", properties.getProperty("headless"));
    }

    // Get the URL based on environment
    public String getUrl() {
        return System.getProperty("url", properties.getProperty("url"));

//        String env = getEnv();
//
//        return properties.getProperty(env + ".url");
    }

    // Get the expected title based on environment
    public String getTitle() {
        String env = getEnv();
        return properties.getProperty(env + ".title");
    }
}