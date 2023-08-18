package ru.nicholas.nms.v1_17.wrappers;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import ru.nicholas.nms.AbstractPacket;

public class WrapperSetActionBarText extends AbstractPacket {

    public static final PacketType TYPE = PacketType.Play.Server.SET_ACTION_BAR_TEXT;

    public WrapperSetActionBarText() {
        super(new PacketContainer(TYPE), TYPE);
        handle.getModifier().writeDefaults();
    }

    public WrapperSetActionBarText(PacketContainer packet) {
        super(packet, TYPE);
    }

    public void setText(WrappedChatComponent wrappedChatComponent) {

        handle.getChatComponents().write(0, wrappedChatComponent);
    }

    public WrappedChatComponent getText() {

        return handle.getChatComponents().read(0);
    }
}
