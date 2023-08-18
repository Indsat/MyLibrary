package ru.nicholas.bukkit.file;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Класс-репозиторий для файлов плагина
 *
 * @author Nicholas Alexandrov 18.06.2023
 */
public class FileRepository {

    private static final List<YamlFile> files;

    private static final Plugin plugin;

    static {

        files = new ArrayList<>();

        plugin = Bukkit.getPluginManager().getPlugin("LeakBlackMarket");

        load();
    }


    public static void load() {

        files.addAll(Arrays.stream(Objects.requireNonNull(plugin.getDataFolder().listFiles())).filter(File::isFile)
                .filter(file -> file.getName().endsWith(".yml"))
                .map(file -> new YamlFile(file.getAbsolutePath()))
                .collect(Collectors.toList()));
    }

    public static YamlFile getByName(String name) {

        try {

            return get(name);

        } catch (FileNotFoundException e) {

            throw new RuntimeException(e);
        }
    }

    private static YamlFile get(String name) throws FileNotFoundException {

        Optional<YamlFile> optional = files.stream().filter(file -> file.getName().equals(name)).findFirst();

        if (optional.isPresent()) {

            return optional.get();
        }

        throw new FileNotFoundException("Could not find a file with the name " + name);
    }
}
