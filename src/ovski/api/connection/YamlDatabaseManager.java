package ovski.api.connection;

import java.io.File;
import java.io.FileNotFoundException;

import ovski.api.yaml.YamlAccessor;


public class YamlDatabaseManager {

    /*Variables*/
    private static YamlAccessor yamlDatabaseAccessor = null;

    public static YamlAccessor getYamlDatabaseAccessor()
    {
            if(YamlDatabaseManager.yamlDatabaseAccessor != null)
            {
                return YamlDatabaseManager.yamlDatabaseAccessor;
            }
            else
            {
                YamlDatabaseManager.yamlDatabaseAccessor = new YamlAccessor(new File("plugins/Yaml/database.yml"));
                return YamlDatabaseManager.yamlDatabaseAccessor;
            }
    }

    public static String getHost()
    {
        try
        {
            YamlDatabaseManager.getYamlDatabaseAccessor().reloadConfig();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        return YamlDatabaseManager.getYamlDatabaseAccessor().getConfig().getString("MySQL.Host");
    }

    public static String getUser()
    {
        try
        {
            YamlDatabaseManager.getYamlDatabaseAccessor().reloadConfig();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        return YamlDatabaseManager.getYamlDatabaseAccessor().getConfig().getString("MySQL.Login");
    }

    public static String getPassword()
    {
        try
        {
            YamlDatabaseManager.getYamlDatabaseAccessor().reloadConfig();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        return YamlDatabaseManager.getYamlDatabaseAccessor().getConfig().getString("MySQL.Password");
    }

    public static String getDatabaseName()
    {
        try
        {
            YamlDatabaseManager.getYamlDatabaseAccessor().reloadConfig();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        return YamlDatabaseManager.getYamlDatabaseAccessor().getConfig().getString("MySQL.Database");
    }

    public static int getPort()
    {
        try
        {
            YamlDatabaseManager.getYamlDatabaseAccessor().reloadConfig();
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        return YamlDatabaseManager.getYamlDatabaseAccessor().getConfig().getInt("MySQL.Port"); //3306 by default
    }
}
