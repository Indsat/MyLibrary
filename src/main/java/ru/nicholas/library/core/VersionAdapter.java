package ru.nicholas.library.core;

import org.bukkit.Bukkit;
import ru.nicholas.library.bukkit.utils.VersionsUtil;
import ru.nicholas.library.core.builder.*;
import ru.nicholas.library.core.util.SkullUtils;
import ru.nicholas.library.paper.PaperAdapter;
import ru.nicholas.library.spigot.SpigotAdapter;
import ru.nicholas.library.spigot.utils.universal.SpigotInventoryUtil;
import ru.nicholas.library.spigot.utils.universal.SpigotMessageUtil;
import ru.nicholas.library.spigot.utils.universal.SpigotTextUtil;

/**
 * Класс адаптер для версий и ядер
 *
 * @author Nicholas Alexandrov 02.06.2023
 */
public class VersionAdapter {

    private static final SpigotAdapter adapter;

    private static final VersionsUtil.ServerVersion serverVersion = VersionsUtil.getServerVersion();

    private static final String version = Bukkit.getVersion();

    static {

        adapter = (version.contains("Paper") || version.contains("Purpur")) ? new PaperAdapter() : new SpigotAdapter();
    }

    public static SpigotTextUtil TextUtil(){
        return adapter.getTextUtil();
    }

    public static PotionEffectBuilder getPotionEffectBuilder() {

        return adapter.getPotionEffectBuilder();
    }

    public static PotionBuilder getPotionBuilder() {

        return adapter.getPotionBuilder();
    }

    public static SkullBuilder getSkullBuilder() {

        return adapter.getSkullBuilder();
    }

    public static ItemBuilder getItemBuilder() {

        return adapter.getItemBuilder();
    }

    public static MessageBuilder getMessageBuilder(String text) {

        return adapter.MessageBuilder();
    }

    public static MessageBuilder getMessageBuilder() {

        return adapter.MessageBuilder();
    }

    public static SkullUtils SkullUtils() {

        return adapter.SkullUtils();
    }

     public static SpigotInventoryUtil InventoryUtils() {

        return adapter.InventoryUtils();
     }

     public static SpigotMessageUtil MessageUtils() {

        return adapter.MessageUtils();
     }

    public static SpigotAdapter getAdapter() {
        return adapter;
    }
}
