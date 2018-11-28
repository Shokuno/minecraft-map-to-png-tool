
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Shokuno
 */
public class MinecraftMapColors implements Serializable{
    private String version;
    private ArrayList<MinecraftColor> allColors = new ArrayList<>();

    public MinecraftMapColors(String version) {
        this.version = version;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
    
    public MinecraftColor getByColorIndex(int index) {
        MinecraftColor colour = null;
        for (MinecraftColor e : allColors) {
            if(e.getId() == index) {
//                System.out.println("Color RGB: "+e.getColor().getRed()+", "+e.getColor().getGreen()+","+e.getColor().getBlue());
                colour = e;
            }
        }
        return colour;
    }
    
    public void addColor(MinecraftColor mc) {
        allColors.add(mc);
        sort();
    }

    public boolean remove(int id) {
        boolean success = false;
        if(getByColorIndex(id) != null) {
            success = allColors.remove(getByColorIndex(id));
        }
        return success;
    }

    public int size() {
        return allColors.size();
    }
    
    public String[] colorsToString() {
        String[] list = new String[size()];
        for (int i = 0; i < size(); i++) {
            list[i] = allColors.get(i).toString();
        }
        return list;
    }
    
    public String[] colorsSaveToString() {
        String[] list = new String[size()];
        for (int i = 0; i < size(); i++) {
            list[i] = allColors.get(i).saveToString();
        }
        return list;
    }

    public void sort() {
        Collections.sort(allColors, new SortByColorId());
    }
}
