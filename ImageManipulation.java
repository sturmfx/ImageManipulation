/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagetext;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author lordstorm
 */
public class ImageManipulation 
{

    /**
     * @param args the command line arguments
     */
    
    static Color[] color_list = {Color.BLACK, Color.RED, Color.GREEN, Color.YELLOW, Color.BLUE};
    public static void main(String[] args) 
    {
        int width = 1920;
        int height = 1080;
        BufferedImage image = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
        String directory = "C:\\Users\\lordstorm\\Pictures\\out.png";
        Graphics2D g = image.createGraphics();
        
        g.setColor(Color.white);
        g.fillRect(0, 0, image.getWidth(), image.getHeight());
        
        noise(image, 10);
        add_random_circles(30, 50, 150, 5, image, g);
        add_random_rectangles(30, 50, 150, 50, 150, 5, image, g);
        
        save_to(image, directory);
        g.dispose();
    }
    
    public static void save_to(BufferedImage im, String dir)
    {
        try 
        {
            ImageIO.write(im, "png",new File(dir));
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(ImageManipulation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void add_random_circles(int n, int min_diam, int max_diam, int brush_width, BufferedImage im, Graphics2D g2d)
    {
        Random r = new Random();
        for(int i = 0; i < n; i++)
        {
            g2d.setColor(color_list[r.nextInt(5)]);
            int d = min_diam + r.nextInt(max_diam-min_diam);
            int w = r.nextInt(im.getWidth());
            int h = r.nextInt(im.getHeight());
            g2d.fillOval(w, h, d, d);
            g2d.setColor(Color.white);
            g2d.fillOval(w + brush_width, h + brush_width, d - 2*brush_width, d - 2*brush_width);
        }
        
    }
    
    public static void add_random_rectangles(int n, int min_w, int max_w, int min_h, int max_h,  int brush_width, BufferedImage im, Graphics2D g2d)
    {
        Random r = new Random();
        for(int i = 0; i < n; i++)
        {
            g2d.setColor(color_list[r.nextInt(5)]);
            int dw = min_w + r.nextInt(max_w-min_w);
            int dh = min_h + r.nextInt(max_h - min_h);
            int w = r.nextInt(im.getWidth());
            int h = r.nextInt(im.getHeight());
            g2d.fillRect(w, h, dw, dh);
            g2d.setColor(Color.white);
            g2d.fillRect(w + brush_width, h + brush_width, dw - 2*brush_width, dh - 2*brush_width);
        }
        
    }
    
    public static void noise(BufferedImage im, int foreach)
    {
        int n = 0;
        int m = 0;
        Random r = new Random();
        for(int i = 0; i < im.getWidth(); i++)
        {
            for(int j = 0; j < im.getHeight(); j++)
            {
                n = r.nextInt();
                m = color_list[r.nextInt(5)].getRGB();
                if(n % foreach == 0)
                {
                    im.setRGB(i, j, m);
                }
            }
        }
    }
}
