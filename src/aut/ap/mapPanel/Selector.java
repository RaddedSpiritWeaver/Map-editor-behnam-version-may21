package aut.ap.mapPanel;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by sarb on 5/19/17.
 */
public class Selector implements MouseListener
{

    private MapPanel source;

    public Selector(MapPanel source)
    {
        this.source = source;
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        int x = (e.getX() - source.getTileSize()/2) / source.getTileSize();
        int y = e.getY() *2 * source.getCotang() / source.getTileSize();

        int xtile = source.getxRoot() + x;
        int ytile = source.getyRoot() + y;

        source.setTileColor( xtile, ytile, Color.red);

        source.repaint();
    }

    @Override
    public void mousePressed(MouseEvent e)
    {

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
}
