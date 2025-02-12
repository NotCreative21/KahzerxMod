package com.kahzerx.kahzerxmod.extensions.bocaExtension;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.network.MessageType;
import net.minecraft.network.packet.s2c.play.TitleS2CPacket;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Util;

import java.util.List;

import static net.minecraft.server.command.CommandManager.literal;

public class BocaCommand {
    public void register(CommandDispatcher<ServerCommandSource> dispatcher, BocaExtension boca) {
        dispatcher.register(literal("boca").
                requires(server -> boca.extensionSettings().isEnabled()).
                then(literal("annoying").
                        executes(context -> {
                            List<ServerPlayerEntity> players = context.getSource().getServer().getPlayerManager().getPlayerList();
                            for (ServerPlayerEntity player : players) {
                                player.networkHandler.sendPacket(new TitleS2CPacket(new LiteralText("§9❤§r §l§9BO§eQUI§9TA§r §9❤§r")));
                            }
                            return 1;
                        })).
                executes(context -> {
                    context.getSource().getServer().getPlayerManager().broadcast(new LiteralText(
                            "§9❤§r §l§9BO§eQUI§9TA§r §9❤§r"
                    ), MessageType.CHAT, Util.NIL_UUID);
                    return 1;
                }));
    }
}
