package ap.finalProject.mapPanel.Resources;

import ap.finalProject.mapPanel.terrains.Terrains;

import java.awt.*;

/**
 * Created by sarb on 5/20/17.
 */
public class IronQuarry extends Resource
{
    static
    {
        availableLocation = Terrains.MOUNTAIN;
    }

    public IronQuarry(int x, int y)
    {
        super(x, y);
        color = new Color(128,128,128);
        amount = ResourceAmounts.IRON_AMOUNT;
        resourceType = Resources.IRON_QUARRY;
    }
}
