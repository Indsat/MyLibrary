package ru.nicholas.bukkit.utils.text;

import org.bukkit.entity.Player;
import ru.nicholas.core.VersionAdapter;

public class ActionBar {

    public static void sendActionBar(Player player, String text) {

        VersionAdapter.getTitlePackets().sendActionBar(player, text);

    }
}
