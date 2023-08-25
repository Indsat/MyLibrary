package ru.nicholas.library.spigot.utils.universal;

import com.cryptomorin.xseries.XSound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.nicholas.library.bukkit.utils.text.ActionBar;
import ru.nicholas.library.bukkit.utils.text.Title;
import ru.nicholas.library.core.VersionAdapter;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author Nicholas Alexandrov 20.06.2023
 */
public class SpigotMessageUtil {

    public void sendMessage(CommandSender commandSender, String object) {

        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            String[] strings = object.split("%split%");
            Arrays.stream(strings).forEach(string -> {
                string = VersionAdapter.TextUtil().colorize(string);

                if (string.startsWith("sound:")) {
                    string = string.substring("sound:".length());
                    Optional<XSound> optional = XSound.matchXSound(string.toUpperCase());
                    optional.ifPresent(xSound -> {
                        assert xSound.parseSound() != null;
                        player.playSound(player.getLocation(), xSound.parseSound(), 2, 1);
                    });
                } else if (string.startsWith("title:")) {
                    Title.sendTitle(player, string.split("title:")[1]);
                } else if (string.startsWith("actionbar:")) {
                    ActionBar.sendActionBar(player, string.split("actionbar:")[1]);
                } else {
                    player.sendMessage(VersionAdapter.TextUtil().colorize(object));
                }
            });
        }
    }

    public void sendMessage(CommandSender commandSender, List<String> text) {
        text.forEach(str -> sendMessage(commandSender, str));
    }
}
