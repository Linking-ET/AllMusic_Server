package com.coloryr.allmusic.server.side.fabric;

import com.coloryr.allmusic.server.AllMusicFabric;
import net.minecraft.network.packet.s2c.play.OverlayMessageS2CPacket;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;

public class FabricApi {

    public static void sendMessageRun(Object obj, String message, String end, String command) {
        ServerCommandSource sender = (ServerCommandSource) obj;
        MutableText send = Text.literal(message);
        var endText = Text.literal(end);
        endText.setStyle(endText.getStyle().withClickEvent(new ClickEvent.RunCommand(command)));
        send.append(endText);
        sender.sendMessage(send);
    }

    public static void sendMessageSuggest(Object obj, String message, String end, String command) {
        ServerCommandSource sender = (ServerCommandSource) obj;
        MutableText send = Text.literal(message);
        var endText = Text.literal(end);
        endText.setStyle(endText.getStyle().withClickEvent(new ClickEvent.SuggestCommand(command)));
        send.append(endText);
        sender.sendMessage(send);
    }

    public static void sendBar(ServerPlayerEntity player, String message) {
        var pack = new OverlayMessageS2CPacket(Text.literal(message));
        player.networkHandler.sendPacket(pack);
    }

    public static void sendMessageBqRun(String message, String end, String command) {
        MutableText send = Text.literal(message);
        MutableText endText = Text.literal(end);
        endText.setStyle(endText.getStyle().withClickEvent(new ClickEvent.SuggestCommand(command)));
        send.append(endText);
        for (var player : AllMusicFabric.server.getPlayerManager().getPlayerList()) {
            player.sendMessage(send, false);
        }
    }
}
