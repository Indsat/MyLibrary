package ru.nicholas.bukkit.file;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import ru.nicholas.core.VersionAdapter;
import ru.nicholas.java.text.ReplaceData;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Nicholas Alexandrov 27.06.2023
 */

public abstract class ConfigurationFile {

    public static Map<String, String> strings;

    private static FileConfiguration config;

    public ConfigurationFile(YamlFile yamlFile) {

        config = yamlFile.getConfiguration();

        strings = new HashMap<>();

        strings = load();
    }

    abstract public Map<String, String> load();

    public static FileConfiguration getConfig() {

        return config;
    }

    public static ConfigurationSection getSection(String path) {

        return config.getConfigurationSection(path);
    }

    public static Map<String, String> getStrings() {

        return strings;
    }

    public static List<String> getList(String path, ReplaceData... replacesData) {

        String value = strings.get(path);

        if (value == null) {

            return Collections.singletonList("Failed to receive a message in the configuration");

        }

        String[] lines = value.split("\n");

        List<String> result = VersionAdapter.TextUtil().colorize(VersionAdapter.TextUtil().setReplaces(Arrays.asList(lines), replacesData));

        return result.stream().map(line -> line.replace("[", "").replace("]", "").replace("\n", "")).collect(Collectors.toList());
    }

    public static String getString(String path, ReplaceData... replaceData) {

        String value = strings.get(path);

        if (value == null) {

            return "Failed to receive a message in the configuration";
        }

        return VersionAdapter.TextUtil().colorize(VersionAdapter.TextUtil().setReplaces(value, replaceData));
    }

    public static int getInt(String path) {

        return Integer.parseInt(getString(path));
    }

    public static List<Integer> getIntList(String path) {

        return config.getIntegerList(path);
    }
}
