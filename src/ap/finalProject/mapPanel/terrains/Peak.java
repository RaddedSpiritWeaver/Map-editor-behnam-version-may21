package ap.finalProject.mapPanel.terrains;

import ap.finalProject.mapPanel.Tile;

import java.awt.*;

/**
 * Created by sarb on 5/20/17.
 */
public class Peak extends Tile
{
    public Peak(int x, int y)
    {
        super(x, y);
        this.terrainType = Terrains.PEAK;
        this.color = new Color(51,25,0,255);
    }
}
