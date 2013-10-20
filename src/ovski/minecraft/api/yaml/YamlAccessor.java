package ovski.minecraft.api.yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
 
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 * YamlAccessor
 * Manage a yaml file
 */
public class YamlAccessor
{

    private final File file;
    private FileConfiguration fileConfiguration;

    public YamlAccessor(File f)
    {
        if (!f.exists())
            throw new IllegalArgumentException("file must exist");
        this.file = f;
    }

    public void reloadConfig() throws FileNotFoundException {
        fileConfiguration = YamlConfiguration.loadConfiguration(file);
        // Look for defaults in the jar
        InputStream defConfigStream = new FileInputStream(file);
        if (defConfigStream != null) {
            YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
            fileConfiguration.setDefaults(defConfig);
        }
    }

    public FileConfiguration getConfig()
    {
        if (fileConfiguration == null)
        {
            try
            {
                this.reloadConfig();
            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }
        }
        return fileConfiguration;
    }

    public void saveConfig()
    {
        try {
            getConfig().save(file);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}