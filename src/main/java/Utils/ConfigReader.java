package Utils;

import Components.KibanaDashboard;
import org.apache.log4j.Logger;

import java.io.*;
import java.time.Clock;
import java.util.Properties;

public class ConfigReader {
    private Properties prop;
    public ConfigReader(String filePath) {
        FileReader fr = null;
        try {
            fr = new FileReader(new File(filePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        BufferedReader br = new BufferedReader(fr);
        prop = new Properties();
        try {
            prop.load(br);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String propLabel) {
        String propertyValue = prop.getProperty(propLabel);
        if (propertyValue.startsWith("\"") && (propertyValue.endsWith("\""))) {
            propertyValue = propertyValue.substring(1, (propertyValue.length()) - 1);
        }

        return propertyValue;
    }

    public void setProperty(String propLabel, String propValue) {
        prop.setProperty(propLabel, propValue);
    }


}
