package ap.finalProject.mapPanel.Resources;

import ap.finalProject.mapPanel.terrains.Terrains;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

/**
 * Created by sarb on 5/20/17.
 */
public class Resource
{
    protected int x,y;

    public ImageIcon getImage() {
        return image;
    }

    public void setImage(ImageIcon image) {
        this.image = image;
    }

    protected ImageIcon image;
    protected static Terrains availableLocation;
    protected int amount;

    protected Color color;
    protected Resources resourceType;

    public Resource(int x, int y)
    {
        this.x = x;
        this.y = y;

        this.color = Color.WHITE;
        this.resourceType = Resources.RESOURCE;
    }

    public Shape getShape(int xRoot, int yRoot, int size, int cotang)
    {

        int relx = ( (x - xRoot) * size ) + size/2 ;
        int rely = (y - yRoot) * size / (2* cotang) ;
        if(y%2 == 1)
        {
            relx += size / 2;
        }

        Shape roundRectangle = new RoundRectangle2D.Double(relx - size/4, rely + size/(3*cotang) , size/2, size/(3*cotang),40,20);
        return roundRectangle;
    }

    public static Terrains getAvailableLocation()
    {
        return availableLocation;
    }

    public Color getColor()
    {
        return color;
    }
}
