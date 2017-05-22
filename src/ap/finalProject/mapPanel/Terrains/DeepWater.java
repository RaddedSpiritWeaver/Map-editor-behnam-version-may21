package ap.finalProject.mapPanel.terrains;

import ap.finalProject.mapPanel.Tile;

/**
 * Created by sarb on 5/19/17.
 */

public class DeepWater extends Tile
{
    public DeepWater(int x, int y)
    {
        super(x, y);
        this.terrainType = Terrains.DEEP_WATER;
        this.color = color.BLUE;
    }
}
