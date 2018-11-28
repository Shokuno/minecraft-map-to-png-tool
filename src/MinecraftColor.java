
import java.awt.Color;
import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Shokuno
 */
class MinecraftColor implements Serializable {
    private int id;
    private Color color;

    public MinecraftColor(int id, Color color) {
        this.id = id;
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    
    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "ID: "+id+"  RGB: "+color.getRed()+","+color.getGreen()+","+color.getBlue();
    }
    
    public String saveToString() {
        return "ID: "+id+"; RGB: "+color.getRed()+","+color.getGreen()+","+color.getBlue();
    }
}
