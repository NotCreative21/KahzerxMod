package com.kahzerx.kahzerxmod.extensions.modTPExtension;

import com.kahzerx.kahzerxmod.Extensions;
import com.kahzerx.kahzerxmod.extensions.ExtensionSettings;
import com.kahzerx.kahzerxmod.extensions.GenericExtension;
import com.kahzerx.kahzerxmod.extensions.permsExtension.PermsExtension;
import com.kahzerx.kahzerxmod.extensions.permsExtension.PermsLevels;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;

public class ModTPExtension extends GenericExtension implements Extensions {
    public final PermsExtension permsExtension;
    public ModTPExtension(ExtensionSettings settings, PermsExtension perms) {
        super(settings);
        this.permsExtension = perms;
    }

    @Override
    public void onRegisterCommands(CommandDispatcher<ServerCommandSource> dispatcher) {
        new ModTPCommand().register(dispatcher, this);
    }

    public int tp(ServerCommandSource source, String playerName) throws CommandSyntaxException {
        ServerPlayerEntity player = source.getServer().getPlayerManager().getPlayer(playerName);
        if (player == null) {
            source.sendFeedback(new LiteralText("Not connected XD."), false);
            return 1;
        }
        ServerPlayerEntity sourcePlayer = source.getPlayer();
        if (permsExtension.getPlayerPerms().containsKey(sourcePlayer.getUuidAsString())) {
            if (permsExtension.getPlayerPerms().get(sourcePlayer.getUuidAsString()).getId() == PermsLevels.MEMBER.getId()) {
                source.sendFeedback(new LiteralText("You are not allowed to run this command."), false);
                return 1;
            }
        } else {
            source.sendFeedback(new LiteralText("Error."), false);
            return 1;
        }
        sourcePlayer.teleport(player.getWorld(), player.getX(), player.getY(), player.getZ(), sourcePlayer.getYaw(), sourcePlayer.getPitch());
        return 1;
    }

    @Override
    public ExtensionSettings extensionSettings() {
        return this.getSettings();
    }
}
