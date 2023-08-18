package ru.nicholas.nms.v1_17.wrappers;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import ru.nicholas.nms.AbstractPacket;

public class WrapperSetTitleAnimation extends AbstractPacket {

    public static final PacketType TYPE = PacketType.Play.Server.SET_TITLES_ANIMATION;

    public WrapperSetTitleAnimation() {
        super(new PacketContainer(TYPE), TYPE);
        handle.getModifier().writeDefaults();
    }

    public WrapperSetTitleAnimation(PacketContainer packet) {
        super(packet, TYPE);
    }

    public void setFadeIn(int value) {

        handle.getIntegers().write(0, value);
    }

    public int getFadeIn() {

        return handle.getIntegers().read(0);
    }

    public void setStay(int value) {

        handle.getIntegers().write(1, value);
    }

    public int getStay() {

        return handle.getIntegers().read(1);
    }

    public void setFadeOut(int value) {

        handle.getIntegers().write(2, value);
    }

    public int getFadeOut() {

        return handle.getIntegers().read(2);
    }
}
