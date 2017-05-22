package ap.finalProject.mapPanel;

import ap.finalProject.mapPanel.terrains.Terrains;

import java.awt.*;
import java.util.Random;

/**
 * Created by sarb on 5/16/17.
 */
public class Tile
{

    protected int x,y;
    protected Color color;
    protected Terrains terrainType;

    public Tile(int x, int y)
    {
        this.x = x;
        this.y = y;

        terrainType = Terrains.TILE;
        color = Color.WHITE;
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

    public Terrains getTerrainType()
    {
        return terrainType;
    }

    public Color getColor()
    {
        return color;
    }
}
