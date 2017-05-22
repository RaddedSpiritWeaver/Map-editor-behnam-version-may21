package ap.finalProject.mapPanel.Resources;

import ap.finalProject.mapPanel.terrains.Terrains;

import java.awt.*;

/**
 * Created by sarb on 5/20/17.
 */
public class ShallowWaterFish extends Resource
{
    static
    {
        availableLocation = Terrains.SHALLOW_WATER;
    }

    public ShallowWaterFish(int x, int y)
    {
        super(x, y);
        color = new Color(255,255,0,255);
        amount = ResourceAmounts.SHALLOW_WATER_FISH_AMOUNT;
        resourceType = Resources.SHALLOW_WATER_FISH;
    }
}
