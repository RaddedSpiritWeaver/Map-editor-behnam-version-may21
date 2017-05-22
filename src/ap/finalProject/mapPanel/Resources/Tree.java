package ap.finalProject.mapPanel.Resources;

import ap.finalProject.mapPanel.terrains.Terrains;

import java.awt.*;

/**
 * Created by sarb on 5/20/17.
 */
public class Tree extends Resource
{
    static
    {
        availableLocation = Terrains.LAWN;
    }

    public Tree(int x, int y)
    {
        super(x, y);
        color = new Color(139,69,19);
        amount = ResourceAmounts.LUMBER_AMOUNT;
        resourceType = Resources.TREE;
    }
}
