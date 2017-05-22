package ap.finalProject.mapPanel.terrains;

import ap.finalProject.mapPanel.Tile;

/**
 * Created by sarb on 5/20/17.
 */
public class TerrainEvent
{
    private Tile oldTile;
    private Tile newTile;

    private int x,y;

    private long id;

    public TerrainEvent(Tile oldTile, Tile newTile, int x, int y, long id)
    {
        this.oldTile = oldTile;
        this.newTile = newTile;
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public Tile getOldTile()
    {
        return oldTile;
    }

    public Tile getNewTile()
    {
        return newTile;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public long getId()
    {
        return id;
    }
}
