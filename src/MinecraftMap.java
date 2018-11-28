/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author Shokuno
 */
class MinecraftMap {
    
    public final static int WIDTH = 128;
    
    private Color[][] mapColorOld = new Color[128][128];
    private Color[] mapColor = new Color[16384];

    /**
     * @deprecated 
     * @param row
     * @param column
     * @param color 
     */
    public void addColourOld(int row, int column, Color color) {
//        System.out.println("COLOR: " + color.getRed() + "," + color.getGreen() + "," + color.getBlue());
        mapColorOld[column][row] = color;
    }
    
    public void addColor(int row, int column, Color color) {
        // get the correct id, check: https://minecraft.gamepedia.com/Map_item_format
        int id = row + column * WIDTH;
        
        mapColor[id] = color;
    }
    
    public Color getColor(int row, int column) {
        // get the correct id, check: https://minecraft.gamepedia.com/Map_item_format
        int id = row + column * WIDTH;
        
        return mapColor[id];
    }
    
    /**
     * @deprecated 
     * @param g2
     * @param width
     * @param height 
     */
    public void drawOld(Graphics2D g2, int width, int height) {
//        System.out.println("------------------------------------ DRAW START ------------------------------------");
        int counter = 0;
        for (int i = 0; i < 127; i++) {
            for (int j = 0; j < 127; j++) {
//                System.out.println("coordinate: "+j+","+i);
                if (mapColorOld[j][i] != Color.PINK && mapColorOld[j][i] != null) {
//               System.out.println("Color: "+mapColor[j][i].getRed()+","+mapColor[j][i].getGreen()+","+mapColor[j][i].getBlue());
                    
                    
                    g2.setColor(mapColorOld[j][i]);
                    g2.fillRect(i*4, j*4, 4, 4);
//                    g2.fillRect(i, j, 1, 1);
                }
                else if (mapColorOld[j][i] == null) {
                    counter ++;
                    System.out.println("Null pointer: "+counter);
                }
            }
        }
    }
    
    public void draw(Graphics2D g2, int width, int height) {
        int emptyPixelCounter = 0;
        //calculate the ammount of pixels each block occupies in the image
        int pixelPerBlock = 1;
        int tempWidth = width;
        while ((tempWidth = tempWidth/2) >= 128) pixelPerBlock = pixelPerBlock*2;
        
        for (int i = 0; i < 128; i++) {
            for (int j = 0; j < 128; j++) {
                if (getColor(j, i) != Color.PINK && getColor(j, i) != null) {
                    System.out.println("row: "+j+"; col: "+i);
                    
                    g2.setColor(getColor(j, i));
                    g2.fillRect(i*pixelPerBlock, j*pixelPerBlock, pixelPerBlock, pixelPerBlock);
                }
                else if (getColor(j, i) == null) {
                    emptyPixelCounter ++;
                    System.out.println("Null pointer: "+emptyPixelCounter);
                }
            }
        }
    }
}
