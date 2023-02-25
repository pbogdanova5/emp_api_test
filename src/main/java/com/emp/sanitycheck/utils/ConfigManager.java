package com.emp.sanitycheck.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {

    private static ConfigManager manager;

    private static final Properties prop = new Properties();

    private ConfigManager(){

        try{
            String path = new File("src\\main\\resources\\config.properties").getPath();
            prop.load(new FileInputStream(path));
        }
        catch (NullPointerException e){
            e.printStackTrace();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ConfigManager getInstance() throws IOException {
        if(manager == null) {
            synchronized (ConfigManager.class) {
                manager = new ConfigManager();
            }
        }
        return manager;
    }

    public String getPropertyString(String key){
        return System.getProperty(key, prop.getProperty(key));
    }
}
