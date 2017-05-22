package aut.ap.mapPanel;

import java.awt.*;
import java.util.Random;

/**
 * Created by sarb on 5/16/17.
 */
public class Tile
{

    private int x,y;

    private static Random random;
    static {
        random = new Random(System.currentTimeMillis());
    }

    private Color color;

    public Tile(int x, int y)
    {
        this.x = x;
        this.y = y;

        color = new Color(random.nextInt(32)* 8 , random.nextInt(32)* 8,random.nextInt(32)* 8,210);
//        color = Color.white;
    }

    public Polygon getPolygon(int xRoot, int yRoot, int size, int cotang)
    {
         Polygon polygon = new Polygon();

         int relx = ( (x - xRoot) * size ) + size/2 ;
         int rely = (y - yRoot) * size / (2* cotang) ;
         if(y%2 == 1)
             relx += size / 2;

         polygon.addPoint(relx, rely);
         polygon.addPoint( relx - size/2, rely + size/(2*cotang));
         polygon.addPoint(relx, rely + size/cotang);
         polygon.addPoint(relx + size/2, rely + size/(2*cotang));

         return polygon;
    }

    public void setColor(Color color)
    {
        this.color = color;
    }

    public Color getColor()
    {
        return color;
    }
}
