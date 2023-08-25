package ru.nicholas.library.spigot;

import org.bukkit.Bukkit;
import ru.nicholas.library.MyLibrary;
import ru.nicholas.library.bukkit.utils.VersionsUtil;
import ru.nicholas.library.core.builder.*;
import ru.nicholas.library.core.util.SkullUtils;
import ru.nicholas.library.spigot.builder.universal.SpigotMessageBuilder;
import ru.nicholas.library.spigot.builder.v1_12.SpigotItemBuilder_v1_12;
import ru.nicholas.library.spigot.builder.v1_12.SpigotPotionEffectBuilder_v1_12;
import ru.nicholas.library.spigot.builder.v1_12.SpigotSkullBuilder_v1_12;
import ru.nicholas.library.spigot.builder.v1_15.SpigotItemBuilder_v1_15;
import ru.nicholas.library.spigot.builder.v1_16.SpigotItemBuilder_v1_16;
import ru.nicholas.library.spigot.builder.v1_16.SpigotPotionEffectBuilder_v1_16;
import ru.nicholas.library.spigot.builder.v1_8.SpigotItemBuilder_v1_8;
import ru.nicholas.library.spigot.builder.v1_8.SpigotPotionBuilder_v1_8;
import ru.nicholas.library.spigot.builder.v1_8.SpigotPotionEffectBuilder_v1_8;
import ru.nicholas.library.spigot.builder.v1_8.SpigotSkullBuilder_v1_8;
import ru.nicholas.library.spigot.listener.PlayerPickupExperienceListener;
import ru.nicholas.library.spigot.utils.universal.SpigotInventoryUtil;
import ru.nicholas.library.spigot.utils.universal.SpigotMessageUtil;
import ru.nicholas.library.spigot.utils.universal.SpigotTextUtil;
import ru.nicholas.library.spigot.utils.v1_19.SpigotSkullUtil_v1_19;
import ru.nicholas.library.spigot.utils.v1_20.SpigotSkullUtil_v1_20;

/**
 * @author Nicholas Alexandrov 27.06.2023
 */
public class SpigotAdapter {

    private final SkullUtils skullUtils;

    private final SpigotInventoryUtil inventoryUtils;

    private final SpigotMessageUtil messageUtils;

    private final SpigotTextUtil textUtil;

    private final Class<? extends MessageBuilder> messageBuilder;

    private final Class<? extends ItemBuilder> itemBuilder;

    private final Class<? extends PotionBuilder> potionBuilder;

    private final Class<? extends SkullBuilder> skullBuilder;

    private final Class<? extends PotionEffectBuilder> potionEffectBuilder;

    {

        Bukkit.getPluginManager().registerEvents(new PlayerPickupExperienceListener(), MyLibrary.getLibrary());

        VersionsUtil.ServerVersion serverVersion = VersionsUtil.getServerVersion();

        textUtil = new SpigotTextUtil();

        switch (serverVersion) {
            case v1_20:
            case v1_19:
            case v1_17:
            case v1_16:

                itemBuilder = SpigotItemBuilder_v1_16.class;

                skullBuilder = SpigotSkullBuilder_v1_12.class;

                potionEffectBuilder = SpigotPotionEffectBuilder_v1_16.class;

                potionBuilder = SpigotPotionBuilder_v1_8.class;

                break;

            case v1_15:

                itemBuilder = SpigotItemBuilder_v1_15.class;

                skullBuilder = SpigotSkullBuilder_v1_12.class;

                potionBuilder = SpigotPotionBuilder_v1_8.class;

                potionEffectBuilder = SpigotPotionEffectBuilder_v1_12.class;

                break;

            case v1_12:
            case v1_13:
            case v1_14:

                itemBuilder = SpigotItemBuilder_v1_12.class;

                skullBuilder = SpigotSkullBuilder_v1_12.class;

                potionEffectBuilder = SpigotPotionEffectBuilder_v1_8.class;

                potionBuilder = SpigotPotionBuilder_v1_8.class;

                break;

            default:

                potionBuilder = SpigotPotionBuilder_v1_8.class;

                itemBuilder = SpigotItemBuilder_v1_8.class;

                skullBuilder = SpigotSkullBuilder_v1_8.class;

                potionEffectBuilder = SpigotPotionEffectBuilder_v1_8.class;

                break;
        }


        skullUtils = VersionsUtil.getServerVersion().isNewerEqualThanV1_20() ? new SpigotSkullUtil_v1_20() : new SpigotSkullUtil_v1_19();

        inventoryUtils = new SpigotInventoryUtil();

        messageUtils = new SpigotMessageUtil();

        messageBuilder = SpigotMessageBuilder.class;
    }


    public SpigotTextUtil getTextUtil() {
        return textUtil;
    }

    public ItemBuilder getItemBuilder() {

        try {

            return itemBuilder.newInstance();

        } catch (InstantiationException | IllegalAccessException e) {

            throw new RuntimeException(e);
        }
    }

    public  PotionBuilder getPotionBuilder() {
        try {

            return potionBuilder.newInstance();

        } catch (InstantiationException | IllegalAccessException e) {

            throw new RuntimeException(e);
        }
    }

    public  PotionEffectBuilder getPotionEffectBuilder() {
        try {

            return potionEffectBuilder.newInstance();

        } catch (InstantiationException | IllegalAccessException e) {

            throw new RuntimeException(e);
        }
    }

    public  SkullBuilder getSkullBuilder() {
        try {

            return skullBuilder.newInstance();

        } catch (InstantiationException | IllegalAccessException e) {

            throw new RuntimeException(e);
        }
    }

    public SpigotMessageUtil MessageUtils() {
        return messageUtils;
    }

    public SkullUtils SkullUtils() {
        return skullUtils;
    }

    public SpigotInventoryUtil InventoryUtils() {
        return inventoryUtils;
    }

    public MessageBuilder MessageBuilder() {

        try {

            return messageBuilder.newInstance();

        } catch (InstantiationException | IllegalAccessException e) {

            throw new RuntimeException(e);
        }
    }
}
