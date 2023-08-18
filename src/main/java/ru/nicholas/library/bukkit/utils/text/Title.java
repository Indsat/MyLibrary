package ru.nicholas.bukkit.utils.text;

import org.bukkit.entity.Player;
import ru.nicholas.core.VersionAdapter;
import ru.nicholas.nms.TitlePackets;

import java.util.Objects;

public class Title {

    public static void sendTitle(Player player, String text, int fadeIn, int stay, int fadeOut) {

        if (text == null) return;

        TitlePackets titlePackets = VersionAdapter.getTitlePackets();

        text = VersionAdapter.TextUtil().colorize(text);

        String[] args = text.split("%new%");

        if (args[0] != null && args[1] == null) {

            titlePackets.sendTitle(player, args[0], " ", fadeIn, stay, fadeOut);
        }

        if (args[0] != null && args[1] != null) {

            titlePackets.sendTitle(player, args[0], args[1], fadeIn, stay, fadeOut);

        }

        if (args[1] != null && args[0] == null) {

            titlePackets.sendTitle(player, "", args[1], fadeIn, stay, fadeOut);
        }
    }

    public static void sendTitle(Player player, String text) {

        Objects.requireNonNull(player, "Player can not be null");

        sendTitle(player, text, 10, 20, 10);
    }

    public static void clearTitle(Player player) {

        Objects.requireNonNull(player, "Player can not be null");

        sendTitle(player, "");
    }
}
