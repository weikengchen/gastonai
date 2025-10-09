package com.chenweikeng.gastonai.mixin;

import com.chenweikeng.gastonai.GastonAI;
import com.chenweikeng.gastonai.ScoreBoardEntry;
import com.chenweikeng.gastonai.TeamRanks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundContainerSetContentPacket;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemLore;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(ClientPacketListener.class)
public class GastonMessageMixin {
    @Inject(at = @At("HEAD"), method = "handleContainerContent")
    public void handleContainerContent(ClientboundContainerSetContentPacket clientboundContainerSetContentPacket, CallbackInfo ci) {
        Player player = Minecraft.getInstance().player;
        if(player != null) {
            List<ItemStack> itemStacks = clientboundContainerSetContentPacket.items();

            for(ItemStack itemStack : itemStacks) {
                ItemLore itemLore = itemStack.getComponents().get(DataComponents.LORE);
                if (itemLore != null) {
                    List<Component> lines = itemLore.lines();
                    if(!lines.isEmpty() && lines.getFirst().getString().startsWith("Located in ")) {
                        if(lines.size() > 1 && lines.get(1).getString().startsWith("Top Teams:")) {
                            net.minecraft.network.chat.Component chatComponent = itemStack.getComponents().get(DataComponents.CUSTOM_NAME);
                            if(chatComponent != null) {
                                List<Component> list = chatComponent.toFlatList();
                                if(!list.isEmpty()) {
                                    String rideName = list.getFirst().getString();
                                    GastonAI.LOGGER.info(rideName);
                                    GastonAI.RANKS.put(
                                            rideName,
                                            TeamRanks.parse(lines)
                                    );
                                    GastonAI.MAP.put(
                                            rideName,
                                            ScoreBoardEntry.fromLore(lines)
                                    );
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}