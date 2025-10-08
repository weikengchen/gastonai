package com.chenweikeng.gastonai;

import net.minecraft.network.chat.Component;
import java.util.List;

public class ScoreBoardEntry {
    public int Queen;
    public int Hook;
    public int Hades;
    public int Scar;
    public int Maleficent;
    public int Cruella;

    public ScoreBoardEntry(int queen, int hook, int hades, int scar, int maleficent, int cruella) {
        this.Queen = queen;
        this.Hook = hook;
        this.Hades = hades;
        this.Scar = scar;
        this.Maleficent = maleficent;
        this.Cruella = cruella;
    }

    public static ScoreBoardEntry fromLore(List<Component> lines){
        ScoreBoardEntry entry = new ScoreBoardEntry(0, 0, 0, 0, 0, 0);
        for (int i = 2; i < lines.size(); i++) {
            String str = lines.get(i).getString();
            String s1 = str.split("- ")[2];
            String s2 = s1.split(" ")[0];

            if(str.startsWith("- Scar")) {
                entry.Scar = Integer.parseInt(s2);
            }
            if(str.startsWith("- Hook")) {
                entry.Hook = Integer.parseInt(s2);
            }
            if(str.startsWith("- Queen")) {
                entry.Queen = Integer.parseInt(s2);
            }
            if(str.startsWith("- Hades")) {
                entry.Hades = Integer.parseInt(s2);
            }
            if(str.startsWith("- Maleficent")) {
                entry.Maleficent = Integer.parseInt(s2);
            }
            if(str.startsWith("- Cruella")) {
                entry.Cruella = Integer.parseInt(s2);
            }
        }
        return entry;
    }
}