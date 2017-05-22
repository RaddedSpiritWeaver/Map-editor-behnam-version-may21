package ap.finalProject.mapPanel;

import ap.finalProject.mapPanel.Resources.Resource;
import ap.finalProject.mapPanel.terrains.DeepWater;
import ap.finalProject.mapPanel.terrains.Lawn;
import ap.finalProject.mapPanel.terrains.TerrainEvent;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

/**
 * Created by sarb on 5/16/17.
 */

public class MapPanel extends JPanel
{

    protected Tile[][] tiles;

    protected int widthCoord;
    protected int heightCoord;

    private int panelWidth;
    private int panelHeight;

    protected int tileSize;
    private int cotang;

    protected int xRoot;
    protected int yRoot;

    private Vector<Resource> resources;

    private CameraManager cameraManager;
    private Creator creator;
    private PlacingArrangement placer;

    public MapPanel(int widthCoord, int heightCoord, int panelWidth, int panelHeight, int tileSize, int cotang)
    {
        xRoot = 1;
        yRoot = 1;

        this.widthCoord = widthCoord;
        this.heightCoord = heightCoord;

        this.panelHeight = panelHeight;
        this.panelWidth = panelWidth;

        tiles = new Tile[widthCoord][heightCoord];

        for (int j = 0; j < heightCoord; j++)
            for (int i = 0; i < widthCoord; i++)
                tiles[i][j] = new DeepWater(i, j);

        this.tileSize = tileSize;
        this.cotang = cotang;

        resources = new Vector<>();

        setCameraManager();
        setCreator();
        setPlacer();
    }

    private void setPlacer()
    {
        this.placer = new PlacingArrangement(this);
    }

    private void setCameraManager()
    {
        cameraManager = new CameraManager(this);
        this.addMouseWheelListener(cameraManager);
        this.addKeyListener(cameraManager);
        this.addMouseMotionListener(cameraManager);
    }

    private void setCreator()
    {
        creator = new Creator(this);
        this.addMouseListener(creator);
        this.addMouseMotionListener(creator);
    }

    public void setjtree(JTreetmp jt){
        jt.setcreator(creator);
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
                    g2.setColor(Color.BLACK);
                    g2.drawPolygon(tiles[i][j].getPolygon(xRoot, yRoot, tileSize,cotang));
                }

        for (Resource resource:resources)
        {
            g2.setColor(resource.getColor());
            g2.fill(resource.getShape(xRoot,yRoot,tileSize,cotang));
        }

//        for (int j = 0; j < getVerticalTiles(); j++)
//        {
//            if ( j%2 == 0 )
//                g2.setColor(Color.BLACK);
//            else
//                g2.setColor(Color.white);
//            g2.drawLine(0 , j * (tileSize / (2* cotang)), getWidth(),j * (tileSize / (2* cotang)));
//        }
//
//        for (int i = 0; i < getHorizontaltiles(); i++)
//        {
//            if ( i%2 == 0 )
//                g2.setColor(Color.BLACK);
//            else
//                g2.setColor(Color.white);
//            g2.drawLine(i * tileSize, 0, i * tileSize, getHeight());
//        }
    }

    public static void main(String[] args)
    {
        JFrame myFrame = new JFrame("MAP EDITOR");
        myFrame.setMinimumSize(new Dimension(1000,700));
        myFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        myFrame.setLayout(new BorderLayout());
        MapPanel mapPanel = new MapPanel(50,100,1500,1000,160,2);
        mapPanel.setPreferredSize(new Dimension(500,500));
        myFrame.add( mapPanel,BorderLayout.CENTER);

        // injash khoob nist ( cameraManager behtare be panel add she vali dare be frame add mishe)
        myFrame.addKeyListener(mapPanel.cameraManager);
        myFrame.addKeyListener(mapPanel.creator);
        myFrame.addKeyListener(mapPanel.placer);


        JPanel subPanel = new JPanel();
        subPanel.setLayout(new BorderLayout());

        subPanel.setBackground(Color.WHITE);
        MenuExp me = new MenuExp();

        myFrame.setJMenuBar(me.getMenubar());
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JTreetmp jt = new JTreetmp();
                jt.setBackground(Color.WHITE);
                mapPanel.setjtree(jt);
//                JScrollPane panelPane = new JScrollPane(jt);
//                panelPane.setSize(new Dimension(200,400));




                JScrollPane scrollPane = new JScrollPane(jt);
                scrollPane.setPreferredSize(new Dimension(200,200));
                //JScrollBar bar = scrollPane.getVerticalScrollBar();
//                bar.setPreferredSize(new Dimension(20, 0));
//                scrollPane.setHorizontalScrollBar(bar);
                scrollPane.setBackground(Color.WHITE);
                subPanel.add(scrollPane,BorderLayout.BEFORE_FIRST_LINE);
//                myFrame.add(scrollPane,BorderLayout.WEST);
            }
        });
//        setFullScreen(myFrame);

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JTreetmp jt = new JTreetmp();
                jt.setBackground(Color.WHITE);
                mapPanel.setjtree(jt);
//                JScrollPane panelPane = new JScrollPane(jt);
//                panelPane.setSize(new Dimension(200,400));

                MenuExp me = new MenuExp();
                myFrame.setJMenuBar(me.getMenubar());


                JScrollPane scrollPane = new JScrollPane(jt);
                scrollPane.setPreferredSize(new Dimension(200,500));
                //JScrollBar bar = scrollPane.getVerticalScrollBar();
//                bar.setPreferredSize(new Dimension(20, 0));
//                scrollPane.setHorizontalScrollBar(bar);


                scrollPane.setBackground(Color.WHITE);
                subPanel.add(scrollPane,BorderLayout.WEST);
//                myFrame.add(scrollPane,BorderLayout.WEST);
            }
        });
//        me.setplacer(mapPanel.placer);
        myFrame.add(subPanel,BorderLayout.WEST);
        myFrame.setVisible(true);
    }

    private static void setFullScreen(JFrame myFrame)
    {
        myFrame.setResizable(false);
        myFrame.setUndecorated(true);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        gd.setFullScreenWindow(myFrame);
    }

    public void addResource(Resource resource)
    {
        resources.add(resource);
    }

    public void placeTile(Tile tile, int x, int y, long id)
    {
        if ( PlacingArrangement.getLastId() < id )
            placer.nextId();

        if ( PlacingArrangement.getLastId() == id )
        {
            TerrainEvent newEvent = new TerrainEvent(tiles[x][y], tile, x, y, id);
            placer.addEvent(newEvent);
        }

        tiles[x][y] = tile;
    }

    public void setTile(Tile tile, int x, int y)
    {
        tiles[x][y] = tile;
        repaint();
    }

    public long getLastEventId()
    {
        return PlacingArrangement.getLastId();
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


    public Tile[][] getTiles() {
        return tiles;
    }

    public Creator getCreator() {
        return creator;
    }

    public CameraManager getCameraManager() {
        return cameraManager;
    }

    public PlacingArrangement getPlacer() {
        return placer;
    }

    public Tile getTile(int x, int y)
    {
        return tiles[x][y];
    }

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

    public int getxRoot()
    {
        return xRoot;
    }
}
