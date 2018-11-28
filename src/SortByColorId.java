
import java.util.Comparator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Shokuno
 */
public class SortByColorId implements Comparator<MinecraftColor> {
    @Override
    public int compare(MinecraftColor a, MinecraftColor b) {
        return a.getId() - b.getId();
    }
} 