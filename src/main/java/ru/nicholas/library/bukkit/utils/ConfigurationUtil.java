package ru.nicholas.bukkit.utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import ru.nicholas.villagers.UltimateVillagers;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class ConfigurationUtil
{

    public static FileConfiguration loadConfiguration(File dataFolder, String fileName)
    {

        File file = new File(dataFolder, fileName);

        if (!file.exists()) {

            UltimateVillagers.getPlugin().saveResource(fileName, false);

        }

        return YamlConfiguration.loadConfiguration(file);
    }

    public static FileConfiguration loadConfiguration(String fileName)
    {
        return loadConfiguration(UltimateVillagers.getPlugin().getDataFolder(), fileName);
    }

    public static void loadConfigurations(String... fileNames) {

        Arrays.stream(fileNames).forEach(ConfigurationUtil::loadConfiguration);
    }


    public static File createFile(File dataFolder, String fileName) {

        File file  = new File(dataFolder, fileName);

        if (!file.exists()) {

            file.mkdir();
        }

        return file;
    }

    public static File createFile(String fileName) {

       return createFile(UltimateVillagers.getPlugin().getDataFolder(), fileName);
    }

    public static void saveFile(FileConfiguration fileConfiguration, String dataFolder, String fileName) {

        try {

            fileConfiguration.save(new File(dataFolder, fileName));

        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}
