package ap.finalProject.mapPanel.terrains;

import ap.finalProject.mapPanel.Tile;

import java.awt.*;

/**
 * Created by sarb on 5/19/17.
 */
public class Lawn extends Tile
{
    public Lawn(int x, int y)
    {
        super(x, y);
        this.terrainType = Terrains.LAWN;
        this.color = new Color(153,255,51,255);
    }
}
