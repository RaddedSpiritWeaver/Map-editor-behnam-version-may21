package frameWork;

import javax.swing.*;
import java.awt.*;

public class EditorFrame extends JFrame {

    private int screenWidth,screenHeight; // !!! maybe these will become irrelevant idk cause every thing has to be set with the frame width and height
    private JPanel treePanel,selectedObjectPanel; // these 4 panels ...
    // for now they will just be normal panels but in future the will be changed with their own classes
    private MapPanel mapPanel;
    private Minimap minimapPanel;

    public EditorFrame(){
        screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        // frame setup:
        this.setLayout(null); // im ganna go with a null manager first ...
        this.setSize(1000,1000);
        this.setResizable(true);
        this.setTitle("Map Editor");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // making the frame full screen: (there is just one drawback to this ... no more menu options and exit buttons)
        this.setUndecorated(true);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        gd.setFullScreenWindow(this);

        // setting up the map panel:
        mapPanel = new MapPanel(100,400,0,0,160,2);
        //mapPanel.setBackground(Color.yellow); // $$$ just a test line ...
        mapPanel.setSize(this.getWidth()/4*3,this.getHeight());
        mapPanel.setLocation(this.getWidth()/4,0);
        this.addKeyListener(mapPanel.getCameraManager());
        this.addKeyListener(mapPanel.getCreator());
        this.add(mapPanel);
        // setting up the minimap panel:
        minimapPanel = new Minimap(mapPanel);
        //minimapPanel.setBackground(Color.blue); //$$$ test line
        minimapPanel.setSize(this.getWidth()/4,this.getHeight()/4);
        minimapPanel.setLocation(0,0);
        this.add(minimapPanel);
        // setting up the tree panel:
        treePanel = new JPanel(null);
        treePanel.setBackground(Color.cyan);
        treePanel.setSize(this.getWidth()/4,this.getHeight()/2);
        treePanel.setLocation(0,this.getHeight()/4);
        this.add(treePanel);
        // setting up the image panel:
        selectedObjectPanel = new JPanel(null);
        selectedObjectPanel.setBackground(Color.magenta);
        selectedObjectPanel.setSize(this.getWidth()/4,this.getHeight()/4);
        selectedObjectPanel.setLocation(0,this.getHeight()/4*3);
        this.add(selectedObjectPanel);


        refreshMini.start();

        this.setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        mapPanel.setSize(this.getWidth()/4*3,this.getHeight());
        mapPanel.setLocation(this.getWidth()/4,0);

        minimapPanel.setSize(this.getWidth()/4,this.getHeight()/4);
        minimapPanel.setLocation(0,0);

        treePanel.setSize(this.getWidth()/4,this.getHeight()/2);
        treePanel.setLocation(0,this.getHeight()/4);

        selectedObjectPanel.setSize(this.getWidth()/4,this.getHeight()/4);
        selectedObjectPanel.setLocation(0,this.getHeight()/4*3);
    }

    Timer refreshMini = new Timer(100,e ->{
        minimapPanel.repaint();
        minimapPanel.setTiles(minimapPanel.getSource().getTiles());
    });

    public static void main(String[] args) {
        new EditorFrame();
    }
}
