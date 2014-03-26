package net.ovski.minecraft.api.connection;

import java.io.File;
import java.io.FileNotFoundException;

import net.ovski.minecraft.api.yaml.YamlAccessor;


/**
 * YamlDatabaseManager
 * 
 * Manager for the yaml database file.
 *
 * @author baptiste <baptiste.bouchereau@gmail.com>
 */
public class YamlDatabaseManager
{
    private static YamlAccessor yamlDatabaseAccessor = null;

    /**
     * Get the accessor for the database.yml file
     * 
     * @return yamlDatabaseAccessor : the YamlAccessor
     */
    public static YamlAccessor getYamlDatabaseAccessor()
    {
            if(YamlDatabaseManager.yamlDatabaseAccessor != null) {
                return YamlDatabaseManager.yamlDatabaseAccessor;
            } else {
                YamlDatabaseManager.yamlDatabaseAccessor = new YamlAccessor(new File("plugins/Yaml/database.yml"));

                return YamlDatabaseManager.yamlDatabaseAccessor;
            }
    }

    /**
     * Get the database host
     * 
     * @return the database host as a String
     */
    public static String getHost()
    {
        try {
            YamlDatabaseManager.getYamlDatabaseAccessor().reloadConfig();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return YamlDatabaseManager.getYamlDatabaseAccessor().getConfig().getString("MySQL.Host");
    }

    /**
     * Get the database user
     * 
     * @return the database user as a String
     */
    public static String getUser()
    {
        try {
            YamlDatabaseManager.getYamlDatabaseAccessor().reloadConfig();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return YamlDatabaseManager.getYamlDatabaseAccessor().getConfig().getString("MySQL.Login");
    }

    /**
     * Get the database password
     * 
     * @return the database password as a String
     */
    public static String getPassword()
    {
        try {
            YamlDatabaseManager.getYamlDatabaseAccessor().reloadConfig();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return YamlDatabaseManager.getYamlDatabaseAccessor().getConfig().getString("MySQL.Password");
    }

    /**
     * Get the database password
     * 
     * @return the database password as a String
     */
    public static String getDatabaseName()
    {
        try {
            YamlDatabaseManager.getYamlDatabaseAccessor().reloadConfig();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return YamlDatabaseManager.getYamlDatabaseAccessor().getConfig().getString("MySQL.Database");
    }

    /**
     * Get the database port
     * 
     * @return the database port as a String
     */
    public static int getPort()
    {
        try {
            YamlDatabaseManager.getYamlDatabaseAccessor().reloadConfig();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return YamlDatabaseManager.getYamlDatabaseAccessor().getConfig().getInt("MySQL.Port"); //3306 by default
    }
}
