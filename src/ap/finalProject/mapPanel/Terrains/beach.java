package ap.finalProject.mapPanel.terrains;

import ap.finalProject.mapPanel.Tile;

import java.awt.*;

/**
 * Created by sarb on 5/19/17.
 */
public class Beach extends Tile
{
    public Beach(int x, int y)
    {
        super(x, y);
        this.terrainType = Terrains.BEACH;
        this.color = new Color(245,222,179,255);
    }
}
