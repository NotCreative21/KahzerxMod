package com.kahzerx.kahzerxmod.extensions.hatExtension;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Hand;

import static net.minecraft.server.command.CommandManager.literal;

public class HatCommand {
    public void register(CommandDispatcher<ServerCommandSource> dispatcher, HatExtension hat) {
        dispatcher.register(literal("hat").
                requires(server -> hat.extensionSettings().isEnabled()).
                executes(context -> {
                    ServerPlayerEntity player = context.getSource().getPlayer();
                    if (player.getMainHandStack().isEmpty()) {
                        context.getSource().sendFeedback(new LiteralText("Tienes que tener algo en la mano."), false);
                        return 1;
                    }
                    if (player.getMainHandStack().getCount() != 1) {
                        context.getSource().sendFeedback(new LiteralText("Solo 1 item mejor"), false);
                        return 1;
                    }
                    int mainHandStack = player.getInventory().getSlotWithStack(player.getMainHandStack());
                    ItemStack mainHand = player.getInventory().getStack(mainHandStack);
                    ItemStack head = player.getEquippedStack(EquipmentSlot.HEAD);
                    player.equipStack(EquipmentSlot.HEAD, mainHand);
                    player.setStackInHand(Hand.MAIN_HAND, head);
                    return 1;
                }));
    }
}