package ru.nicholas.library.nms.v1_16;

import com.comphenix.protocol.wrappers.EnumWrappers;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import org.bukkit.entity.Player;
import ru.nicholas.library.nms.TitlePackets;

public class CraftTitlePackets_v1_16 implements TitlePackets {


    @Override
    public void sendTitle(Player player, String title, String subTitle, int fadeIn, int stay, int fadeOut) {

        WrapperPlayServerTitle wrapperPlayServerTitle = new WrapperPlayServerTitle();

        wrapperPlayServerTitle.setStay(stay);

        wrapperPlayServerTitle.setFadeIn(fadeIn);

        wrapperPlayServerTitle.setFadeOut(fadeOut);

        WrappedChatComponent titleComponent = WrappedChatComponent.fromText(title);

        wrapperPlayServerTitle.setTitle(titleComponent);

        wrapperPlayServerTitle.setAction(EnumWrappers.TitleAction.TITLE);

        wrapperPlayServerTitle.sendPacket(player);

        WrappedChatComponent subTitleComponent = WrappedChatComponent.fromText(subTitle);

        wrapperPlayServerTitle.setTitle(subTitleComponent);

        wrapperPlayServerTitle.setAction(EnumWrappers.TitleAction.SUBTITLE);

        wrapperPlayServerTitle.sendPacket(player);
    }

    @Override
    public void sendActionBar(Player player, String text) {

        WrapperPlayServerTitle wrapperPlayServerTitle = new WrapperPlayServerTitle();

        WrappedChatComponent textComponent = WrappedChatComponent.fromText(text);

        wrapperPlayServerTitle.setAction(EnumWrappers.TitleAction.ACTIONBAR);

        wrapperPlayServerTitle.setTitle(textComponent);

        wrapperPlayServerTitle.sendPacket(player);
    }
}
