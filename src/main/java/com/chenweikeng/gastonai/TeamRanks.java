package com.chenweikeng.gastonai;

import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.List;

public enum TeamRanks {
    Queen,
    Hook,
    Hades,
    Scar,
    Maleficent,
    Cruella;

    public static List<TeamRanks> parse(List<Component> lines) {
        List<TeamRanks> teams = new ArrayList<>();
        for (int i = 2; i < lines.size(); i++) {
            String str = lines.get(i).getString();
            if(str.startsWith("- Scar")) {
                teams.add(TeamRanks.Scar);
            }
            if(str.startsWith("- Hook")) {
                teams.add(TeamRanks.Hook);
            }
            if(str.startsWith("- Queen")) {
                teams.add(TeamRanks.Queen);
            }
            if(str.startsWith("- Hades")) {
                teams.add(TeamRanks.Hades);
            }
            if(str.startsWith("- Maleficent")) {
                teams.add(TeamRanks.Maleficent);
            }
            if(str.startsWith("- Cruella")) {
                teams.add(TeamRanks.Cruella);
            }
        }
        return teams;
    }
}
