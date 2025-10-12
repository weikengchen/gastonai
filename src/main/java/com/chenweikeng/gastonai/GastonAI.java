package com.chenweikeng.gastonai;

import com.google.common.base.Optional;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static java.lang.Math.min;

public class GastonAI implements ModInitializer {
	public static final String MOD_ID = "gastonai";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final ConcurrentHashMap<String, List<TeamRanks>> RANKS = new ConcurrentHashMap<>();

    public static final ConcurrentHashMap<String, ScoreBoardEntry> MAP = new ConcurrentHashMap<>();

    public static String TEXT = "";

	@Override
	public void onInitialize() {
		LOGGER.info("Hello I am Gaston!");

        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            var gastonai = ClientCommandManager.literal("gastonai");

            gastonai.then(ClientCommandManager.literal("count").executes(
                    command -> {
                        int hookCount = 0;
                        for(var entry: RANKS.entrySet()){
                            List<TeamRanks> ranks = RANKS.get(entry.getKey());
                            if(!ranks.isEmpty() && ranks.getFirst().equals(TeamRanks.Hook)) {
                                hookCount += 5;
                            }
                            if(ranks.size() > 1 && ranks.get(1).equals(TeamRanks.Hook)) {
                                hookCount += 4;
                            }
                            if(ranks.size() > 2 && ranks.get(2).equals(TeamRanks.Hook)) {
                                hookCount += 3;
                            }
                            if(ranks.size() > 3 && ranks.get(3).equals(TeamRanks.Hook)) {
                                hookCount += 2;
                            }
                            if(ranks.size() > 4 && ranks.get(4).equals(TeamRanks.Hook)) {
                                hookCount += 1;
                            }
                        }
                        command.getSource().sendFeedback(Component.literal(
                                String.format("Hook has %d treats", hookCount)
                        ));
                        return 0;
                    }
            ));
            gastonai.then(ClientCommandManager.literal("compute").executes(
                    command -> {
                        List<Map.Entry<Strategy, Integer>> list = new ArrayList<>();
                        for(var entry : MAP.entrySet()) {
                            int time = TimeOwner.getTime(entry.getKey());
                            if(time == 99999) {
                                LOGGER.error("Unknown ride {}", entry.getKey());
                                return 0;
                            }

                            Optional<Integer> larger = Optional.absent();
                            ScoreBoardEntry value = entry.getValue();

                            if(value.Scar >= value.Hook) {
                                larger = Optional.of(value.Scar);
                            }
                            if(value.Cruella >= value.Hook) {
                                if(larger.isPresent()) {
                                    if(value.Cruella < larger.get()) {
                                        larger = Optional.of(value.Cruella);
                                    }
                                } else {
                                    larger = Optional.of(value.Cruella);
                                }
                            }
                            if(value.Hades >= value.Hook) {
                                if(larger.isPresent()) {
                                    if(value.Hades < larger.get()) {
                                        larger = Optional.of(value.Hades);
                                    }
                                } else {
                                    larger = Optional.of(value.Hades);
                                }
                            }
                            if(value.Queen >= value.Hook) {
                                if(larger.isPresent()) {
                                    if(value.Queen < larger.get()) {
                                        larger = Optional.of(value.Queen);
                                    }
                                } else {
                                    larger = Optional.of(value.Queen);
                                }
                            }
                            if(value.Maleficent >= value.Hook) {
                                if(larger.isPresent()) {
                                    if(value.Maleficent < larger.get()) {
                                        larger = Optional.of(value.Maleficent);
                                    }
                                } else {
                                    larger = Optional.of(value.Maleficent);
                                }
                            }

                            if(larger.isPresent()) {
                                list.add(Map.entry(new Strategy(entry.getKey(), larger.get() - value.Hook + 1), (larger.get() - value.Hook + 1) * time));
                            }
                        }

                        list.sort(Map.Entry.comparingByValue());

                        Player player = Minecraft.getInstance().player;
                        assert player != null;

                        StringBuilder out = new StringBuilder();
                        for(int i = 0; i < min(list.size(), 10); i++) {
                            out.append(String.format("Ride %s for %d times\n", list.get(i).getKey().rideName(), list.get(i).getKey().times()));
                        }
                        String outResult = out.toString();

                        GastonAI.TEXT = outResult;

                        command.getSource().sendFeedback(Component.literal(outResult));

                        return 0;
                    }
            ));
            dispatcher.register(gastonai);
        });

        HudElementRegistry.attachElementBefore(
                VanillaHudElements.CHAT,
                ResourceLocation.fromNamespaceAndPath(GastonAI.MOD_ID, "before_chat"),
                GastonAI::render);
	}

    public static void render(GuiGraphics context, DeltaTracker tickCounter){
        int color = 0xFFFF0000; // Red

        if(!GastonAI.TEXT.isEmpty()) {
            String[] lines = GastonAI.TEXT.split("\\R");

            for(int i = 0; i < lines.length; i++) {
                context.drawString(
                        Minecraft.getInstance().font,
                        lines[i],
                        50,
                        50 + i * 10,
                        color
                );
            }
        }
    }
}