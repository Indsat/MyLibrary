package ru.nicholas.nms.v1_17.wrappers;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import ru.nicholas.nms.AbstractPacket;

public class WrapperSetSubtitleText extends AbstractPacket {

    public static final PacketType TYPE = PacketType.Play.Server.SET_SUBTITLE_TEXT;

    public WrapperSetSubtitleText() {
        super(new PacketContainer(TYPE), TYPE);
        handle.getModifier().writeDefaults();
    }

    public WrapperSetSubtitleText(PacketContainer packet) {
        super(packet, TYPE);
    }

    public void setText(WrappedChatComponent wrappedChatComponent) {

        handle.getChatComponents().write(0, wrappedChatComponent);
    }

    public WrappedChatComponent getText() {

        return handle.getChatComponents().read(0);
    }
}
