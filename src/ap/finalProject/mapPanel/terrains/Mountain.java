package ap.finalProject.mapPanel.terrains;

import ap.finalProject.mapPanel.Tile;

import java.awt.*;

/**
 * Created by sarb on 5/20/17.
 */
public class Mountain extends Tile
{
    public Mountain(int x, int y)
    {
        super(x, y);
        this.terrainType = Terrains.MOUNTAIN;
        this.color = new Color(139,69,19);
    }
}
