package resources;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

//import Utilities.Constants;


public class ReadPropertiesFile {

    private static Properties properties = null;
    BufferedReader br;

    /** Loads the properties file */
    public ReadPropertiesFile() {
        properties = new Properties();
        try {
            properties.load(new FileInputStream(Constants.CONFIG_PROPERTY_FILENAME));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assert !properties.isEmpty();
    }

    /** returns the value of the property denoted by key
     * 
     * @param key
     * @return */
    public static String getProperty(String key) {
        if (properties == null) {
            new ReadPropertiesFile();
        }
        String property = properties.getProperty(key);
        return property != null ? property.trim() : property;
    }
    
}