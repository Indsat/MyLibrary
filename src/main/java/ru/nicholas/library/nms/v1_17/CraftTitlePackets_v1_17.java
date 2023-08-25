package ru.nicholas.library.nms.v1_17;

import com.comphenix.protocol.wrappers.WrappedChatComponent;
import org.bukkit.entity.Player;
import ru.nicholas.library.nms.TitlePackets;
import ru.nicholas.library.nms.v1_17.wrappers.WrapperSetActionBarText;
import ru.nicholas.library.nms.v1_17.wrappers.WrapperSetSubtitleText;
import ru.nicholas.library.nms.v1_17.wrappers.WrapperSetTitleAnimation;
import ru.nicholas.library.nms.v1_17.wrappers.WrapperSetTitleText;

public class CraftTitlePackets_v1_17 implements TitlePackets {

    @Override
    public void sendTitle(Player player, String title, String subTitle, int fadeIn, int stay, int fadeOut) {

        WrapperSetTitleText wrapperSetTitleText = new WrapperSetTitleText();

        WrappedChatComponent[] components = WrappedChatComponent.fromChatMessage(title);
        if (components.length > 0) {
            wrapperSetTitleText.setText(components[0]);
        }

        wrapperSetTitleText.sendPacket(player);

        WrapperSetSubtitleText wrapperSetSubtitleText = new WrapperSetSubtitleText();

        WrappedChatComponent[] componentsSub = WrappedChatComponent.fromChatMessage(subTitle);
        if (components.length > 0) {
            wrapperSetTitleText.setText(componentsSub[0]);
        }

        wrapperSetSubtitleText.sendPacket(player);

        WrapperSetTitleAnimation wrapperSetTitleAnimation = new WrapperSetTitleAnimation();

        wrapperSetTitleAnimation.setFadeIn(fadeIn);

        wrapperSetTitleAnimation.setStay(stay);

        wrapperSetTitleAnimation.setFadeOut(fadeIn);

        wrapperSetTitleAnimation.sendPacket(player);

    }

    @Override
    public void sendActionBar(Player player, String text) {

        WrapperSetActionBarText wrapperSetActionBarText = new WrapperSetActionBarText();

        WrappedChatComponent[] components = WrappedChatComponent.fromChatMessage(text);
        if (components.length > 0) {
            wrapperSetActionBarText.setText(components[0]);
        }

        wrapperSetActionBarText.sendPacket(player);
    }
}
