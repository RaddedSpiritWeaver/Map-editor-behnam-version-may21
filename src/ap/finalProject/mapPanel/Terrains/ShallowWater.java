package ap.finalProject.mapPanel.terrains;

import ap.finalProject.mapPanel.Tile;

import java.awt.*;

/**
 * Created by sarb on 5/19/17.
 */
public class ShallowWater extends Tile
{
    public ShallowWater(int x, int y)
    {
        super(x, y);
        this.terrainType = Terrains.SHALLOW_WATER;
        this.color = Color.cyan;
    }
}
