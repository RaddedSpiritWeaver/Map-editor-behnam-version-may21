package ap.finalProject.mapPanel.Resources;

import ap.finalProject.mapPanel.terrains.Terrains;

import java.awt.*;

/**
 * Created by sarb on 5/20/17.
 */
public class DeepWaterFish extends Resource
{
    static
    {
        availableLocation = Terrains.DEEP_WATER;
    }

    public DeepWaterFish(int x, int y)
    {
        super(x, y);
        color = new Color(255,0,0,255);
        amount = ResourceAmounts.DEEP_WATER_FISH_AMOUNT;
        resourceType = Resources.DEEP_WATER_FISH;
    }
}
