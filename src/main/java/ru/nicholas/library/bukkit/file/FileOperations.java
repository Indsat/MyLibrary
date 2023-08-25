package ru.nicholas.library.bukkit.file;

import org.bukkit.configuration.file.FileConfiguration;
import ru.nicholas.library.core.VersionAdapter;
import ru.nicholas.library.java.text.ReplaceData;

import java.util.List;

/**
 * @author Nicholas Alexandrov 06.08.2023
 */
public class FileOperations {

    public static String getString(FileConfiguration fileConfiguration, String path, ReplaceData... replacesData) {
        String text = fileConfiguration.getString(path);
        text = VersionAdapter.TextUtil().setReplaces(text, replacesData);
        return VersionAdapter.TextUtil().colorize(text);
    }

    public static List<String> getList(FileConfiguration fileConfiguration, String path, ReplaceData... replacesData) {
        List<String> text = fileConfiguration.getStringList(path);
        text = VersionAdapter.TextUtil().setReplaces(text, replacesData);
        return VersionAdapter.TextUtil().colorize(text);
    }
}
