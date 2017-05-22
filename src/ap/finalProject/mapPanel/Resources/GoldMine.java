package ap.finalProject.mapPanel.Resources;

import ap.finalProject.mapPanel.terrains.Terrains;

import java.awt.*;

/**
 * Created by sarb on 5/20/17.
 */
public class GoldMine extends Resource
{
    static
    {
        availableLocation = Terrains.PEAK;
    }

    public GoldMine(int x, int y)
    {
        super(x, y);
        color = new Color(212,175,55,255);
        amount = ResourceAmounts.GOLD_AMOUNT;
        resourceType = Resources.GOLD_MINE;
    }
}
