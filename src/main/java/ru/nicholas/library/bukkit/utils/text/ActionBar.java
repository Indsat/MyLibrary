package ru.nicholas.library.bukkit.utils.text;

import org.bukkit.entity.Player;
import ru.nicholas.library.core.VersionAdapter;

public class ActionBar {

    public static void sendActionBar(Player player, String text) {
        VersionAdapter.getTitlePackets().sendActionBar(player, text);
    }
}
