package ru.nicholas.bukkit.file;

import org.bukkit.configuration.file.FileConfiguration;
import ru.nicholas.bukkit.utils.ConfigurationUtil;

import java.io.File;

/**
 * @author Nicholas Alexandrov 18.06.2023
 */
public class YamlFile {

    private final String name;

    private final String path;

    private final File file;

    private final FileConfiguration configuration;

    public YamlFile(String path) {

        this.path = path;

        this.file = new File(path);

        this.name = file.getName().substring(file.getName().length() - 4);

        this.configuration = ConfigurationUtil.loadConfiguration(name);
    }

    public File getFile() {
        return file;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public FileConfiguration getConfiguration() {

        return configuration;
    }
}
