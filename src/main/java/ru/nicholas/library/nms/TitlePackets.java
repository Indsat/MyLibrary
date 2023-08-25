package ru.nicholas.library.nms;

import org.bukkit.entity.Player;

public interface TitlePackets {

    void sendTitle(Player player, String title, String subTitle, int fadeIn, int stay, int fadeOut);

    void sendActionBar(Player player, String text);
}
