package ap.finalProject.mapPanel;

import ap.finalProject.mapPanel.Resources.*;
import ap.finalProject.mapPanel.terrains.*;

import java.awt.*;
import java.awt.event.*;

/**
 * Created by sarb on 5/19/17.
 */
public class Creator implements MouseListener, KeyListener, MouseMotionListener
{

    private MapPanel source;
    private Terrains terrain;
    private Resources resource;

    private int[][] oddNeighbors = { {1,-1}, {1,1}, {0,-1}, {0,-2}, {1,0}, {0,2}, {0,1}, {-1,0} };
    private int[][] evenNeighbors = { {-1,-1}, {-1,1}, {0,-1}, {0,-2}, {1,0}, {0,2}, {0,1}, {-1,0} };

    public Creator(MapPanel source)
    {
        this.source = source;
        terrain = Terrains.LAWN;
    }

    private void manageDeepWater(int x, int y, long id)
    {
        int[][] neighbors = null;
        if ( y%2 == 0 )
            neighbors = evenNeighbors;
        else
            neighbors = oddNeighbors;

        Tile toCheck = null;
        for (int k = 0; k < 8; k++)
        {
            int i = neighbors[k][0], j = neighbors[k][1];
            if ( x+i >= 0 && x+i < source.getWidthCoord() && y+j >=0 && y+j < source.getHeightCoord() )
            {
                toCheck = source.getTile(x + i, y + j);
                if (toCheck instanceof Beach || toCheck instanceof Lawn || toCheck instanceof Mountain || toCheck instanceof Peak)
                {
                    manageShallowWater(x + i, y + j, id);
                    source.placeTile(new ShallowWater(x + i, y + j), x + i, y + j,id);
                }
            }
        }
    }

    private void manageShallowWater(int x, int y, long id)
    {
        int[][] neighbors = null;
        if ( y%2 == 0 )
            neighbors = evenNeighbors;
        else
            neighbors = oddNeighbors;

        Tile toCheck = null;
        for (int k = 0; k < 8; k++)
        {
            int i = neighbors[k][0], j = neighbors[k][1];
            if ( x+i >= 0 && x+i < source.getWidthCoord() && y+j >=0 && y+j < source.getHeightCoord() )
            {
                toCheck = source.getTile(x + i , y + j);

                if (toCheck instanceof Lawn || toCheck instanceof Mountain || toCheck instanceof Peak)
                {
                    manageBeach(x + i, y + j, id);
                    source.placeTile(new Beach(x + i, y + j), x + i, y + j, id);
                }
            }
        }
    }

    private void manageBeach(int x, int y, long id)
    {
        int[][] neighbors = null;
        if ( y%2 == 0 )
            neighbors = evenNeighbors;
        else
            neighbors = oddNeighbors;

        Tile toCheck = null;
        for (int k = 0; k < 8; k++)
        {
            int i = neighbors[k][0], j = neighbors[k][1];
            if ( x+i >= 0 && x+i < source.getWidthCoord() && y+j >=0 && y+j < source.getHeightCoord() )
            {
                toCheck = source.getTile(x + i, y + j);
                if (toCheck instanceof DeepWater)
                {
                    manageShallowWater(x + i, y + j, id);
                    source.placeTile(new ShallowWater(x + i, y + j), x + i, y + j, id);
                }
                else if (toCheck instanceof Mountain || toCheck instanceof Peak)
                {
                    manageLawn(x + i,y + j, id);
                    source.placeTile(new Lawn(x + i,y + j),x + i,y + j, id);
                }
            }
        }
    }

    private void manageLawn(int x, int y, long id)
    {
        int[][] neighbors = null;
        if ( y%2 == 0 )
            neighbors = evenNeighbors;
        else
            neighbors = oddNeighbors;

        Tile toCheck = null;
        for (int k = 0; k < 8; k++)
        {
            int i = neighbors[k][0], j = neighbors[k][1];
            if ( x+i >= 0 && x+i < source.getWidthCoord() && y+j >=0 && y+j < source.getHeightCoord() )
            {
                toCheck = source.getTile(x + i, y + j);
                if (toCheck instanceof DeepWater || toCheck instanceof ShallowWater)
                {
                    manageBeach(x + i, y + j, id);
                    source.placeTile(new Beach(x + i, y + j), x + i, y + j, id);
                }
                else if (toCheck instanceof Peak)
                {
                    manageMountain(x + i,y + j, id);
                    source.placeTile(new Mountain(x + i,y + j),x + i,y + j, id);
                }
            }
        }
    }

    private void manageMountain(int x, int y, long id)
    {
        int[][] neighbors = null;
        if ( y%2 == 0 )
            neighbors = evenNeighbors;
        else
            neighbors = oddNeighbors;

        Tile toCheck = null;
        for (int k = 0; k < 8; k++)
        {
            int i = neighbors[k][0], j = neighbors[k][1];
            if ( x+i >= 0 && x+i < source.getWidthCoord() && y+j >=0 && y+j < source.getHeightCoord() )
            {
                toCheck = source.getTile(x + i, y + j);
                if (toCheck instanceof DeepWater || toCheck instanceof ShallowWater || toCheck instanceof Beach)
                {
                    manageLawn(x + i, y + j, id);
                    source.placeTile(new Lawn(x + i, y + j), x + i, y + j, id);
                }
            }
        }
    }

    private void managePeak(int x, int y, long id)
    {
        int[][] neighbors = null;
        if ( y%2 == 0 )
            neighbors = evenNeighbors;
        else
            neighbors = oddNeighbors;

        Tile toCheck = null;
        for (int k = 0; k < 8; k++)
        {
            int i = neighbors[k][0], j = neighbors[k][1];
            if ( x+i >= 0 && x+i < source.getWidthCoord() && y+j >=0 && y+j < source.getHeightCoord() )
            {
                toCheck = source.getTile(x + i, y + j);
                if (toCheck instanceof DeepWater || toCheck instanceof ShallowWater || toCheck instanceof Beach || toCheck instanceof Lawn)
                {
                    manageMountain(x + i, y + j, id);
                    source.placeTile(new Mountain(x + i, y + j), x + i, y + j, id);
                }
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {

    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        selectTile(e);
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {

    }

    @Override
    public void mouseEntered(MouseEvent e)
    {

    }

    @Override
    public void mouseExited(MouseEvent e)
    {

    }

    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    public void select(char selected){
        switch ( selected )
        {
            case 's':
                terrain = Terrains.SHALLOW_WATER;
                resource = Resources.RESOURCE;
                break;
            case 'd':
                terrain = Terrains.DEEP_WATER;
                resource = Resources.RESOURCE;
                break;
            case 'l':
                terrain = Terrains.LAWN;
                resource = Resources.RESOURCE;
                break;
            case 'b':
                terrain = Terrains.BEACH;
                resource = Resources.RESOURCE;
                break;
            case 'm':
                terrain = Terrains.MOUNTAIN;
                resource = Resources.RESOURCE;
                break;
            case 'p':
                terrain = Terrains.PEAK;
                resource = Resources.RESOURCE;
                break;
            case '1':
                terrain = Terrains.TILE;
                resource = Resources.GOLD_MINE;
                break;
            case '2':
                terrain = Terrains.TILE;
                resource = Resources.IRON_QUARRY;
                break;
            case '3':
                terrain = Terrains.TILE;
                resource = Resources.TREE;
                break;
            case '4':
                terrain = Terrains.TILE;
                resource = Resources.SHALLOW_WATER_FISH;
                break;
            case'5':
                terrain = Terrains.TILE;
                resource = Resources.DEEP_WATER_FISH;
        }
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        switch ( e.getKeyChar() )
        {
            case 's':
                terrain = Terrains.SHALLOW_WATER;
                resource = Resources.RESOURCE;
                break;
            case 'd':
                terrain = Terrains.DEEP_WATER;
                resource = Resources.RESOURCE;
                break;
            case 'l':
                terrain = Terrains.LAWN;
                resource = Resources.RESOURCE;
                break;
            case 'b':
                terrain = Terrains.BEACH;
                resource = Resources.RESOURCE;
                break;
            case 'm':
                terrain = Terrains.MOUNTAIN;
                resource = Resources.RESOURCE;
                break;
            case 'p':
                terrain = Terrains.PEAK;
                resource = Resources.RESOURCE;
                break;
            case '1':
                terrain = Terrains.TILE;
                resource = Resources.GOLD_MINE;
                break;
            case '2':
                terrain = Terrains.TILE;
                resource = Resources.IRON_QUARRY;
                break;
            case '3':
                terrain = Terrains.TILE;
                resource = Resources.TREE;
                break;
            case '4':
                terrain = Terrains.TILE;
                resource = Resources.SHALLOW_WATER_FISH;
                break;
            case'5':
                terrain = Terrains.TILE;
                resource = Resources.DEEP_WATER_FISH;
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {

    }

    @Override
    public void mouseDragged(MouseEvent e)
    {
        selectTile(e);
    }

    @Override
    public void mouseMoved(MouseEvent e)
    {

    }

    private void selectTile(MouseEvent e)
    {
        if (terrain != Terrains.TILE)
            placeTerrain(e);
        else if (resource != Resources.RESOURCE)
            placeResource(e);
    }

    private void placeResource(MouseEvent e)
    {
        int x = e.getX() / source.getTileSize();
        int y = e.getY() * 2 * source.getCotang() / source.getTileSize();

        int xtile = source.getxRoot() + x;
        int ytile = source.getyRoot() + y;

        Point point = e.getPoint();

        boolean isValid = false;

        if (xtile >= 0 && xtile < source.getWidthCoord() && ytile >= 0 && ytile < source.getHeightCoord() && source.getTile(xtile, ytile).getPolygon(source.getxRoot(), source.getyRoot(), source.getTileSize(), source.getCotang()).contains(point))
        {
            isValid = true;
        } else if (ytile - 1 >= 0 && source.getTile(xtile, ytile - 1).getPolygon(source.getxRoot(), source.getyRoot(), source.getTileSize(), source.getCotang()).contains(point))
        {
            ytile--;
            isValid = true;
        } else if (xtile - 1 >= 0 && source.getTile(xtile - 1, ytile).getPolygon(source.getxRoot(), source.getyRoot(), source.getTileSize(), source.getCotang()).contains(point))
        {
            xtile--;
            isValid = true;
        } else if (xtile - 1 >= 0 && ytile - 1 >= 0 && source.getTile(xtile - 1, ytile - 1).getPolygon(source.getxRoot(), source.getyRoot(), source.getTileSize(), source.getCotang()).contains(point))
        {
            xtile--;
            ytile--;
            isValid = true;
        }

        if (!isValid)
            return;

        switch ( resource )
        {
            case GOLD_MINE:
                if (source.getTile(xtile,ytile).getTerrainType() == Terrains.PEAK)
                    source.addResource(new GoldMine(xtile,ytile));
                break;
            case IRON_QUARRY:
                if (source.getTile(xtile,ytile).getTerrainType() == Terrains.MOUNTAIN)
                    source.addResource(new IronQuarry(xtile,ytile));
                break;
            case TREE:
                if (source.getTile(xtile,ytile).getTerrainType() == Terrains.LAWN ) {
                    source.addResource(new Tree(xtile, ytile));
                }
                break;
            case SHALLOW_WATER_FISH:
                if (source.getTile(xtile,ytile).getTerrainType() == Terrains.SHALLOW_WATER)
                    source.addResource(new ShallowWaterFish(xtile,ytile));
                break;
            case DEEP_WATER_FISH:
                if (source.getTile(xtile,ytile).getTerrainType() == Terrains.DEEP_WATER)
                    source.addResource(new DeepWaterFish(xtile,ytile));
                break;
        }
        source.repaint();
    }

    private void placeTerrain(MouseEvent e)
    {
        int x = e.getX() / source.getTileSize();
        int y = e.getY() *2 * source.getCotang() / source.getTileSize();

        int xtile = source.getxRoot() + x;
        int ytile = source.getyRoot() + y;

        Point point = e.getPoint();

        boolean isValid = false;

        if ( xtile >= 0 && xtile < source.getWidthCoord() && ytile >= 0 && ytile <source.getHeightCoord() && source.getTile(xtile, ytile).getPolygon(source.getxRoot(),source.getyRoot(),source.getTileSize(),source.getCotang()).contains(point) )
        {
            isValid = true;
        }
        else if ( ytile -1 >= 0 && source.getTile(xtile, ytile -1).getPolygon(source.getxRoot(),source.getyRoot(),source.getTileSize(),source.getCotang()).contains(point) )
        {
            ytile--;
            isValid = true;
        }
        else if ( xtile -1 >= 0 && source.getTile(xtile -1, ytile).getPolygon(source.getxRoot(),source.getyRoot(),source.getTileSize(),source.getCotang()).contains(point) )
        {
            xtile --;
            isValid = true;
        }

        else if ( xtile -1 >= 0 && ytile -1 >=0 && source.getTile(xtile -1, ytile -1).getPolygon(source.getxRoot(),source.getyRoot(),source.getTileSize(),source.getCotang()).contains(point) )
        {
            xtile--;
            ytile--;
            isValid = true;
        }

        if ( !isValid )
            return;

        //******************************** very very dangerous !!! ****************************\\
        if ( source.getTile(xtile,ytile).getTerrainType() == terrain )
            return;

        Tile tile = null;
        long id = source.getLastEventId() +1;

        switch ( terrain )
        {
            case DEEP_WATER:
                tile = new DeepWater(xtile,ytile);
                manageDeepWater(xtile,ytile,id);
                break;
            case SHALLOW_WATER:
                tile = new ShallowWater(xtile,ytile);
                manageShallowWater(xtile,ytile,id);
                break;
            case LAWN:
                tile = new Lawn(xtile,ytile);
                manageLawn(xtile,ytile,id);
                break;
            case BEACH:
                tile = new Beach(xtile,ytile);
                manageBeach(xtile,ytile,id);
                break;
            case MOUNTAIN:
                tile = new Mountain(xtile,ytile);
                manageMountain(xtile,ytile,id);
                break;
            case PEAK:
                tile = new Peak(xtile,ytile);
                managePeak(xtile,ytile,id);
                break;
        }

        source.placeTile(tile,xtile,ytile,id);

        source.repaint();
    }
}
