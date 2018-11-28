/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Shokuno
 */
public class MinecraftFileReader {
    
    
    /**
     * OLD color data reader
     * @deprecated 
     * @param file
     * @param frame
     * @return
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public static MinecraftMap readFile(File file, MainFrame frame) throws FileNotFoundException, IOException {       
        MinecraftMap map = new MinecraftMap();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;

            String lineSplitter = "  ";

            int column = 0;
            int row = 0;
            while ((line = br.readLine()) != null) {

                String[] parts = line.split(lineSplitter);
                for (int i = 0; i < parts.length; i++) {
                    if (column >= 128) {
                        column = 0;
                        row++;
                    }
                    else if(column < 128 && row < 128) {

                        int id = Integer.valueOf(parts[i]);
                        column++;
                    }
                }
            }
        }
        return map;
    }
    
    
    /**
     * Reads color data
     * @param file
     * @param mapColors
     * @return
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public static MinecraftMap readColorBinaryHexFile(File file, MinecraftMapColors mapColors) throws FileNotFoundException, IOException {       
        MinecraftMap map = new MinecraftMap();
        
        FileInputStream fin = new FileInputStream(file.getAbsolutePath());

        int len;
        byte data[] = new byte[16];
        
        int column = 0;
        int row = 0;

        // Read bytes until EOF is encountered.
        do {
            len = fin.read(data);
            for (int z = 0; z < len; z++) {
                int id = data[z] & 0xFF;

                if (column >= MinecraftMap.WIDTH) {
                    column = 0;
                    row++;
                } //else if
                if (column < MinecraftMap.WIDTH && row < MinecraftMap.WIDTH) {

                    if (mapColors.getByColorIndex(id) != null) {
//                        System.out.println("reached color got: row:"+row+"; col:"+column);
                        Color color = mapColors.getByColorIndex(id).getColor();
                        map.addColor(row, column, color);

                    } else {
//                        System.out.println("reached color fail: row:" + row + "; col:" + column);
                        map.addColor(row, column, null);
                    }
                    column++;
                }
//                System.out.print(Integer.toBinaryString(id).toUpperCase()+"  ");
//                if (z==15) {
//                    System.out.print("\n");
//                }
            }
        } while (len != -1);

        return map;
    }
    
    public static void saveMapColorsReadable(MinecraftMapColors colors) throws IOException {
        String path = colors.getVersion().replace(".", "--") + ".mmcv";

        FileWriter fileWriter = new FileWriter(path);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        //for first line - first line is skiped since it's a comment
        printWriter.println("// THIS IS A COMMENT! The formatting for colors is as follows (without single quotes) 'ID: 4; RGB: 89,125,39' - one entry per line - ID need to be unique and rgb numbers between 0 and 255");
        
        //for all following lines
        for (int i = 0; i < colors.colorsSaveToString().length; i++) {
            printWriter.println(colors.colorsSaveToString()[i]);
        }
        printWriter.close();
    }

    public static MinecraftMapColors loadMapColorsReadable(String version) throws FileNotFoundException, IOException {
        MinecraftMapColors result = null;

        String path = version.replace(".", "--") + ".mmcv";

        BufferedReader br = new BufferedReader(new FileReader(path));
        String rgbSplitter = ",", valueSplitter = "; ", dataSplitter = ": ";
        String line;

        result = new MinecraftMapColors(version);
        //for first line - first line is skiped since it's a comment
        br.readLine();
        
        //for all following lines
        while ((line = br.readLine()) != null) {
            String[] temp = line.split(valueSplitter);
//                System.out.println(line);
            int id = Integer.valueOf(temp[0].split(dataSplitter)[1]);
            int r = Integer.valueOf(temp[1].split(dataSplitter)[1].split(rgbSplitter)[0]);
            int g = Integer.valueOf(temp[1].split(dataSplitter)[1].split(rgbSplitter)[1]);
            int b = Integer.valueOf(temp[1].split(dataSplitter)[1].split(rgbSplitter)[2]);

            result.addColor(new MinecraftColor(id, new Color(r, g, b)));
        }
        br.close();

        return result;
    }
    
    public static boolean checkForMapColorsFileExsitence(String version) throws IllegalArgumentException {
        checkForInput(version);
        
        String path = version.replace(".", "--") + ".mmcv";
        
        File test = new File(path);
        return test.exists();
    }
    
    public static void checkForInput(String text) throws IllegalArgumentException {
        if(text.equals("")) {
            throw new IllegalArgumentException("No input was given.");
        }
    }
}
