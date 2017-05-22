package ap.finalProject.mapPanel;

import java.awt.event.*;

/**
 * Created by sarb on 5/16/17.
 */
public class CameraManager implements MouseWheelListener,KeyListener,MouseMotionListener
{

    private static final int TILE_MIN_SIZE = 80 ,TILE_MAX_SIZE = 300;

    private MapPanel source;

    public CameraManager(MapPanel source)
    {
        this.source = source;
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e)
    {

        if (e.getModifiers() == 1)
            return;
        changeZoom((int) e.getPreciseWheelRotation() * 4 );
    }

    private void changeZoom(int speed)
    {
        int changeAmount = source.getTileSize() - speed;

        if( changeAmount <= TILE_MIN_SIZE )
        {
            source.setTileSize(TILE_MIN_SIZE);
            source.repaint();
        }
        else if( changeAmount >= TILE_MAX_SIZE)
        {
            source.setTileSize(TILE_MAX_SIZE);
            source.repaint();
        }
        else
        {
            source.setTileSize(changeAmount);
            source.repaint();
        }
    }

    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        int yRoot = source.getyRoot();
        int xRoot = source.getxRoot();

        switch(e.getKeyCode())
        {
            case KeyEvent.VK_UP:
                if ( yRoot > 2 )
                {
                    source.setyRoot(source.getyRoot() - 1);
                    source.repaint();
                }
                break;
            case KeyEvent.VK_DOWN:
                if( yRoot < source.getHeightCoord() - source.getVerticalTiles())
                {
                    source.setyRoot(source.getyRoot() + 1);
                    source.repaint();
                }
                break;
            case KeyEvent.VK_LEFT:
                if ( xRoot > 2 )
                {
                    source.setxRoot(source.getxRoot() - 1);
                    source.repaint();
                }
                break;
            case KeyEvent.VK_RIGHT:
                if ( xRoot < source.getWidthCoord() - source.getHorizontaltiles())
                {
                    source.setxRoot(source.getxRoot() + 1);
                    source.repaint();
                }
                break;
        }

    }

    @Override
    public void keyReleased(KeyEvent e)
    {

    }

    @Override
    public void mouseDragged(MouseEvent e)
    {

    }

    @Override
    public void mouseMoved(MouseEvent e)
    {
        int yRoot = source.getyRoot();
        int xRoot = source.getxRoot();

        int xPoint = e.getX();
        int yPoint = e.getY();

        Direction dir = null;

        if ( xPoint == 0 )
            dir = Direction.LEFT;
        else if ( xPoint > source.getWidth() -3 )
            dir = Direction.RIGHT;
        if ( yPoint == 0 )
            dir = Direction.UP;
        else if (yPoint > source.getHeight() -10 )
            dir = Direction.DOWN;

        if ( dir == null )
            return;

        switch (dir)
        {
            case UP:
                if (yRoot > 2)
                {
                    source.setyRoot(source.getyRoot() - 1);
                    source.repaint();
                }
                break;
            case DOWN:
                if (yRoot < source.getHeightCoord() - source.getVerticalTiles())
                {
                    source.setyRoot(source.getyRoot() + 1);
                    source.repaint();
                }
                break;
            case LEFT:
                if (xRoot > 2)
                {
                    source.setxRoot(source.getxRoot() - 1);
                    source.repaint();
                }
                break;
            case RIGHT:
                if (xRoot < source.getWidthCoord() - source.getHorizontaltiles())
                {
                    source.setxRoot(source.getxRoot() + 1);
                    source.repaint();
                }
                break;
        }
    }

    private enum Direction
    {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }
}
