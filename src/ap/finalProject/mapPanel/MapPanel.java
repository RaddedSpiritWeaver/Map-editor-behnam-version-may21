package aut.ap.mapPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by sarb on 5/16/17.
 */

public class MapPanel extends JPanel
{

    private Tile[][] tiles;

    private int widthCoord;
    private int heightCoord;

    private int panelWidth;
    private int panelHeight;

    private int tileSize;
    private int cotang;

    private int xRoot;
    private int yRoot;

    private CameraManager cameraManager;

    public MapPanel(int widthCoord, int heightCoord, int panelWidth, int panelHeight, int tileSize, int cotang)
    {
        xRoot = 0;
        yRoot = 0;

        this.widthCoord = widthCoord;
        this.heightCoord = heightCoord;

        this.panelHeight = panelHeight;
        this.panelWidth = panelWidth;

        tiles = new Tile[widthCoord][heightCoord];

        for (int j = 0; j < heightCoord; j++)
            for (int i = 0; i < widthCoord; i++)
                tiles[i][j] = new Tile(i, j);

        this.tileSize = tileSize;
        this.cotang = cotang;

        setCameraManager();
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.darkGray);
        g2.fillRect(0, 0, getWidth(),getHeight());

        for (int j = 0; j < heightCoord; j++)
            for (int i = 0; i < widthCoord; i++)
                if (i > xRoot-2 && j > yRoot-2 && i < xRoot + getHorizontaltiles() +1 && j < yRoot + getVerticalTiles() +1)
                {
                    g2.setColor(tiles[i][j].getColor());
                    g2.fillPolygon(tiles[i][j].getPolygon(xRoot, yRoot, tileSize,cotang));
                }
        for (int j = 0; j < getVerticalTiles(); j++)
        {
            g2.setColor(Color.BLACK);
            g2.drawLine(0 , j * (tileSize / (2* cotang)), getWidth(),j * (tileSize / (2* cotang)));
        }

        for (int i = 0; i < getHorizontaltiles(); i++)
        {
            g2.setColor(Color.BLACK);
            g2.drawLine( i*tileSize, 0, i*tileSize , getHeight() );
        }
    }

    private void setCameraManager()
    {
        cameraManager = new CameraManager(this);
        this.addMouseWheelListener(cameraManager);
        this.addKeyListener(cameraManager);
        this.addMouseMotionListener(cameraManager);
    }

    public static void main(String[] args)
    {
        JFrame myFrame = new JFrame("MAP EDITOR");
        myFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        myFrame.setLayout(new BorderLayout());
        MapPanel mapPanel = new MapPanel(50,100,1500,1000,160,2);
        myFrame.add( mapPanel, BorderLayout.CENTER );

        // injash khoob nist ( cameraManager behtare be panel add she vali dare be frame add mishe)
        myFrame.addKeyListener(mapPanel.cameraManager);
        myFrame.addMouseListener(new Selector(mapPanel));
        mapPanel.addMouseListener(new Selector(mapPanel));

        myFrame.setResizable(false);
        myFrame.setUndecorated(true);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        gd.setFullScreenWindow(myFrame);

        myFrame.setVisible(true);
    }

    public void setTileColor(int x, int y, Color color)
    {
        tiles[x][y].setColor(color);
    }

    public int getHorizontaltiles()
    {
        return getWidth()/ tileSize;
    }

    public int getVerticalTiles()
    {
        return (getHeight() * 2 * cotang) / tileSize;
    }

    // getters and setters :

    public int getCotang()
    {
        return cotang;
    }

    public int getHeightCoord()
    {
        return heightCoord;
    }

    public int getWidthCoord()
    {
        return widthCoord;
    }

    public int getTileSize()
    {
        return tileSize;
    }

    public void setTileSize(int size)
    {
        this.tileSize = size;
    }

    public void setxRoot(int xRoot)
    {
        this.xRoot = xRoot;
    }

    public void setyRoot(int yRoot)
    {
        this.yRoot = yRoot;
    }

    public int getyRoot()
    {
        return yRoot;
    }

    public Tile getTile(int x, int y)
    {
        return tiles[x][y];
    }

    public int getxRoot()

    {
        return xRoot;
    }
}
