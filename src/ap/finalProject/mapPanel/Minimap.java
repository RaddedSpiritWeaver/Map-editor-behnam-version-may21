package frameWork;

import mapPanel.Tile;

import javax.swing.*;
import java.awt.*;

public class Minimap extends JPanel{

    private Tile[][] tiles;
    private MapPanel source;
    private int tileSize;

    public Minimap(MapPanel source){
        this.source=source;
        tiles = source.getTiles();
        tileSize = this.getWidth()/source.widthCoord;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        tileSize = this.getHeight()/source.getHeightCoord()*4;
        Graphics2D g2 = (Graphics2D) g;


        for (int j = 0; j < source.heightCoord; j++)
            for (int i = 0; i < source.widthCoord; i++)
                {
                    g2.setColor(tiles[i][j].getColor());
                    g2.fillPolygon(tiles[i][j].getPolygon(1, 1, tileSize,2)); // addade ro haye inja bayad badan kam shan to keshidane mostatil ...
//                    g2.setColor(Color.BLACK);
//                    g2.drawPolygon(tiles[i][j].getPolygon(0, 0, tileSize,2));
                }
        int sizeWidth = source.getWidth()*tileSize/source.tileSize;
        int sizeHeight = source.getHeight()*tileSize/source.tileSize;
        float thickness =  1;
        Stroke oldStroke = g2.getStroke();
        g2.setStroke(new BasicStroke(thickness));
        g2.setColor(Color.yellow);
        g2.drawRect((source.xRoot-1)*tileSize,(source.yRoot-1 )*tileSize/4,sizeWidth,sizeHeight); // inja source.root ke migirim bayad kam she az on rooty ke to paint midim ... (- on biyad inja)
        g2.setStroke(oldStroke);
    }

    // getters and setters:

    public MapPanel getSource() {
        return source;
    }

    public void setTiles(Tile[][] tiles) {
        this.tiles = tiles;
    }

}
